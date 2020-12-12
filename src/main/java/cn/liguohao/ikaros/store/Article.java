package cn.liguohao.ikaros.store;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description TODO 文章
 * @Author liguohao
 * @Date 2020/11/25 19:27
 */
@Entity
@Table(name = "article")
public class Article {

    /**
     * 文章ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "acticle_id")
    private Long  articleId;

    /**
     * 自定义URL 格式 https://liguohao.cn/articles/2020/11/25/customer
     */
    @Column(name = "url")
    private String URL;

    /**
     * 标题
     */
    private String title;

    /**
     * 文章简述
     */
    private String description;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 内容
     */
    @Column(name = "content",columnDefinition="mediumtext")
    private String content;

    /**
     * 状态 0-已删除 1-正常
     */
    private Integer status;

    /**
     * 与用户的多对一，文章表负责维护外键
     */
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
