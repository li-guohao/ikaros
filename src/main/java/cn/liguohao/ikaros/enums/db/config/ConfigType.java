package cn.liguohao.ikaros.enums.db.config;

import java.lang.reflect.Method;

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
    APP_INIT(APPInitKey.class)
    ,

    /**
     * 数据缓存
     */
    CACHE(CacheKey.class)
    ,

    /**
     * 主题
     */
    THEME(ThemeKey.class)
    ,
    /**
     * 磁盘文件
     */
    DISK_FILE(DiskFileKey.class)
    ,

    /**
     * 阿里云对象存储
     */
    ALIYUN_OSS(AliyunOSSKey.class)
    ,

    /**
     * 百度网盘
     */
    PAN_BAIDU(null)
    ,

    ;

    /**
     * 对应的KEY枚举类的类型
     */
    private Class keyEnumClass;

    ConfigType(Class keyEnumClass) {
        this.keyEnumClass = keyEnumClass;
    }

    public Class getKeyEnumClass() {
        return keyEnumClass;
    }

}
