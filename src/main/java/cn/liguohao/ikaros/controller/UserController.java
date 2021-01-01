package cn.liguohao.ikaros.controller;

import cn.liguohao.ikaros.dto.UserDTO;
import cn.liguohao.ikaros.service.UserService;
import cn.liguohao.ikaros.store.database.User;
import cn.liguohao.ikaros.vo.Result;
import cn.liguohao.ikaros.vo.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**用户
 * @author liguohao_cn
 * @date 2021/1/1
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO){
        User user = userService.login(userDTO);
        if (user == null) {
            return Result.build().setStatus(Status.forbidden).setMessage("登录失败");
        }else {
            return Result.build().setStatus(Status.success).setMessage("登录成功");
        }
    }

}
