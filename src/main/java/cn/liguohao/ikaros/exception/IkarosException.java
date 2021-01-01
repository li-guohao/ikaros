package cn.liguohao.ikaros.exception;

/**
 * @author liguohao_cn
 * @date 2021/1/1
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

    public static IkarosException build(){
        return new IkarosException();
    }

    public static IkarosException build(String message){
        return new IkarosException(message);
    }
    public static IkarosException build(String message, Throwable cause){
        return new IkarosException(message, cause);
    }
}
