package cn.liguohao.ikaros.store.diskfile.builder;

import cn.liguohao.ikaros.enums.DiskFilePlaceEnum;
import cn.liguohao.ikaros.store.diskfile.handler.DiskFileHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**动态指定磁盘文件处理器构建类
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2020/12/30
 */
@Component
public class DiskFileHandlerBuilder  implements InitializingBean, ApplicationContextAware {
    private Map<String, DiskFileHandler> diskFileHandlerImplMap = new HashMap<>();
    private ApplicationContext applicationContext;

    /**
     * 根据储存类型构建具体进行工作的磁盘文件处理器
     * @param diskFilePlaceEnum 储存商指定
     * @return
     */
    public DiskFileHandler builderDefiniteDiskFileHandler(DiskFilePlaceEnum diskFilePlaceEnum){
        return diskFileHandlerImplMap.get(diskFilePlaceEnum.getSimpleClassName());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.diskFileHandlerImplMap = applicationContext.getBeansOfType(DiskFileHandler.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
