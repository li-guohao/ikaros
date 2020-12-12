package cn.liguohao.ikaros.response;

/**
 * @ClassName: Meta
 * @Description: 响应描述
 * @author: liguohao
 * @date: 2020-7-18 1:06:52
 */
public class Meta {

    /**
     * 响应状态，详情请参考接口文档
     */
    private Status status;

    /**
     * 响应信息
     */
    private String msg;

    /**
     * 响应信息详情
     */
    private String detail;

    public Meta(Status status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Meta(Status status, String msg, String detail) {
        this.status = status;
        this.msg = msg;
        this.detail = detail;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
