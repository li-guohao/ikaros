package cn.liguohao.ikaros.rest.controller;

import cn.liguohao.ikaros.exception.IkarosException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试用
 *
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/5/22
 *
 */
@RestController
public class TestRestController {

    @GetMapping("/ex")
    public String exception(){
        throw new IkarosException(this.getClass().getName() + "测试全局异常");
    }
}
