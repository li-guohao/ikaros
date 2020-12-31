package cn.liguohao.ikaros.constant;

/**配置类型 对应config表中的type字段
 * @author liguohao_cn
 * @date 2020/12/31
 */
public class ConfigType {
    private ConfigType(){}

    /**
     * 对象存储配置
     */
    public static final String OSS_CONFIG = "oss_config";

    /**
     * 本地文件配置
     */
    public static final String DISK_FILE_CONFIG = "disk_file_config";

    /**
     * 初始化配置
     */
    public static final String APP_INIT_CONFIG = "app_init_config";
}
