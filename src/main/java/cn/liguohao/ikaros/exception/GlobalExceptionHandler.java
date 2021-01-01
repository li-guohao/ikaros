package cn.liguohao.ikaros.exception;

import cn.liguohao.ikaros.vo.Result;
import cn.liguohao.ikaros.vo.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**全局异常处理类
 * @author liguohao_cn
 * @date 2021/1/1
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IkarosException.class)
    public Result ikarosException(HttpServletRequest request, IkarosException ikarosException){
        String detail = "[伊卡洛斯]出现了异常情况 ==> "+ikarosException.getClass().getName()+"==>"+ikarosException.getMessage();
        logger.info(detail);
        ikarosException.printStackTrace();
        return Result.build()
                .setStatus(Status.serverError)
                .setMessage(ikarosException.getMessage())
                .setDetail(detail)
                ;//END
    }

    @ExceptionHandler(Exception.class)
    public Result exception(HttpServletRequest request, Exception exception){
        String detail = "[SpringBoot]出现了异常情况 ==> "+exception.getClass().getName()+"==>"+exception.getMessage();
        logger.info(detail);
        exception.printStackTrace();
        return Result.build()
                .setStatus(Status.serverError)
                .setMessage(exception.getMessage())
                .setDetail(detail)
                ;//END
    }
}
