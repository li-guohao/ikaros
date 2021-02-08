package cn.liguohao.ikaros.vo;

import java.util.List;

/**返回结果类
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2020/12/31
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

    public static Result build(){return new Result();}

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    /**
     * @see #setDSM(Object, Status, String, Status, String)
     */
    public Result<T> setDSM(T data,String successMsg,String faildMsg){
        return setDSM(data,Status.success,successMsg,Status.notFound,faildMsg);
    }

    /**
     * <p>根据所设置的数据 动态设置状态和消息</p>
     * <p>DSM: Date Status Message</p>
     * @param data 待设置的数据
     * @param successStatus 操作成功返回的状态
     * @param successMsg 操作成功返回的消息
     * @param faildStatus 操作失败返回的状态
     * @param faildMsg 操作失败返回的消息
     * @return 设置好数据和状态和消息的结果对象
     */
    public Result<T> setDSM(T data,Status successStatus,String successMsg,Status faildStatus,String faildMsg){
        this.data = data;
        // 如是[List]集合数据
        boolean isEmptyListData = false;
        if(data instanceof List){
            List dataList = (List) data;
            if(dataList.isEmpty()) {isEmptyListData = true;}
        }
        // 如是[分页]数据
        boolean isEmptyPagingData = false;
        if(data instanceof PagingData){
            PagingData pagingData = (PagingData) data;
            if(pagingData.isEmpty()) {isEmptyPagingData = true;}
        }
        // 动态判断数据,返回对应的状态和
        if(data==null || isEmptyListData || isEmptyPagingData) {
            this.setStatus(faildStatus).setMessage(faildMsg);
        }else {
            this.setStatus(successStatus).setMessage(successMsg);
        }
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
