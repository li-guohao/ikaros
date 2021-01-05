package cn.liguohao.ikaros.annotation;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.annotation.*;

/**<p>伊卡洛斯更新缓存注解</p>
 * <p>只能用在方法上, 当查询方法加上[@IkarosCache]时，为保证缓存与数据库的数据一致性，需要在修改了数据库数据的方法上加上此注解</p>
 *
 * <p>具体执行的逻辑如下： </p>
 * <pre>
 * // 获取对应实现类的信息 ，得到所有此实现类在缓存中的key前缀 执行的方法全路径名
 * String cacheKeyprofix = new StringBuffer()
 *         .append(joinPoint.getTarget().getClass().getName()+"."+joinPoint.getSignature().getName())
 *         .toString();
 * // 移除所有包含此前缀的缓存
 * cacheStore.removeByKeyprofix(cacheKeyprofix);
 * </pre>
 * <p>需要注意的是：此注解加在服务层实现类的调用方法上是无效的，切面切不到</p>
 * <p>如一个服务层实现类中的[A]方法调用的[B]方法, 此时缓存注解应该加在[A]方法上，注解加在[B]方法上无效。可参考[UserServiceImpl]中的[checkToken][A]方法和[findTokenByUuid][B]方法</p>
 *
 * @author liguohao_cn
 * @since  2021/1/2
 * @see cn.liguohao.ikaros.aspect.IkarosUpdateCacheAspect#around(ProceedingJoinPoint)
 */
@Documented
@Inherited
@Target({ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface IkarosUpdateCache {
    String value() default "";
}
