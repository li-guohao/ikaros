package cn.liguohao.ikaros.controller;

import cn.liguohao.ikaros.vo.Result;
import cn.liguohao.ikaros.vo.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**首页
 * @author liguohao_cn
 * @date 2020/12/31
 */
@Controller
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/")
    public String index(){
        return "index";
    }


    /**
     * 应用初始化
     */
    @GetMapping("/init")
    public void init(HttpServletResponse response) throws IOException{

    }

    /**
     * 应用安装
     */
    @GetMapping("/install")
    public void install(HttpServletResponse response) throws IOException {
        // 判断是否已经安装

        logger.info("安装成功");
        response.sendRedirect("/");
    }


}
