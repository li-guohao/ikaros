package cn.liguohao.ikaros.lifecycle;

import cn.liguohao.ikaros.dao.SettingDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description TODO 应用启动之后
 * @Auther: liguohao
 * @Date: 2020/11/28 15:19
 */
@Component
public class AfterAppStart implements ApplicationContextAware{

    private Log log = LogFactory.getLog(AfterAppStart.class);


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("After AppStart ==> Ikaros Appliction");



    }
}
