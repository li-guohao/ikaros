package cn.liguohao.ikaros;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.logging.Logger;

/**
 * @Description TODO 插件测试
 * @Author liguohao
 * @Date 2020/9/9 22:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Plugln {

    // 日志
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Test
    public void pluglnTest(){
        logger.info("测试插件");
    }
}
