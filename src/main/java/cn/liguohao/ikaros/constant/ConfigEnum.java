package cn.liguohao.ikaros.constant;

/**配置 对应[config]表
 * @author liguohao_cn
 * @since 2021/1/3
 */
public enum ConfigEnum {

    /**
     * 应用初始化配置
     */
    APP_INIT_IS_INITED("app_init","is_inited","1")
    ,
    /**
     * <p>默认缓存策略</p>
     * <ul>
     *     <li>memory-内存缓存, 用一个HashMap缓存</li>
     * </ul>
     */
    CACHE_DEFAULT_STRATEGY("cache_config","default_strategy","memory")

    ;//END


    /**
     * 配置类型
     */
    private String type;

    /**
     * 配置名称
     */
    private String name;

    /**
     * 配置值
     */
    private String value;

    /**
     * 对应配置的实现类
     */
    private String implClassName;

    ConfigEnum(String type, String name, String value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public ConfigEnum setType(String type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public ConfigEnum setName(String name) {
        this.name = name;
        return this;
    }

    public String getValue() {
        return value;
    }

    public ConfigEnum setValue(String value) {
        this.value = value;
        return this;
    }

    public String getImplClassName() {
        return implClassName;
    }

    public ConfigEnum setImplClassName(String implClassName) {
        this.implClassName = implClassName;
        return this;
    }
}
