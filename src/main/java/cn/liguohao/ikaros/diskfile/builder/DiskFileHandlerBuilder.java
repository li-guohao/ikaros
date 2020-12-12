package cn.liguohao.ikaros.diskfile.builder;

import cn.liguohao.ikaros.constant.DiskFilePlace;
import cn.liguohao.ikaros.diskfile.handler.DiskFileHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**动态指定磁盘文件处理器构建类
 * @Auther: liguohao
 * @Date: 2020/11/27 18:13
 */
@Component
public class DiskFileHandlerBuilder implements InitializingBean, ApplicationContextAware {

    private Map<String, DiskFileHandler> diskFileHandlerImplMap = new HashMap<>();
    private ApplicationContext applicationContext;

    /**
     * 根据储存类型构建具体进行工作的磁盘文件处理器
     * @param diskFilePlace 储存商指定
     * @return
     */
    public DiskFileHandler builderDefiniteDiskFileHandler(DiskFilePlace diskFilePlace){
        return diskFileHandlerImplMap.get(diskFilePlace.getSimpleClassName());
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
