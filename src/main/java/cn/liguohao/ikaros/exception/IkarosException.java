package cn.liguohao.ikaros.exception;

/**
 * @Description TODO ikaros基础异常
 * @Author liguohao
 * @Date 2020/11/26 13:19
 */
public class IkarosException extends RuntimeException{
    public IkarosException() {
    }

    public IkarosException(String message) {
        super(message);
    }

    public IkarosException(String message, Throwable cause) {
        super(message, cause);
    }

    public IkarosException(Throwable cause) {
        super(cause);
    }

    public IkarosException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
