package cn.liguohao.ikaros.handler;

import cn.liguohao.ikaros.exception.DataNotExistException;
import cn.liguohao.ikaros.exception.IkarosException;
import cn.liguohao.ikaros.exception.UserOperateException;
import cn.liguohao.ikaros.response.Result;
import cn.liguohao.ikaros.response.Status;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**全局异常处理
 * @Author liguohao
 * @Date 2020/11/26 12:32
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private Log log = LogFactory.getLog(GlobalExceptionHandler.class);

    /**
     * 用户操作异常处理
     * @param request 请求的对象
     * @param userOperateException 用户操作异常
     * @return 用户操作异常处理的响应结果
     */
    @ExceptionHandler(UserOperateException.class)
    public Result userOperateException(HttpServletRequest request, UserOperateException userOperateException){
        String detail = "应用层面逻辑异常-UserOperateException => "+userOperateException.getClass().getName() +"==>"+userOperateException.getMessage();
        log.info(detail);
        userOperateException.printStackTrace();
        return new Result()
                .setStatus(Status.badRequest)
                .setMessage(userOperateException.getMessage())
                .setDetail(detail)
                ;//END
    }

    /**
     * 数据不存在异常处理
     * @param request 请求的对象
     * @param dataNotExistException 数据不存在异常
     * @return 数据不存在异常处理的响应结果
     */
    @ExceptionHandler(DataNotExistException.class)
    public Result dataNotExistException(HttpServletRequest request, DataNotExistException dataNotExistException){
        String detail = "应用层面逻辑异常-DataNotExistException => "+dataNotExistException.getClass().getName() +"==>"+dataNotExistException.getMessage();
        log.info(detail);
        dataNotExistException.printStackTrace();
        return new Result()
                .setStatus(Status.notFound)
                .setMessage(dataNotExistException.getMessage())
                .setDetail(detail)
                ;//END
    }

    /**
     * ikaros应用异常
     * @param request 请求的对象
     * @param ikarosException ikaros应用异常
     * @return ikaros应用异常的响应结果
     */
    @ExceptionHandler(IkarosException.class)
    public Result ikarosException(HttpServletRequest request, IkarosException ikarosException){
        String detail = "应用层面逻辑异常-IkarosException => "+ikarosException.getClass().getName() +"==>"+ikarosException.getMessage();
        log.info(detail);
        ikarosException.printStackTrace();
        return new Result()
                .setStatus(Status.serverError)
                .setMessage(ikarosException.getMessage())
                .setDetail(detail)
                ;//END
    }

    /**
     * 所有的父异常
     * @param request 请求的对象
     * @param exception 异常对象
     * @return 异常的响应结果
     */
    @ExceptionHandler(Exception.class)
    public Result exception(HttpServletRequest request, Exception exception){
        String detail = "框架层面调用异常-Exception => "+exception.getClass().getName() + "==>"+exception.getMessage();
        log.info(detail);
        exception.printStackTrace();
        return new Result()
                .setStatus(Status.serverError)
                .setMessage(exception.getMessage())
                .setDetail(detail)
                ;//END
    }
}
