package cn.liguohao.ikaros.handler;

import cn.liguohao.ikaros.exception.IkarosException;
import cn.liguohao.ikaros.exception.IkarosNotFoundException;
import cn.liguohao.ikaros.exception.UserOperateException;
import cn.liguohao.ikaros.service.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ConfigService configService;

    @ExceptionHandler(IkarosNotFoundException.class)
    public String ikarosNotFoundException(IkarosNotFoundException ikarosNotFoundException, Model model) {
        logger.error("[伊卡洛斯]数据不存在异常 ==> ", ikarosNotFoundException);
        model.addAttribute(ERROR_MSG_KEY, ikarosNotFoundException);
        return configService.getThemeName() + "/" + ERROR_PAGE_PLACE;
    }


    @ExceptionHandler(UserOperateException.class)
    public String userOperateException(UserOperateException userOperateException, Model model) {
        logger.error("[伊卡洛斯]用户请求异常 ==> ", userOperateException);
        model.addAttribute(ERROR_MSG_KEY, userOperateException);
        return configService.getThemeName() + "/" + ERROR_PAGE_PLACE;
    }


    @ExceptionHandler(IkarosException.class)
    public String ikarosException(IkarosException ikarosException, Model model) {
        logger.error("[伊卡洛斯]出现了异常情况 ==> ", ikarosException);
        model.addAttribute(ERROR_MSG_KEY, ikarosException);
        return configService.getThemeName() + "/" + ERROR_PAGE_PLACE;
    }


    @ExceptionHandler(IOException.class)
    public String exception(IOException ioException, Model model) {
        logger.error("[伊卡洛斯]IO操作出现了异常情况 ==> ", ioException);
        model.addAttribute(ERROR_MSG_KEY, ioException);
        return configService.getThemeName() + "/" + ERROR_PAGE_PLACE;
    }

    @ExceptionHandler(Exception.class)
    public String exception(Exception exception, Model model) {
        logger.error("[SpringBoot]出现了异常情况 ==> ", exception);
        model.addAttribute(ERROR_MSG_KEY, exception);
        return configService.getThemeName() + "/" + ERROR_PAGE_PLACE;
    }
}
