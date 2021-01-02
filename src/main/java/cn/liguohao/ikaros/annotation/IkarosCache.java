package cn.liguohao.ikaros.annotation;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.annotation.*;

/**<p>伊卡洛斯缓存注解</p>
 * <p>只能用在方法上, 服务层正常返回数据库对象，需要缓存则在对应的方法加上@IkarosCache注解</p>
 *
 * <p>具体执行的逻辑如下： </p>
 * <pre>
 * // 特定的唯一的缓存键 执行的方法全路径名+查询参数JSON字符串
 * String cacheKey = new StringBuffer()
 *         .append(joinPoint.getTarget().getClass().getName()+"."+joinPoint.getSignature().getName())
 *         .append("==>"+JSON.toJSONString(args)).toString();
 * Object result = null;
 * if(!cacheStore.containsKey(cacheKey)){ //如缓存中没有
 *     result = joinPoint.proceed(); //执行切点 获取数据库中对应的信息
 *     cacheStore.set(cacheKey,result); //将从数据库查到的信息添加到缓存 数据库中未查到缓存中对应的值为null
 * }
 * // 返回缓存中对应的值
 * return  cacheStore.get(cacheKey);
 * </pre>
 * <p>需要注意的是：此注解加在服务层实现类的调用方法上是无效的，切面切不到</p>
 * <p>如一个服务层实现类中的[A]方法调用的[B]方法, 此时缓存注解应该加在[A]方法上，注解加在[B]方法上无效。可参考[UserServiceImpl]中的[checkToken][A]方法和[findTokenByUuid][B]方法</p>
 *
 * @author liguohao_cn
 * @version  2021/1/2
 * @see cn.liguohao.ikaros.aspect.IkarosCacheAspect#doAround(ProceedingJoinPoint)
 */
@Documented
@Inherited
@Target({ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface IkarosCache {
    String value() default "";
}
