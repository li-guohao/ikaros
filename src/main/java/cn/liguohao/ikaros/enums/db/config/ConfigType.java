package cn.liguohao.ikaros.enums.db.config;

/**
 * 数据库配置项类型
 *
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/5/23
 */
public enum ConfigType {
    /**
     * 应用初始化
     */
    APP_INIT
    ,

    /**
     * 数据缓存
     */
    CACHE
    ,

    /**
     * 主题
     */
    THEME
    ,
    /**
     * 磁盘文件
     */
    DISK_FILE
    ,

    /**
     * 阿里云对象存储
     */
    ALIYUN_OSS
    ,

    /**
     * 百度网盘
     */
    PAN_BAIDU
    ,

    ;
}
