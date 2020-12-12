package cn.liguohao.ikaros.response;

import com.alibaba.fastjson.serializer.JSONSerializable;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @Description TODO 状态类，封装了状态的定义
 * @Author liguohao
 * @Date 2020/11/25 20:06
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status {

    //2XX 已成功被服务器接收、理解、并接受
    success(" success ", 200, " OK - - > 请求成功 ")
    ,
    created("created", 201, "Created --> 已创建。成功请求并创建了新的资源")
    ,
    resetContent("resetContent",205,"Reset Content --> 已处理请求，无响应结果")
    ,

    //4XX 客户端错误
    badRequest(" badRequest ", 400, " Bad Request - - > 客户端请求的语法错误 ， 服务器无法理解 ")
    ,
    unauthorized(" unauthorized ", 401, " Unauthorized Request - - > 未认证，无权访问")
    ,
    forbidden(" forbidden ", 403, " Forbidden - - > 未授权，无权访问 ")
    ,
    notFound(" notFound ", 404, " Not Found - - > 服务器无法根据客户端的请求找到资源 （ 网页 ） ")
    ,

    //5XX 服务端错误
    serverError(" serverError ", 500, " Internal Server Error - - > 服务器内部出现了逻辑异常 ， 无法处理请求 ")
    ;


    /**
     * 状态的名称
     */
    private String name;
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 状态详情
     */
    private String info;

    private Status() {
    }

    private Status(Integer code, String info) {
        this.code = code;
        this.info = info;
    }

    private Status(String name, Integer code, String info) {
        this.name = name;
        this.code = code;
        this.info = info;
    }

    public Integer getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public String getName() {
        return name;
    }

}
