package cn.liguohao.ikaros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**启动类
 * @author liguohao_cn
 * @since 2020/9/9 1:51
 */
@SpringBootApplication
@EnableScheduling
public class IkarosApplication {
    public static void main(String[] args) {
        SpringApplication.run(IkarosApplication.class, args);
    }
}
