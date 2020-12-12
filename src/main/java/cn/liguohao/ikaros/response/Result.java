package cn.liguohao.ikaros.response;

/**
 * @ClassName: Result
 * @Description: 响应结果
 * @author: liguohao
 * @date: 2020-7-18 1:07:07
 */
public class Result<T> {

    /**
     * 数据
     */
    private T data;

    /**
     * 状态
     */
    private Status status;

    /**
     * 主要信息 给用户看
     */
    private String message;

    /**
     * 详细信息 给程序员看
     */
    private String detail;

    /**
     * 无参构造，链式设置返回值
     */
    public Result() {
    }


    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public Result<T> setStatus(Status status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getDetail() {
        return detail;
    }

    public Result<T> setDetail(String detail) {
        this.detail = detail;
        return this;
    }
}
