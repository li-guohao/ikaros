package cn.liguohao.ikaros.exception;

/**数据不存在异常
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/1/15
 */
public class IkarosNotFoundException extends IkarosException {
    public IkarosNotFoundException() {
        super();
    }

    public IkarosNotFoundException(String message) {
        super(message);
    }

    public IkarosNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
