package cn.liguohao.ikaros.exception;

/**
 * @author liguohao_cn
 * @since 2021/1/1
 */
public class IkarosException extends RuntimeException{

    public IkarosException() {
        super();
    }

    public IkarosException(String message) {
        super(message);
    }

    public IkarosException(String message, Throwable cause) {
        super(message, cause);
    }

}
