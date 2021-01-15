package cn.liguohao.ikaros.constant;

/**配置项枚举 对应[config]表
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/1/3
 */
public enum ConfigItemEnum {

    /**
     * 应用初始化配置
     */
    APP_INIT_IS_INITED("app_init","is_inited","0","应用初始化配置")
    ,
    /**
     * 默认主题文件URL地址
     */
    DOWNLOAD_DEFAULT_THEME_URL("download","default_theme_url","https://static.liguohao.cn/.ikaros/theme/simple.zip","默认主题文件URL地址")
    ,
    /**
     * <p>默认缓存策略-内存缓存</p>
     * <p>用一个HashMap缓存</p>
     */
    CACHE_DEFAULT_STRATEGY("cache","default_strategy","memory","默认缓存策略-内存缓存")
    ,
    /**
     * <p>文件存储策略-本地</p>
     * <p>文件上传到本地服务器</p>
     */
    DISK_FILE_PLACE_LOCAL("disk_file","place","local","文件存储策略-本地")
    ,
    /**
     * <p>文件存储策略-阿里云对象存储</p>
     */
    DISK_FILE_PLACE_ALIYUN_OSS("disk_file","place","aliyun_oss","文件存储策略-阿里云对象存储")
    ,
    /**
     * 阿里云对象存储-拥有OSS控制权限的子账号[access_key_id]
     */
    ALIYUN_OSS_ACCESS_KEY_ID("aliyun_oss","access_key_id","","阿里云对象存储-拥有OSS控制权限的子账号")
    ,
    /**
     * 阿里云对象存储-拥有OSS控制权限的子账号[access_key_secret]
     */
    ALIYUN_OSS_ACCESS_KEY_SECRET("aliyun_oss","access_key_secret","","阿里云对象存储-拥有OSS控制权限的子账号")
    ,
    /**
     * 阿里云对象存储-储存桶位置(地区)[endpoint]
     */
    ALIYUN_OSS_ENDPOINT("aliyun_oss","endpoint","","阿里云对象存储-储存桶位置(地区)")
    ,
    /**
     * 阿里云对象存储-储存桶名称[bucket_name]
     */
    ALIYUN_OSS_BUCKET_NAME("aliyun_oss","bucket_name","","阿里云对象存储-储存桶名称")
    ,
    /**
     * 阿里云对象存储-储存桶内储存目录前缀[object_name_prefix]
     * 默认为[.ikaros/upload]
     */
    ALIYUN_OSS_OBJECT_NAME_PREFIX("aliyun_oss","object_name_prefix",".ikaros/upload","阿里云对象存储-储存桶内储存目录前缀")
    ,
    /**
     * 阿里云对象存储-文件HTTP访问协议[access_protocol]
     * 默认为[https]
     */
    ALIYUN_OSS_ACCESS_PROTOCOL("aliyun_oss","access_protocol","https","阿里云对象存储-文件HTTP访问协议")
    ,
    /**
     * 阿里云对象存储-文件HTTP访问域名[access_domain]
     * 如：example.oss-cn-shanghai.aliyuncs.com
     */
    ALIYUN_OSS_ACCESS_DOMAIN("aliyun_oss","access_domain","","阿里云对象存储-文件HTTP访问域名")
    ,


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
     * 配置项描述
     */
    private String description;

    /**
     * 对应配置的实现类
     */
    private String implClassName;

    ConfigItemEnum(String type, String name, String value, String description) {
        this.type = type;
        this.name = name;
        this.value = value;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public ConfigItemEnum setType(String type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public ConfigItemEnum setName(String name) {
        this.name = name;
        return this;
    }

    public String getValue() {
        return value;
    }

    public ConfigItemEnum setValue(String value) {
        this.value = value;
        return this;
    }

    public String getImplClassName() {
        return implClassName;
    }

    public ConfigItemEnum setImplClassName(String implClassName) {
        this.implClassName = implClassName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ConfigItemEnum setDescription(String description) {
        this.description = description;
        return this;
    }
}
