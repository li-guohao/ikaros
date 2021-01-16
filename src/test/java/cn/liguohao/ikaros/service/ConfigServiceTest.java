package cn.liguohao.ikaros.service;

import cn.liguohao.ikaros.store.database.Config;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

/**系统配置服务层测试
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/1/16
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ConfigServiceTest {

    @Autowired
    private ConfigService configService;


    @Test
    public void save(){
        configService.findAll().forEach(e ->{
            System.out.println(JSON.toJSON(e));
        });
        configService.save(Config.build().setType("").setName("test").setDescription("测试").setUpdateTime(LocalDateTime.now()));
        System.out.println("--------------------");
        configService.findAll().forEach(e ->{
            System.out.println(JSON.toJSON(e));
        });
    }

    @Test
    public void findAll(){
        configService.findAll();
        configService.findAll();
    }

}
