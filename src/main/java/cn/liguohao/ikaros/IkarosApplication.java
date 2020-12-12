package cn.liguohao.ikaros;

import cn.liguohao.ikaros.store.User;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**启动类
 * @Author liguohao
 * @Date 2020/9/9 1:51
 */
@SpringBootApplication
public class IkarosApplication {
    public static void main(String[] args) {
        SpringApplication.run(IkarosApplication.class, args);
    }
}
