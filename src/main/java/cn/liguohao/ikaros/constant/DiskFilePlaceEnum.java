package cn.liguohao.ikaros.constant;

/**磁盘文件位置
 * @author liguohao_cn
 * @since 2020/12/30
 */
public enum DiskFilePlaceEnum {

    /**
     * 本地文件存储
     */
    LOCAL("local","localDiskFileHandler","cn.liguohao.ikaros.store.diskfile.handler.LocalDiskFileHandler")
    ,
    /**
     * 阿里云对象存储-OSS
     */
    ALIYUN_OSS("aliyun_oss","aliyunOSSDiskFileHandler","cn.liguohao.ikaros.store.diskfile.handler.AliyunOSSDiskFileHandler")
    ,
    /**
     * 腾讯云对象存储-COS
     */
    TENCENT_CLOUD_COS("tencent_cloud_cos","tencentCloudCOSDiskFileHandler","cn.liguohao.ikaros.store.diskfile.handler.TencentCloudCOSDiskFileHandler")

    ; //结束符

    /**
     * 储存商名称
     */
    private String name;

    /**
     * 对应的DiskFileHandler实现类简单名称 类名但首字母是小写的
     */
    private String simpleClassName;

    /**
     * 对应的DiskFileHandler实现类完整类名
     */
    private String className;

     DiskFilePlaceEnum(String name, String simpleClassName, String className) {
        this.name = name;
        this.simpleClassName = simpleClassName;
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSimpleClassName() {
        return simpleClassName;
    }

    public void setSimpleClassName(String simpleClassName) {
        this.simpleClassName = simpleClassName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}
