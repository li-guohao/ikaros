package cn.liguohao.ikaros.constant;

/**
 * @author liguohao_cn
 * @since 2021/1/1
 */
public class UserDTOType {
    private UserDTOType(){}

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
