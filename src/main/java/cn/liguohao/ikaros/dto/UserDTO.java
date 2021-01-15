package cn.liguohao.ikaros.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

/**用户 数据传输对象
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/1/1
 */
public class UserDTO {


    /**
     * 用户ID
     */
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
    private String phoneNumber;
    /**
     * 验证码
     */
    private String captcha;


    /**
     * 邮箱
     */
    private String email;


    /**
     * 登录令牌
     */
    private String token;
    /**
     * <p>类型</p>
     * <ul>
     *     <li>token-通过用户ID</li>
     *     <li>username_password-用户名密码</li>
     *     <li>email_password-邮箱密码</li>
     *     <li>phone_number_password-手机号密码</li>
     *     <li>phoneNumber_captcha-手机号验证码</li>
     * </ul>
     * @see Type
     */
    @NotBlank
    private String type;

    public UserDTO() {
    }

    public static UserDTO build(){return new UserDTO();}

    public Long getUuid() {
        return uuid;
    }

    public UserDTO setUuid(Long uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getToken() {
        return token;
    }

    public UserDTO setToken(String token) {
        this.token = token;
        return this;
    }

    public String getCaptcha() {
        return captcha;
    }

    public UserDTO setCaptcha(String captcha) {
        this.captcha = captcha;
        return this;
    }

    public String getType() {
        return type;
    }

    public UserDTO setType(String type) {
        this.type = type;
        return this;
    }

    /**
     * <p>定义UserDTO类型字段常量</p>
     * <p>调用方式 UserDTO.Type.Token</p>
     */
    public static class Type {
        private Type(){}

        /**
         * token-通过用户ID
         */
        public static final String TOKEN = "token";

        /**
         * username_password-用户名密码
         */
        public static final String USERNAME_PASSWORD = "username_password";

        /**
         * email_password-邮箱密码
         */
        public static final String EMAil_PASSWORD = "email_password";

        /**
         * phone_number_password-手机号密码
         */
        public static final String PHONE_NUMBER_PASSWORD = "phone_number_password";

        /**
         * phone_number_captcha-手机号验证码
         */
        public static final String PNONE_NUMBER_CAPTCHA = "phone_number_captcha";
    }
}
