package cn.liguohao.ikaros.annotation;

import java.lang.annotation.*;

/**<p>伊卡洛斯缓存注解</p>
 * <p>只能用在方法上, 服务层正常返回数据库对象，需要缓存则在对应的方法加上@IkarosCache注解</p>
 * <p>实际效果等同于下方: </p>
 * <pre>
 * public List<DBFile> findList(Example<DBFile> dbFileExample) {
 *     String cacheKey = DBFile.class.getName() + ":findList:"+ JSON.toJSONString(dbFileExample);//特定的缓存键
 *     if(!cacheStore.containsKey(cacheKey)){ //如缓存中没有
 *         Optional<DBFile> dbFileOptional = dbFileDao.findOne(dbFileExample); //这里是查询数据库的对应代码
 *         cacheStore.set(cacheKey,dbFileOptional.isPresent()?dbFileOptional.get():null); //查询结果设置回缓存中
 *     }
 *     return cacheStore.get(cacheKey) instanceof List? (List) cacheStore.get(cacheKey) :null;//返回缓存中的结果
 * }
 * <pre>
 * @author liguohao_cn
 * @date 2021/1/2
 */
@Documented
@Inherited
@Target({ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface IkarosCache {
    String value() default "";
}
