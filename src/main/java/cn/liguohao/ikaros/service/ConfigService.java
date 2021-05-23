package cn.liguohao.ikaros.service;

import cn.liguohao.ikaros.annotation.IkarosCache;
import cn.liguohao.ikaros.annotation.IkarosUpdateCache;
import cn.liguohao.ikaros.dao.ConfigDao;
import cn.liguohao.ikaros.enums.db.config.*;
import cn.liguohao.ikaros.exception.IkarosException;
import cn.liguohao.ikaros.store.database.Config;
import cn.liguohao.ikaros.util.HttpClientUtils;
import cn.liguohao.ikaros.util.IkarosAssert;
import cn.liguohao.ikaros.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 服务层实现-系统配置
 *
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/1/3
 */
@Service
public class ConfigService {

    private Logger logger = LoggerFactory.getLogger(ConfigService.class);

    @Autowired
    private ConfigDao configDao;
    @Autowired
    private ConfigService configService;

    @Value("${spring.profiles.active}")
    private String springProfilesActive;

    @Value("${ikaros.config.theme.default-download-url}")
    private String defaultThemeZipUrl;


    /**
     * 根据类型和键新增或更新配置项
     * 先查询后保存，保证不会插入重复的数据
     *
     * @param type        配置类型
     * @param key         键
     * @param description 配置项描述
     * @param value       新值
     */
    @IkarosUpdateCache
    public void saveByTypeAndKey(String type, String key, String description, String value) {
        // 查询是否存在
        Optional<Config> configOptional = configDao.findOne(Example.of(
                Config.build()
                        .setType(type)
                        .setKey(key)
        ));
        Config config = new Config();
        if (configOptional.isPresent()) {
            config = configOptional.get();
        }
        // 保存
        configDao.save(
                config.setType(type)
                        .setKey(key)
                        .setValue(value)
                        .setDescription(description)
        );

    }

    /**
     * 应用是否已经初始化
     *
     * @return true-已经初始化 false-未初始化
     */
    @IkarosCache
    public boolean isInited() {
        Optional<Config> configOptional = configDao.findOne(Example.of(
                Config.build().setType(ConfigType.APP_INIT.name())
                        .setKey(APPInitKey.IS_INITED.name())
        ));
        // 如果设置表有对应的记录，并且已经初始化了
        return configOptional.isPresent() && (AppIsInitedValue.valueOf(configOptional.get().getValue()) == AppIsInitedValue.INITED);
    }


    /**
     * 应用初始化
     *
     * @return true-初始化成功 false-初始化失败
     */
    @Transactional(rollbackOn = Exception.class)
    @IkarosUpdateCache
    public boolean init() {
        logger.info("[伊卡洛斯]开始初始化操作");
        // 初始化配置项
        initConfigItem();

        // 生产环境 ==> 初始化下载默认的主题文件
        if ("pro".equals(springProfilesActive)) {
            downloadDefaultTheme();
        }

        // 初始化完毕 更新数据库配置表对应记录
        configService.saveByTypeAndKey(ConfigType.APP_INIT.name(), APPInitKey.IS_INITED.name(), APPInitKey.IS_INITED.getDescription(), AppIsInitedValue.INITED.name());
        logger.info("[伊卡洛斯]初始化完毕");
        return true;
    }

