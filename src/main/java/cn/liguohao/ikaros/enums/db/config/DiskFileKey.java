package cn.liguohao.ikaros.enums.db.config;

/**
 * 磁盘文件 对应的KEY
 *
 * @since 2021/5/23
 * @see ConfigType#DISK_FILE
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 */
public enum DiskFileKey {

    /**
     * 文件存储策略：
     * LOCAL-本地文件存储 ALIYUN_OSS-阿里云对象存储
     * PAN_BAIDU-百度网盘 TENCENT_CLOUD_COS-腾讯云对象存储
     * @see DiskFileStrategyValue#LOCAL
     * @see DiskFileStrategyValue#ALIYUN_OSS
     * @see DiskFileStrategyValue#PAN_BAIDU
     * @see DiskFileStrategyValue#TENCENT_CLOUD_COS
     */
    STRATEGY("文件存储策略：LOCAL-本地文件存储 ALIYUN_OSS-阿里云对象存储 PAN_BAIDU-百度网盘 TENCENT_CLOUD_COS-腾讯云对象存储")
    ,

    ;



    /**
     * 此键的描述
     */
    private String description;

    DiskFileKey(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
