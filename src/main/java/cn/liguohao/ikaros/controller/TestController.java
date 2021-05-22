package cn.liguohao.ikaros.controller;

import cn.liguohao.ikaros.exception.IkarosException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 测试用
 *
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/5/22
 */
@Controller
public class TestController {

    @GetMapping("/ex")
    public String exception(){
        throw new IkarosException(this.getClass().getName() + "测试全局异常");
    }
}
