package cn.liguohao.ikaros.enums.db.config;

/**
 * 数据缓存策略 对应的值
 *
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @see DiskFileKey#STRATEGY
 * @since 2021/5/23
 */
public enum CacheStrategyValue {

    /**
     * 内存缓存
     */
    MEMORY
    ,

    /**
     * redis缓存
     */
    REDIS
    ,

    ;
}
