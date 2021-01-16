package cn.liguohao.ikaros.exception;

/**用户操作异常
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/1/16
 */
public class UserOperateException extends IkarosException {
    public UserOperateException() {
        super();
    }

    public UserOperateException(String message) {
        super(message);
    }

    public UserOperateException(String message, Throwable cause) {
        super(message, cause);
    }
}
