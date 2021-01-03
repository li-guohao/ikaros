package cn.liguohao.ikaros.service.impl;

import cn.liguohao.ikaros.annotation.IkarosCache;
import cn.liguohao.ikaros.annotation.IkarosUpdateCache;
import cn.liguohao.ikaros.aspect.IkarosCacheAspect;
import cn.liguohao.ikaros.constant.AppInitConfig;
import cn.liguohao.ikaros.constant.CacheConfig;
import cn.liguohao.ikaros.constant.ConfigType;
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
 * @version 2021/1/3
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
                Config.build().setType(ConfigType.APP_INIT_CONFIG)
                    .setName(AppInitConfig.IS_INITED)
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
        // 初始化缓存策略
        Optional<Config> cacheConfig = configDao.findOne(Example.of(
                Config.build().setType(ConfigType.CACHE_CONFIG)
//                    .setName(CacheConfig.)
        ));


        // 初始化完毕 更新数据库配置表对应记录
        Optional<Config> appInitConfigOptional = configDao.findOne(Example.of(
                Config.build().setType(ConfigType.APP_INIT_CONFIG)
                        .setName(AppInitConfig.IS_INITED)
        ));
        if(appInitConfigOptional.isEmpty()){
            configDao.save(Config.build()
                    .setType(ConfigType.APP_INIT_CONFIG) //app_init_config
                    .setDescription("应用初始化配置")
                    .setUpdateTime(LocalDateTime.now())
                    .setName(AppInitConfig.IS_INITED)  //is_inited
                    .setValue("1") //1
            );
        }else {
            configDao.save(appInitConfigOptional.get().setValue("1"));
        }
        logger.info("[伊卡洛斯]初始化完毕");
        return true;
    }
}
