package cn.liguohao.ikaros.enums.db.config;

/**
 * 文件存储策略 对应的值
 *
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @see DiskFileKey#STRATEGY
 * @since 2021/5/23
 */
public enum DiskFileStrategyValue {

    /**
     * 本地文件存储
     */
    LOCAL("localDiskFileHandler", "cn.liguohao.ikaros.store.diskfile.handler.LocalDiskFileHandler"),
    /**
     * 阿里云对象存储-OSS
     */
    ALIYUN_OSS("aliyunOSSDiskFileHandler", "cn.liguohao.ikaros.store.diskfile.handler.AliyunOSSDiskFileHandler"),
    /**
     * 腾讯云对象存储-COS
     */
    TENCENT_CLOUD_COS("tencentCloudCOSDiskFileHandler", "cn.liguohao.ikaros.store.diskfile.handler.TencentCloudCOSDiskFileHandler"),
    /**
     * 百度网盘
     */
    PAN_BAIDU("panBaiduDiskFileHandler", "cn.liguohao.ikaros.store.diskfile.handler.PanBaiduDiskFileHandler"),

    ;

    /**
     * 对应的DiskFileHandler实现类简单名称 类名但首字母是小写的
     */
    private String simpleClassName;

    /**
     * 对应的DiskFileHandler实现类完整类名
     */
    private String className;

    DiskFileStrategyValue(String simpleClassName, String className) {
        this.simpleClassName = simpleClassName;
        this.className = className;
    }

    public String getSimpleClassName() {
        return simpleClassName;
    }

    public DiskFileStrategyValue setSimpleClassName(String simpleClassName) {
        this.simpleClassName = simpleClassName;
        return this;
    }

    public String getClassName() {
        return className;
    }

    public DiskFileStrategyValue setClassName(String className) {
        this.className = className;
        return this;
    }
}
