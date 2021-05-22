package cn.liguohao.ikaros.resolver;

import cn.liguohao.ikaros.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 自定义错误页面
 *
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/5/22
 */
@Component
public class DefaultErrorViewResolver implements ErrorViewResolver {

    @Autowired
    private ConfigService configService;

    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        String themeName = configService.getThemeName();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setStatus(status);
        modelAndView.addAllObjects(model);

        if (HttpStatus.INTERNAL_SERVER_ERROR == status) {
            modelAndView.setViewName(configService.getThemeName() + "/500");
        } else {
            modelAndView.setViewName(configService.getThemeName() + "/404");
        }

        return modelAndView;
    }
}