    /**
     * 下载默认的主题文件
     */
    private void downloadDefaultTheme() {

        // 获取主题文件URL
        String themeSimpleZipUrl = configDao.findOne(Example.of(
                Config.build().setType(ConfigType.THEME.name())
                        .setKey(ThemeKey.DEFAULT_DOWNLOAD_URL.name())
        )).get().getValue();

        String ikarosUserHome = System.getProperty("user.home") + "/.ikaros";
        String defaultThemeDir = ikarosUserHome + "/theme";
        String defaultSimpleThemeFilePath = defaultThemeDir + themeSimpleZipUrl.substring(themeSimpleZipUrl.lastIndexOf("/"));

        // 默认主题ZIP文件
        File ikarosThemeDefaultFile = new File(defaultSimpleThemeFilePath); // .ikaros/theme/simple.zip
        if (!ikarosThemeDefaultFile.getParentFile().exists()) {
            ikarosThemeDefaultFile.getParentFile().mkdirs();
        }

        // 下载默认的主题文件 文件到错误目录之下
        defaultSimpleThemeFilePath = defaultSimpleThemeFilePath.replace("/", "\\");
        try {
            HttpClientUtils.downloadFile(themeSimpleZipUrl, defaultSimpleThemeFilePath);
            logger.info("成功下载默认主题(simple)文件 ==> url: " + themeSimpleZipUrl + " 目录：" + defaultSimpleThemeFilePath);

            // 解压缩到指定目录下
            File defaultSimpleThemeFile = new File(defaultSimpleThemeFilePath);
            HttpClientUtils.unzip(defaultSimpleThemeFile, defaultThemeDir);
            logger.info("成功解压默认主题压缩文件包(simple.zip)至指定目录下 ==> " + defaultThemeDir);

            // 删除压缩包文件
            if (defaultSimpleThemeFile.isFile() && defaultSimpleThemeFile.exists()) {
                boolean isDelSuccess = defaultSimpleThemeFile.delete();
                if (isDelSuccess) {
                    logger.info("成功删除下载的默认主题压缩包文件 ==> " + defaultSimpleThemeFile.getAbsolutePath());
                } else {
                    logger.warn("删除文件失败 ==> 默认主题压缩包文件: " + defaultSimpleThemeFile.getAbsolutePath());
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
            logger.error(ioException.getMessage());
            throw new IkarosException(ioException.getMessage());
        }
    }

    /**
     * 初始化配置项
     */
    private void initConfigItem() {

        configService.saveByTypeAndKey(ConfigType.APP_INIT.name(), APPInitKey.IS_INITED.name(), APPInitKey.IS_INITED.getDescription(), AppIsInitedValue.NOT_INITED.name());

        configService.saveByTypeAndKey(ConfigType.CACHE.name(), CacheKey.STRATEGY.name(), CacheKey.STRATEGY.getDescription(), CacheStrategyValue.MEMORY.name());

        configService.saveByTypeAndKey(ConfigType.THEME.name(), ThemeKey.DEFAULT_DOWNLOAD_URL.name(), ThemeKey.DEFAULT_DOWNLOAD_URL.getDescription(), defaultThemeZipUrl);
        configService.saveByTypeAndKey(ConfigType.THEME.name(), ThemeKey.CURRENT.name(), ThemeKey.CURRENT.getDescription(), defaultThemeZipUrl.substring(defaultThemeZipUrl.lastIndexOf("/") + 1, defaultThemeZipUrl.lastIndexOf(".")));

        configService.saveByTypeAndKey(ConfigType.ALIYUN_OSS.name(), AliyunOSSKey.ACCESS_KEY_ID.name(), AliyunOSSKey.ACCESS_KEY_ID.getDescription(), "");
        configService.saveByTypeAndKey(ConfigType.ALIYUN_OSS.name(), AliyunOSSKey.ACCESS_DOMAIN.name(), AliyunOSSKey.ACCESS_DOMAIN.getDescription(), "");
        configService.saveByTypeAndKey(ConfigType.ALIYUN_OSS.name(), AliyunOSSKey.ACCESS_INTERNAL_DOMAIN.name(), AliyunOSSKey.ACCESS_INTERNAL_DOMAIN.getDescription(), "");
        configService.saveByTypeAndKey(ConfigType.ALIYUN_OSS.name(), AliyunOSSKey.ACCESS_KEY_SECRET.name(), AliyunOSSKey.ACCESS_KEY_SECRET.getDescription(), "");
        configService.saveByTypeAndKey(ConfigType.ALIYUN_OSS.name(), AliyunOSSKey.ACCESS_PROTOCOL.name(), AliyunOSSKey.ACCESS_PROTOCOL.getDescription(), AliyunOSSAccessProtocolValue.HTTPS.name());
        configService.saveByTypeAndKey(ConfigType.ALIYUN_OSS.name(), AliyunOSSKey.ENDPOINT.name(), AliyunOSSKey.ENDPOINT.getDescription(), "");
        configService.saveByTypeAndKey(ConfigType.ALIYUN_OSS.name(), AliyunOSSKey.OBJECT_NAME_PREFIX.name(), AliyunOSSKey.OBJECT_NAME_PREFIX.getDescription(), ".ikaros/upload");
        configService.saveByTypeAndKey(ConfigType.ALIYUN_OSS.name(), AliyunOSSKey.BUCKET_NAME.name(), AliyunOSSKey.BUCKET_NAME.getDescription(), "");

        configService.saveByTypeAndKey(ConfigType.DISK_FILE.name(), DiskFileKey.STRATEGY.name(), DiskFileKey.STRATEGY.getDescription(), DiskFileStrategyValue.LOCAL.name());

    }

    /**
     * 查询所有的配置类型
     *
     * @return 配置类型集合
     */
    @IkarosCache
    public Set<String> findTypes() {
        List<Config> configs = configDao.findAll();
        Set<String> types = new HashSet<>();
        configs.forEach(config -> types.add(config.getType()));
        return types;
    }

    /**
     * 查询DB获取主题名称
     *
     * @return 主题名称
     */
    @IkarosCache
    public String getThemeName() {
        String themeName = "";
        if (StringUtils.isEmpty(themeName)) {
            // 获取主题文件URL
            Optional<Config> configOptional = configDao.findOne(Example.of(
                    Config.build().setType(ConfigType.THEME.name())
                            .setKey(ThemeKey.CURRENT.name())
            ));
            if (configOptional.isEmpty()) {
                themeName = "simple";
            } else {
                themeName = configOptional.get().getValue();
            }
        }
        return themeName;
    }

    /**
     * @return 当前的文件存储策略
     */
    @IkarosCache
    public DiskFileStrategyValue getCurrentDiskFileStrategyValue() {
        DiskFileStrategyValue currentStrategy = null;
        Optional<Config> configOptional = configDao.findOne(Example.of(
                Config.build()
                        .setType(ConfigType.DISK_FILE.name())
                        .setKey(DiskFileKey.STRATEGY.name())
        ));
        if (configOptional.isPresent()) {
            currentStrategy = DiskFileStrategyValue.valueOf(configOptional.get().getValue());
        }
        return currentStrategy;
    }

    /**
     * 查询所有
     *
     * @param config 查询条件
     * @return 集合
     */
    @IkarosCache
    public List<Config> findAll(Config config) {
        return configDao.findAll(Example.of(config));
    }

    /**
     * 查询所有
     *
     * @return 集合
     */
    @IkarosCache
    public List<Config> findAll() {
        return configDao.findAll();
    }

    /**
     * 根据文件初始化配置项
     *
     * @param properties 带有配置信息的文件
     * @param configType 待初始化的配置类型
     */
    @IkarosUpdateCache
    @Transactional(rollbackOn = Exception.class)
    public void readProperties2initItem(ConfigType configType, Properties properties) {
        // 获取对应的键Class
        Class keyEnumClass = configType.getKeyEnumClass();
        Object keyEnum = Arrays.stream(keyEnumClass.getEnumConstants()).filter(configKey -> configKey.getClass().equals(keyEnumClass)).findFirst().get();
        Method nameMethod = null;
        Method getDescriptionMethod = null;
        Method valuesMethod = null;
        for (Method method : keyEnumClass.getMethods()) {
            if ("name".equals(method.getName())) {
                nameMethod = method;
            }
            if ("getDescription".equals(method.getName())) {
                getDescriptionMethod = method;
            }
            if ("values".equals(method.getName())) {
                valuesMethod = method;
            }
        }
        try {
            // 遍历键枚举所有的值
            for (Object configKey : (Object[]) valuesMethod.invoke(null)) {
                String configKeyName = nameMethod.invoke(configKey).toString();
                // 参数缺省校验 校验Properties文件中对应的值是否为空
                IkarosAssert.isNotEmpty(properties.get(configKeyName), getDescriptionMethod.invoke(configKey) + "=>不能为空");
                // 保存文件中的对应值到数据库对应的记录中
                configService.saveByTypeAndKey(
                        configType.name()
                        , configKeyName
                        , getDescriptionMethod.invoke(keyEnum).toString()
                        , properties.get(configKeyName).toString()
                );
            }
        } catch (IllegalAccessException e) {
            logger.error("参数异常 ==> ", e);
        } catch (InvocationTargetException e) {
            logger.error("反射执行异常 ==> ", e);
        }
    }

    /**
     * 保存
     *
     * @param config 待保存对象
     */
    @IkarosUpdateCache
    public void save(Config config) {
        configDao.save(config);
    }

    /**
     * 根据配置类型获取对应的配置信息
     * @param configType 配置项类型
     * @return 对应配置项的信息集合
     */
    @IkarosCache
    public Map<String, String> getConfigInfoByType(ConfigType configType) {
        HashMap<String, String> configMap = new HashMap<>();
        List<Config> configList = configDao.findAll(Example.of(
                Config.build().setType(configType.name())
        ));
        configList.forEach(config -> configMap.put(config.getKey(),config.getValue()));
        return configMap;
    }

}
