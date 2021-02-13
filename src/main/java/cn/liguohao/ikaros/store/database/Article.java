package cn.liguohao.ikaros.store.database;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**专栏(文章)
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/2/13
 */
@Entity
@Table(name = "article")
@EntityListeners(AuditingEntityListener.class)
public class Article {

    /**
     * 文章ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "article_id")
    private Long articleId;

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
    @Column(columnDefinition="mediumtext")
    private String content;

    /**
     * 作者
     */
    private String author;

    /**
     * 状态 0-已删除 1-正常
     */
    private Integer status;

    /**
     * 发布状态 0-草稿(下架) 1-已发布(上架)
     */
    @Column(name = "is_publish")
    private Integer isPublish;

    /**
     * 是否置顶  0-未置顶 1-置顶 有且只有一篇文章置顶
     */
    private Integer top;

    public Long getArticleId() {
        return articleId;
    }

    public Article setArticleId(Long articleId) {
        this.articleId = articleId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Article setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Article setDescription(String description) {
        this.description = description;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Article setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Article setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Article setContent(String content) {
        this.content = content;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Article setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Article setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getIsPublish() {
        return isPublish;
    }

    public Article setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
        return this;
    }

    public Integer getTop() {
        return top;
    }

    public Article setTop(Integer top) {
        this.top = top;
        return this;
    }
}
