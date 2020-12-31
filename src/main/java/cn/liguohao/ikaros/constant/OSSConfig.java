package cn.liguohao.ikaros.constant;

/**对象存储配置名称
 * @author liguohao_cn
 * @date 2020/12/31
 */
public class OSSConfig {
    private OSSConfig() {}

    /**
     * 拥有OSS控制权限的子账号<access_key_id>
     */
    public static final String ACCESS_KEY_ID = "access_key_id";
    /**
     * 拥有OSS控制权限的子账号<access_key_secret>
     */
    public static final String ACCESS_KEY_SECRET = "access_key_secret";
    /**
     *  储存桶位置(地区)<endpoint>
     */
    public static final String ENDPOINT = "endpoint";
    /**
     * 储存桶名称<bucket_name>
     */
    public static final String BUCKET_NAME = "bucket_name";
    /**
     * 储存桶内储存目录前缀<object_name_prefix>, 默认为 .ikaros/upload
     */
    public static final String OBJECT_NAME_PREFIX = "object_name_prefix";
    /**
     * 文件HTTP访问协议<access_protocol>，默认HTTPS
     */
    public static final String ACCESS_PROTOCOL = "access_protocol";

    /**
     * 文件HTTP访问域名<access_domain> 如 example.oss-cn-shanghai.aliyuncs.com
     */
    public static final String ACCESS_DOMAIN = "access_domain";




}
