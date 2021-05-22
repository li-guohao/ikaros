package cn.liguohao.ikaros.handler;

import cn.liguohao.ikaros.exception.IkarosException;
import cn.liguohao.ikaros.exception.IkarosNotFoundException;
import cn.liguohao.ikaros.exception.UserOperateException;
import cn.liguohao.ikaros.vo.Result;
import cn.liguohao.ikaros.vo.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

/**
 * [RestController]层 异常处理类
 *
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/1/1
 */
@ResponseBody
@RestControllerAdvice(basePackages = "cn.liguohao.ikaros.rest.controller")
public class RestControllerExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(RestControllerExceptionHandler.class);

    @ExceptionHandler(IkarosNotFoundException.class)
    public Result ikarosNotFoundException(IkarosNotFoundException ikarosNotFoundException) {
        logger.error("[伊卡洛斯]数据不存在异常 ==> " ,ikarosNotFoundException);
        return Result.build()
                .setStatus(Status.serverError)
                .setMessage(ikarosNotFoundException.getMessage())
                .setException(ikarosNotFoundException)
                ;//END
    }


    @ExceptionHandler(UserOperateException.class)
    public Result userOperateException(UserOperateException userOperateException) {
        logger.error("[伊卡洛斯]用户请求异常 ==> ", userOperateException);
        return Result.build()
                .setStatus(Status.serverError)
                .setMessage(userOperateException.getMessage())
                .setException(userOperateException)
                ;//END
    }


    @ExceptionHandler(IkarosException.class)
    public Result ikarosException(IkarosException ikarosException) {
        logger.error("[伊卡洛斯]出现了异常情况 ==> ", ikarosException);
        return Result.build()
                .setStatus(Status.serverError)
                .setMessage(ikarosException.getMessage())
                .setException(ikarosException)
                ;//END
    }


    @ExceptionHandler(IOException.class)
    public Result exception(IOException ioException) {
        logger.error("[伊卡洛斯]IO操作出现了异常情况 ==> ", ioException);
        return Result.build()
                .setStatus(Status.serverError)
                .setMessage(ioException.getMessage())
                .setException(ioException)
                ;//END
    }

    @ExceptionHandler(Exception.class)
    public Result exception(Exception exception) {
        logger.error("[SpringBoot]出现了异常情况 ==> ", exception);
        return Result.build()
                .setStatus(Status.serverError)
                .setMessage(exception.getMessage())
                .setException(exception)
                ;//END
    }
}
