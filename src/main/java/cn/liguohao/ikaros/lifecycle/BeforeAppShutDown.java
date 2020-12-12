package cn.liguohao.ikaros.lifecycle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 * @Description TODO 应用关闭之前
 * @Auther: liguohao
 * @Date: 2020/11/28 15:12
 */
@Component
public class BeforeAppShutDown implements DisposableBean {

    private Log log = LogFactory.getLog(BeforeAppShutDown.class);

    @Override
    public void destroy() throws Exception {
        log.info("BeforeAppShutDown destroy");

    }
}
