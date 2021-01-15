package cn.liguohao.ikaros.exception;

/**不合规定的参数
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/1/2
 */
public class IllegalArgumentIkarosException extends IkarosException{

    public IllegalArgumentIkarosException(String message) {
        super(message);
    }

    public IllegalArgumentIkarosException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalArgumentIkarosException() {
    }


}
