package cn.liguohao.ikaros.handler;

import cn.liguohao.ikaros.exception.IkarosException;
import cn.liguohao.ikaros.exception.IkarosNotFoundException;
import cn.liguohao.ikaros.exception.UserOperateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

/**
 * [Controller]层 异常处理类
 *
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/1/1
 */
@ControllerAdvice(basePackages = "cn.liguohao.ikaros.controller")
public class ControllerExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * 返回给模板引擎错误信息的变量名称
     */
    private final String ERROR_MSG_KEY = "msg";

    /**
     * 错误页面的位置
     */
    private final String ERROR_PAGE_PLACE = "500";

    @ExceptionHandler(IkarosNotFoundException.class)
    public String ikarosNotFoundException(IkarosNotFoundException ikarosNotFoundException, Model model) {
        String detail = "[伊卡洛斯]数据不存在异常 ==> " + ikarosNotFoundException.getClass().getName() + "==>" + ikarosNotFoundException.getMessage();
        logger.error("[伊卡洛斯]数据不存在异常 ==> ", ikarosNotFoundException);
        model.addAttribute(ERROR_MSG_KEY, detail);
        return ERROR_PAGE_PLACE;
    }


    @ExceptionHandler(UserOperateException.class)
    public String userOperateException(UserOperateException userOperateException, Model model) {
        String detail = "[伊卡洛斯]用户请求异常 ==> " + userOperateException.getClass().getName() + "==>" + userOperateException.getMessage();
        logger.error("[伊卡洛斯]用户请求异常 ==> ", userOperateException);
        model.addAttribute(ERROR_MSG_KEY, detail);
        return ERROR_PAGE_PLACE;
    }


    @ExceptionHandler(IkarosException.class)
    public String ikarosException(IkarosException ikarosException, Model model) {
        String detail = "[伊卡洛斯]出现了异常情况 ==> " + ikarosException.getClass().getName() + "==>" + ikarosException.getMessage();
        logger.error("[伊卡洛斯]出现了异常情况 ==> ", ikarosException);
        model.addAttribute(ERROR_MSG_KEY, detail);
        return ERROR_PAGE_PLACE;
    }


    @ExceptionHandler(IOException.class)
    public String exception(IOException ioException, Model model) {
        String detail = "[伊卡洛斯]IO操作出现了异常情况 ==> " + ioException.getClass().getName() + "==>" + ioException.getMessage();
        logger.error("[伊卡洛斯]IO操作出现了异常情况 ==> ", ioException);
        model.addAttribute(ERROR_MSG_KEY, detail);
        return ERROR_PAGE_PLACE;
    }

    @ExceptionHandler(Exception.class)
    public String exception(Exception exception, Model model) {
        String detail = "[SpringBoot]出现了异常情况 ==> " + exception.getClass().getName() + "==>" + exception.getMessage();
        logger.error("[SpringBoot]出现了异常情况 ==> ", exception);
        model.addAttribute(ERROR_MSG_KEY, detail);
        return ERROR_PAGE_PLACE;
    }
}
