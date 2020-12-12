package cn.liguohao.ikaros.store;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description 动态
 * @Author liguohao
 * @Date 2020/9/9 2:11
 */
@Entity
@Table(name = "dynamic")
public class Dynamic {

    /**
     * 动态ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dynamic_id")
    private Long  dynamicId;

    /**
     * URL 文章名称可自定义 格式 https://liguohao.cn/dynamics/2020/11/25/customer
     * 储存形式 dynamics/2020/11/25/customer
     */
    private String url;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 内容
     */
    @Column(name = "content",columnDefinition="mediumtext")
    private String content;

    /**
     * 状态 0-已删除 1-正常
     */
    @Column(name = "status")
    private Integer status;

    public Long getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(Long dynamicId) {
        this.dynamicId = dynamicId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
