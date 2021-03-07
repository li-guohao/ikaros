package cn.liguohao.ikaros.dao;

import cn.liguohao.ikaros.store.database.User;
import cn.liguohao.ikaros.util.MD5Utils;
import cn.liguohao.ikaros.util.Md5UtilsTest;
import cn.liguohao.ikaros.util.TokenUtils;
import com.alibaba.fastjson.JSON;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**用户Dao层测试
 * @author liguohao_cn
 * @date 2021/1/1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void findAll(){
        System.out.println(
                JSON.toJSON(userDao.findAll())
        );
    }


}
