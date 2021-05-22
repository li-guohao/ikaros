package cn.liguohao.ikaros.service.impl;

import cn.liguohao.ikaros.annotation.IkarosCache;
import cn.liguohao.ikaros.annotation.IkarosUpdateCache;
import cn.liguohao.ikaros.enums.ConfigItemEnum;
import cn.liguohao.ikaros.dao.ConfigDao;
import cn.liguohao.ikaros.exception.IkarosException;
import cn.liguohao.ikaros.service.ConfigService;
import cn.liguohao.ikaros.store.database.Config;
import cn.liguohao.ikaros.util.HttpClientUtils;
import cn.liguohao.ikaros.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**服务层实现-系统配置
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/1/3
 */
@Service
public class ConfigServiceImpl extends BaseServiceImpl<Config> implements ConfigService {

    private Logger logger = LoggerFactory.getLogger(ConfigServiceImpl.class);

    @Autowired
    private ConfigDao configDao;

    @Value("${spring.profiles.active}")
    private String springProfilesActive;

    /**
     * @see ConfigService#isInited()
     */
    @Override
    @IkarosCache
    public boolean isInited() {
        Optional<Config> configOptional = configDao.findOne(Example.of(
                Config.build().setType(ConfigItemEnum.APP_INIT_IS_INITED.getType())
                    .setName(ConfigItemEnum.APP_INIT_IS_INITED.getName())
        ));
        // 如果设置表有对应的记录，并且记录值为1，才代表已经初始化了
        return configOptional.isPresent() && "1".equals(configOptional.get().getValue());
    }

    /**
     * @see ConfigService#init()
     */
    @Override
    @Transactional
    @IkarosUpdateCache
    public boolean init() {
        logger.info("[伊卡洛斯]开始初始化操作");
        // 初始化应用初始化配置
        initConfigItem(ConfigItemEnum.APP_INIT_IS_INITED);
        // 初始化默认主题文件URL地址
        initConfigItem(ConfigItemEnum.DOWNLOAD_DEFAULT_THEME_URL);
        // 初始化缓存策略
        initConfigItem(ConfigItemEnum.CACHE_DEFAULT_STRATEGY);
        // 初始化文件存储策略
        initConfigItem(ConfigItemEnum.DISK_FILE_PLACE_LOCAL);
        // 初始化阿里云对象存储配置项
        initAliyunOSSConfigItem();

        // 生产环境 ==> 初始化下载默认的主题文件
        if("pro".equals(springProfilesActive)) {downloadDefaultTheme();}

        // 初始化完毕 更新数据库配置表对应记录
        Optional<Config> appInitConfigOptional = configDao.findOne(Example.of(
                Config.build().setType(ConfigItemEnum.APP_INIT_IS_INITED.getType())
                        .setName(ConfigItemEnum.APP_INIT_IS_INITED.getName())
        ));
        if(appInitConfigOptional.isPresent()){
            configDao.save(appInitConfigOptional.get().setValue("1"));
        }
        logger.info("[伊卡洛斯]初始化完毕");
        return true;
    }

    /**
     * @see ConfigService#findOne(Example)
     */
    @Override
    @IkarosCache
    public Config findOne(Example<Config> configExample) {
        Optional<Config> configOptional = configDao.findOne(configExample);
        return configOptional.isPresent()?configOptional.get():null;
    }

    /**
     * @see ConfigService#findList(Example)
     */
    @Override
    public List<Config> findList(Example<Config> configExample) {
        return configDao.findAll(configExample);
    }



