package cn.liguohao.ikaros.controller;

import cn.liguohao.ikaros.exception.IkarosException;
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

    @GetMapping("/")
    public String index(){
        // 判断是否已经初始化
        if(isInited()) { // 已经初始化返回首页
            return "index";
        }else { // 未初始化则重定向到初始化
            return " redirect:/application/init";
        }
    }

    @GetMapping("/admin")
    public String admin(){
        return "redirect:/admin/index.html";
    }

    @GetMapping("/ex")
    public String exception(){
        throw new IkarosException("测试全局异常");
    }

    /**
     * 应用初始化
     */
    @GetMapping("/application/init")
    public String applicationInit() throws IOException{
        // 判断是否已经初始化 已经初始化不进行任何操作 这里又判断一遍是为了防止误操作
        if(!isInited()) { // 未初始化进行初始化操作
            if(!init()) throw new IOException("应用初始化失败"); // 初始化失败则抛出异常
        }
        // 操作完毕 重定向到首页
        return "redirect:/";
    }

    /**
     * 应用初始化操作
     * @return true-初始化成功 false-初始化失败
     */
    private boolean init(){
        return true;
    }

    /**
     * 判断应用是否已经初始化
     * @return true-已经初始化 false-未初始化
     */
    private boolean isInited(){
        return true;
    }


}
