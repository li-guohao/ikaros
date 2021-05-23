package cn.liguohao.ikaros.service;

import static org.junit.jupiter.api.Assertions.*;

import cn.liguohao.ikaros.enums.db.config.AliyunOSSKey;
import cn.liguohao.ikaros.enums.db.config.ConfigType;
import cn.liguohao.ikaros.store.database.Config;
import cn.liguohao.ikaros.util.StringUtils;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;

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
        configService.save(Config.build().setType("").setKey("test").setDescription("测试").setUpdateTime(LocalDateTime.now()));
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

    /**
     * 读取指定目录文件，保存到数据库对应的配置项
     */
    @Test
    public void readProperties2initItem() throws IOException {

        FileInputStream fileInputStream = new FileInputStream("D:\\云服务\\阿里云\\OSS相关信息.properties");
        Properties properties = new Properties();
        properties.load(fileInputStream);

        assertFalse(StringUtils.isEmpty(properties.get(AliyunOSSKey.BUCKET_NAME.name())));

        configService.readProperties2initItem(ConfigType.ALIYUN_OSS, properties);

        fileInputStream.close();
    }

}
