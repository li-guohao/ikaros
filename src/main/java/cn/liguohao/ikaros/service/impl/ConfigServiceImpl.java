package cn.liguohao.ikaros.service.impl;

import cn.liguohao.ikaros.annotation.IkarosCache;
import cn.liguohao.ikaros.annotation.IkarosUpdateCache;
import cn.liguohao.ikaros.constant.ConfigItemEnum;
import cn.liguohao.ikaros.dao.ConfigDao;
import cn.liguohao.ikaros.service.ConfigService;
import cn.liguohao.ikaros.store.database.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

/**服务层实现-系统配置
 * @author liguohao_cn
 * @since 2021/1/3
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    private Logger logger = LoggerFactory.getLogger(ConfigServiceImpl.class);

    @Autowired
    private ConfigDao configDao;

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
        if(configOptional.isPresent() && "1".equals(configOptional.get().getValue())) {
            return true;
        }else {
            return false;
        }
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
        // 初始化缓存策略
        initConfigItem(ConfigItemEnum.CACHE_DEFAULT_STRATEGY);
        // 初始化文件存储策略
        initConfigItem(ConfigItemEnum.DISK_FILE_PLACE_LOCAL);
        // 初始化阿里云对象存储配置项
        initAliyunOSSConfigItem();


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
     * 初始化配置项
     * @param configItemEnum 对应的配置项
     */
    private void initConfigItem(ConfigItemEnum configItemEnum){
        // 查询配置项
        Optional<Config> cacheConfigOptional = configDao.findOne(Example.of(
                Config.build().setType(configItemEnum.getType())
                        .setName(configItemEnum.getName())
        ));
        // 更新配置项
        if(cacheConfigOptional.isEmpty()){ //配置项不存在 添加配置项
            configDao.save(
                    Config.build().setType(configItemEnum.getType())
                            .setName(configItemEnum.getName())
                            .setValue(configItemEnum.getValue())
                            .setDescription(configItemEnum.getDescription())
                            .setUpdateTime(LocalDateTime.now())
            );
        }
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

}
