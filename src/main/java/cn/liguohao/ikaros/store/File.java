package cn.liguohao.ikaros.store;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description TODO 文件
 * @Author liguohao
 * @Date 2020/11/25 19:24
 */
@Entity
@Table(name = "file")
public class File {

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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getDiskPath() {
        return diskPath;
    }

    public void setDiskPath(String diskPath) {
        this.diskPath = diskPath;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getDiskName() {
        return diskName;
    }

    public void setDiskName(String diskName) {
        this.diskName = diskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