    /**
     * 下载默认的主题文件
     */
    private void downloadDefaultTheme() {

        // 获取主题文件URL
        String themeSimpleZipUrl = configDao.findOne(Example.of(
                Config.build().setType(ConfigItemEnum.DOWNLOAD_DEFAULT_THEME_URL.getType())
                        .setName(ConfigItemEnum.DOWNLOAD_DEFAULT_THEME_URL.getName())
        )).get().getValue();

        String ikarosUserHome = System.getProperty("user.home") + "/.ikaros";
        String defaultThemeDir = ikarosUserHome + "/theme";
        String defaultSimpleThemeFilePath = defaultThemeDir + themeSimpleZipUrl.substring(themeSimpleZipUrl.lastIndexOf("/"));

        // 默认主题ZIP文件
        File ikarosThemeDefaultFile = new File(defaultSimpleThemeFilePath); // .ikaros/theme/simple.zip
        if(!ikarosThemeDefaultFile.getParentFile().exists()){
            ikarosThemeDefaultFile.getParentFile().mkdirs();
        }

        // 下载默认的主题文件 文件到错误目录之下
        defaultSimpleThemeFilePath = defaultSimpleThemeFilePath.replace("/","\\");
        try{
            HttpClientUtils.downloadFile(themeSimpleZipUrl,defaultSimpleThemeFilePath);
            logger.info("成功下载默认主题(simple)文件 ==> url: " + themeSimpleZipUrl + " 目录：" + defaultSimpleThemeFilePath);

            // 解压缩到指定目录下
            File defaultSimpleThemeFile = new File(defaultSimpleThemeFilePath);
            HttpClientUtils.unzip(defaultSimpleThemeFile,defaultThemeDir);
            logger.info("成功解压默认主题压缩文件包(simple.zip)至指定目录下 ==> " + defaultThemeDir);

            // 删除压缩包文件
            if(defaultSimpleThemeFile.isFile() && defaultSimpleThemeFile.exists()) {
                boolean isDelSuccess = defaultSimpleThemeFile.delete();
                if(isDelSuccess) {
                   logger.info("成功删除下载的默认主题压缩包文件 ==> " + defaultSimpleThemeFile.getAbsolutePath());
                } else {
                    logger.warn("删除文件失败 ==> 默认主题压缩包文件: " + defaultSimpleThemeFile.getAbsolutePath());
                }
            }
        }catch (IOException ioException){
            ioException.printStackTrace();
            logger.error(ioException.getMessage());
            throw new IkarosException(ioException.getMessage());
        }
    }

    /**
     * 初始化配置项
     * @param configItemEnum 对应的配置项
     */
    private void initConfigItem(ConfigItemEnum configItemEnum){
        // 查询配置项
        Optional<Config> cacheConfigOptional = configDao.findOne(Example.of(
                Config.build().setType(configItemEnum.getType())
                        .setName(configItemEnum.getName())
        ));
        // 获取配置项
        Config config = cacheConfigOptional.isEmpty()?Config.build():cacheConfigOptional.get();
        // 更新配置项
        configDao.save(
                config.setType(configItemEnum.getType())
                        .setName(configItemEnum.getName())
                        .setValue(configItemEnum.getValue())
                        .setDescription(configItemEnum.getDescription())
                        .setUpdateTime(LocalDateTime.now())
        );
    }

    /**
     * 初始化阿里云对象存储配置项
     */
    private void initAliyunOSSConfigItem(){
        initConfigItem(ConfigItemEnum.ALIYUN_OSS_ACCESS_KEY_ID);
        initConfigItem(ConfigItemEnum.ALIYUN_OSS_ACCESS_KEY_SECRET);
        initConfigItem(ConfigItemEnum.ALIYUN_OSS_ENDPOINT);
        initConfigItem(ConfigItemEnum.ALIYUN_OSS_BUCKET_NAME);
        initConfigItem(ConfigItemEnum.ALIYUN_OSS_OBJECT_NAME_PREFIX);
        initConfigItem(ConfigItemEnum.ALIYUN_OSS_ACCESS_PROTOCOL);
        initConfigItem(ConfigItemEnum.ALIYUN_OSS_ACCESS_DOMAIN);
    }

    @Override
    public Specification<Config> buildSpecification(Config searchEntity) {
        return new Specification<Config>() {
            @Override
            public Predicate toPredicate(Root<Config> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = null;

                // 根据类型精准查询
                if(!StringUtils.isEmpty(searchEntity.getType())){
                    Predicate originalNamePredicate = criteriaBuilder.equal(root.get("type"),searchEntity.getType());
                    if(predicate==null){
                        predicate = originalNamePredicate;
                    }else {
                        predicate = criteriaBuilder.and(predicate,originalNamePredicate);
                    }
                }

                return predicate;
            }
        };
    }

    @Override
    @IkarosCache
    public Set<String> findTypes() {
        List<Config> configs = configDao.findAll();
        Set<String> types = new HashSet<>();
        configs.forEach(config -> types.add(config.getType()));
        return types;
    }
}
