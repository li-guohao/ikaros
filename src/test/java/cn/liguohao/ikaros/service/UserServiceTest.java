package cn.liguohao.ikaros.service;

import cn.liguohao.ikaros.dto.UserDTO;
import cn.liguohao.ikaros.store.database.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liguohao_cn
 * @date 2021/1/1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void login(){
        User user = userService.login(UserDTO.build()
                .setType(UserDTO.Type.USERNAME_PASSWORD)
                .setUsername("test").setPassword("123456")
        );
        System.out.println(user.getToken());
    }

    @Test
    public void checkToken(){
        System.out.println(userService.checkToken(UserDTO.build().setUuid(1L).setToken("01ACE8A140EC482FA59AEE78BE3908D0A2A96DC4C66B4F84A412BD4286C0A31F")));
    }

    @Test
    public void findTokenByUuid(){
        System.out.println(userService.findTokenByUuid(null));
    }

}
