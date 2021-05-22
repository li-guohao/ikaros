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
        String detail = "[伊卡洛斯]数据不存在异常 ==> " + ikarosNotFoundException.getClass().getName() + "==>" + ikarosNotFoundException.getMessage();
        logger.error("[伊卡洛斯]数据不存在异常 ==> " ,ikarosNotFoundException);
        return Result.build()
                .setStatus(Status.serverError)
                .setMessage(ikarosNotFoundException.getMessage())
                .setDetail(detail)
                ;//END
    }


    @ExceptionHandler(UserOperateException.class)
    public Result userOperateException(UserOperateException userOperateException) {
        String detail = "[伊卡洛斯]用户请求异常 ==> " + userOperateException.getClass().getName() + "==>" + userOperateException.getMessage();
        logger.error("[伊卡洛斯]用户请求异常 ==> ", userOperateException);
        return Result.build()
                .setStatus(Status.serverError)
                .setMessage(userOperateException.getMessage())
                .setDetail(detail)
                ;//END
    }


    @ExceptionHandler(IkarosException.class)
    public Result ikarosException(IkarosException ikarosException) {
        String detail = "[伊卡洛斯]出现了异常情况 ==> " + ikarosException.getClass().getName() + "==>" + ikarosException.getMessage();
        logger.error("[伊卡洛斯]出现了异常情况 ==> ", ikarosException);
        return Result.build()
                .setStatus(Status.serverError)
                .setMessage(ikarosException.getMessage())
                .setDetail(detail)
                ;//END
    }


    @ExceptionHandler(IOException.class)
    public Result exception(IOException ioException) {
        String detail = "[伊卡洛斯]IO操作出现了异常情况 ==> " + ioException.getClass().getName() + "==>" + ioException.getMessage();
        logger.error("[伊卡洛斯]IO操作出现了异常情况 ==> ", ioException);
        return Result.build()
                .setStatus(Status.serverError)
                .setMessage(ioException.getMessage())
                .setDetail(detail)
                ;//END
    }

    @ExceptionHandler(Exception.class)
    public Result exception(Exception exception) {
        String detail = "[SpringBoot]出现了异常情况 ==> " + exception.getClass().getName() + "==>" + exception.getMessage();
        logger.error("[SpringBoot]出现了异常情况 ==> ", exception);
        return Result.build()
                .setStatus(Status.serverError)
                .setMessage(exception.getMessage())
                .setDetail(detail)
                ;//END
    }
}
