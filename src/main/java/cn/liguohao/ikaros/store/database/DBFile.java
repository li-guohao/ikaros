package cn.liguohao.ikaros.store.database;

import javax.persistence.*;
import java.util.Date;

/**文件
 * @author liguohao_cn
 * @date 2020/12/29
 */
@Entity
@Table(name = "file")
public class DBFile {

    /**
     * 文件ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "file_id")
    private Long fileId;

    /**
     * 文件在磁盘上的名称
     */
    @Column(name = "disk_name")
    private String diskName;

    /**
     * 原文件名称
     */
    @Column(name = "original_name")
    private String originalName;

    /**
     * 描述
     */
    private String description;

    /**
     * 文件后缀
     */
    private String suffix;

    /**
     * 文件上传时间
     */
    @Column(name = "upload_time")
    private Date uploadTime;

    /**
     * 文件路径 可直接通过HTTP访问到的
     */
    @Column(name = "web_url")
    private String webUrl;

    /**
     * 文件在磁盘上的路径
     */
    @Column(name = "disk_path")
    private String diskPath;

    /**
     * 文件相对upload文件夹的路径
     */
    @Column(name = "relative_path")
    private String relativePath;

    /**
     * 文件储存的地方 具体请查看 cn.liguohao.ikaros.constant.DiskFilePlace
     */
    private String place;

    /**
     * 文件大小
     */
    private Long size;

    public static DBFile build(){return new DBFile();}

    public Long getFileId() {
        return fileId;
    }

    public DBFile setFileId(Long fileId) {
        this.fileId = fileId;
        return this;
    }

    public String getDiskName() {
        return diskName;
    }

    public DBFile setDiskName(String diskName) {
        this.diskName = diskName;
        return this;
    }

    public String getOriginalName() {
        return originalName;
    }

    public DBFile setOriginalName(String originalName) {
        this.originalName = originalName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DBFile setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSuffix() {
        return suffix;
    }

    public DBFile setSuffix(String suffix) {
        this.suffix = suffix;
        return this;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public DBFile setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
        return this;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public DBFile setWebUrl(String webUrl) {
        this.webUrl = webUrl;
        return this;
    }

    public String getDiskPath() {
        return diskPath;
    }

    public DBFile setDiskPath(String diskPath) {
        this.diskPath = diskPath;
        return this;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public DBFile setRelativePath(String relativePath) {
        this.relativePath = relativePath;
        return this;
    }

    public String getPlace() {
        return place;
    }

    public DBFile setPlace(String place) {
        this.place = place;
        return this;
    }

    public Long getSize() {
        return size;
    }

    public DBFile setSize(Long size) {
        this.size = size;
        return this;
    }
}
