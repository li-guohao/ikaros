package cn.liguohao.ikaros.aspect;

import cn.liguohao.ikaros.annotation.IkarosCache;
import cn.liguohao.ikaros.dto.UserDTO;
import cn.liguohao.ikaros.store.cache.CacheStore;
import cn.liguohao.ikaros.util.MD5Utils;
import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.SourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

/**注解[IkraosCache]切面 用于实现此注解功能
 * @author liguohao_cn
 * @date 2021/1/2
 * @see cn.liguohao.ikaros.annotation.IkarosCache
 */
@Aspect
@Component
public class IkarosCacheAspect {

    private Logger logger = LoggerFactory.getLogger(IkarosCacheAspect.class);

    @Autowired
    private CacheStore cacheStore;

    @Pointcut("@annotation(cn.liguohao.ikaros.annotation.IkarosCache)")
    public void cache(){}

    @Around("cache()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取参数
        Object[] args = joinPoint.getArgs();
        // 参数预处理
        for(int i=0;i<args.length;i++){
            // 如果参数是与用户有关的如密码之类的，为了安全考虑，在执行方法前进行MD5加密
            if(args[i] instanceof UserDTO){
                UserDTO userDTO = (UserDTO) args[i];
                if(userDTO.getPassword() != null) args[i] = userDTO.setPassword(MD5Utils.md5(userDTO.getPassword()));
            }
        }
        // 特定的唯一的缓存键 执行的方法全路径名+查询参数JSON字符串
        String cacheKey = new StringBuffer()
                .append(joinPoint.getTarget().getClass().getName()+"."+joinPoint.getSignature().getName())
                .append("==>"+JSON.toJSONString(args)).toString();
        Object result = null;
        if(!cacheStore.containsKey(cacheKey)){ //如缓存中没有
            result = joinPoint.proceed(); //执行切点 获取数据库中对应的信息
            cacheStore.set(cacheKey,result); //将从数据库查到的信息添加到缓存 数据库中未查到缓存中对应的值为null
        }
        // 返回缓存中对应的值
        return  cacheStore.get(cacheKey);
    }

}
