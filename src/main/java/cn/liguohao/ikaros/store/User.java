package cn.liguohao.ikaros.store;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @Description TODO 用户
 * @Author liguohao
 * @Date 2020/11/25 19:19
 */
@Entity
@Table(name = "user")
public class User {

    /**
     * 用户ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uuid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像地址
     */
    @Column(name = "avatar_url")
    private String avatarURL;

    /**
     * 主页站点
     */
    private String website;

    /**
     * token令牌
     */
    private String token;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态 0-已删除 1-正常
     */
    private Integer status;

    /**
     * 个人简介
     */
    private String description;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;


    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
