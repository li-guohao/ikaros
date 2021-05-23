package cn.liguohao.ikaros.enums.db.config;

/**
 * 阿里云对象存储 对应的KEY
 *
 * @since 2021/5/23
 * @see ConfigType#ALIYUN_OSS
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 */
public enum AliyunOSSKey {

    /**
     * 阿里云对象存储-文件HTTP外网访问域名
     */
    ACCESS_DOMAIN("阿里云对象存储-文件HTTP访问域名")
    ,
    /**
     * 阿里云对象存储-文件HTTP内网访问域名
     */
    ACCESS_INTERNAL_DOMAIN("阿里云对象存储-文件HTTP内网访问域名")
    ,

    /**
     * 阿里云对象存储-拥有OSS控制权限的子账号
     */
    ACCESS_KEY_SECRET("阿里云对象存储-拥有OSS控制权限的子账号")
    ,

    /**
     * 阿里云对象存储-文件允许的访问协议
     * HTTP, HTTPS
     * @see AliyunOSSAccessProtocolValue#HTTP
     * @see AliyunOSSAccessProtocolValue#HTTPS
     */
    ACCESS_PROTOCOL("阿里云对象存储-文件允许的访问协议 HTTP, HTTPS")
    ,

    /**
     * 阿里云对象存储-拥有OSS控制权限的子账号
     */
    ACCESS_KEY_ID("阿里云对象存储-拥有OSS控制权限的子账号")
    ,

    /**
     * 阿里云对象存储-储存桶位置(地区)
     */
    ENDPOINT("阿里云对象存储-储存桶位置(地区)")
    ,

    /**
     * 阿里云对象存储-储存桶内储存目录前缀
     */
    OBJECT_NAME_PREFIX("阿里云对象存储-储存桶内储存目录前缀")
    ,

    /**
     * 阿里云对象存储-储存桶名称
     */
    BUCKET_NAME("阿里云对象存储-储存桶名称")
    ,

    ;


    /**
     * 此键的描述
     */
    private String description;

    AliyunOSSKey(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
