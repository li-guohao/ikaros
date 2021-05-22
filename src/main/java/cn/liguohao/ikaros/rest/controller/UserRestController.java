package cn.liguohao.ikaros.rest.controller;

import cn.liguohao.ikaros.dto.UserDTO;
import cn.liguohao.ikaros.service.UserService;
import cn.liguohao.ikaros.vo.Result;
import cn.liguohao.ikaros.vo.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**用户
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/1/1
 */
@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO){
        return Result.build().setDSM(userService.login(userDTO),Status.success,"登录成功",Status.unauthorized,"登录失败");
    }

}
