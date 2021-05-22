package cn.liguohao.ikaros.controller;

import cn.liguohao.ikaros.service.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

/**
 * 首页
 *
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2020/12/31
 */
@Controller
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private ConfigService configService;

    @Value("${spring.profiles.active}")
    private String springProfilesActive;

    @GetMapping("/")
    public String index() {
        // 判断是否已经初始化
        if (configService.isInited()) { // 已经初始化返回首页
            return configService.getThemeName() + "/index";
        } else { // 未初始化则重定向到初始化
            return "redirect:/application/init";
        }
    }

    @GetMapping("/admin")
    public String admin() {
        return "redirect:/admin/index.html";
    }


    /**
     * 应用初始化
     */
    @GetMapping("/application/init")
    public String applicationInit() throws IOException {
        // 判断是否已经初始化 已经初始化不进行任何操作 这里又判断一遍是为了防止误操作
        if (!configService.isInited()) { // 未初始化进行初始化操作
            if (!configService.init()) {
                throw new IOException("应用初始化失败");
            } // 初始化失败则抛出异常
        }
        // 操作完毕 重定向到首页
        return "redirect:/";
    }

}
