package cn.liguohao.ikaros.config;

import cn.liguohao.ikaros.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**应用服务配置
 * 获取URL等
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/1/15
 */

@Component
public class ServerConfig implements ApplicationListener<WebServerInitializedEvent> {

    private int serverPort;

    @Value("${ikaros.domain}")
    private String ikarosDomain;

    /**
     * 配置获取url
     * @return url
     * @example http://localhost:8888/
     */
    public String getUrl() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        // 条件是当自定义域名不为空
        if(!StringUtils.isEmpty(ikarosDomain)) {
            return "http://"+ikarosDomain;
        }else {
            return "http://"+address.getHostAddress() +":"+this.serverPort;
        }
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.serverPort = event.getWebServer().getPort();
    }
}
