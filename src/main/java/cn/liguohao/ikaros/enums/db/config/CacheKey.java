package cn.liguohao.ikaros.enums.db.config;

/**
 * 数据缓存 对应的KEY
 *
 * @since 2021/5/23
 * @see ConfigType#CACHE
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 */
public enum CacheKey {

    /**
     * 缓存策略: 默认内存缓存
     */
    STRATEGY("缓存策略: 默认内存缓存")
    ,

    ;


    /**
     * 此键的描述
     */
    private String description;

    CacheKey(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
