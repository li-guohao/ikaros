package cn.liguohao.ikaros.aspect;

import cn.liguohao.ikaros.annotation.IkarosCache;
import cn.liguohao.ikaros.dto.UserDTO;
import cn.liguohao.ikaros.store.cache.CacheStore;
import cn.liguohao.ikaros.util.MD5Utils;
import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**注解[IkraosCache]切面 用于实现此注解功能
 * @author liguohao_cn
 * @since 2021/1/2
 * @see IkarosCache
 */
@Aspect
@Component
public class IkarosUpdateCacheAspect {

    private Logger logger = LoggerFactory.getLogger(IkarosUpdateCacheAspect.class);

    @Autowired
    private CacheStore cacheStore;

    @Pointcut("@annotation(cn.liguohao.ikaros.annotation.IkarosUpdateCache)")
    public void updateCache(){}

    @After("updateCache()")
    public void after(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取对应实现类的信息 ，得到所有此实现类在缓存中的key前缀 执行的方法全路径名
        String cacheKeyprofix = new StringBuffer()
                .append(joinPoint.getTarget().getClass().getName()+"."+joinPoint.getSignature().getName())
                .toString();
        // 移除所有包含此前缀的缓存
        cacheStore.removeByKeyprofix(cacheKeyprofix);
    }

}
