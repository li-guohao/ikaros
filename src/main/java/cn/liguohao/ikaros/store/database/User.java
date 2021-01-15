package cn.liguohao.ikaros.store.database;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/**用户
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2020/12/29
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
     * 手机号
     */
    @Column(name = "phone_number")
    private String phoneNumber;

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
     * 邮箱
     */
    private String email;

    /**
     * 个人简介
     */
    private String description;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private LocalDateTime createDate;

    /**
     * 登录令牌
     */
    private String token;

    public static User build(){return new User();}

    public Long getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public String getWebsite() {
        return website;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public User setUuid(Long uuid) {
        this.uuid = uuid;
        return this;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
        return this;
    }

    public User setWebsite(String website) {
        this.website = website;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setDescription(String description) {
        this.description = description;
        return this;
    }

    public User setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getToken() {
        return token;
    }

    public User setToken(String token) {
        this.token = token;
        return this;
    }
}
