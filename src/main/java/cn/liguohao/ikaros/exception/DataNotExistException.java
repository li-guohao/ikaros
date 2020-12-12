package cn.liguohao.ikaros.exception;

/**
 * @Description TODO 数据不存在异常
 * @Author liguohao
 * @Date 2020/11/26 9:09
 */
public class DataNotExistException extends IkarosException {
    public DataNotExistException(String message) {
        super(message);
    }
}
