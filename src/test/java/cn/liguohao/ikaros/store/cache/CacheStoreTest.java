package cn.liguohao.ikaros.store.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**缓存测试
 * @author liguohao_cn
 * @date 2020/12/31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheStoreTest {

    @Autowired
    private CacheStore<String> cacheStore;

    /**
     * 测试添加缓存数据
     */
    @Test
    public void putCacheDate(){
        cacheStore.set("test001","001");
    }

    /**
     * 获取缓存数据
     */
    @Test
    public void getCacheDate(){
        cacheStore.set("test002","002");
        System.out.println(cacheStore.get("test002"));
    }

    /**
     * <p>测试缓存失效情况</p>
     * <p>注意线程睡眠时间要超过缓存清理定时器的时间间隔 就是要让定时器在下次查询前清理下失效缓存</p>
     * <p>定时器时间间隔设置在cn.liguohao.ikaros.store.cache.MemoryCacheStore中的clearExpireDate方法注解值上</p>
     * @see MemoryCacheStore#clearExpireDate()
     */
    @Test
    public void cacheExpireTime() throws InterruptedException {
        cacheStore.set("test003","003",30);
        Thread.sleep(61000);
        System.out.println(cacheStore.get("test003"));
    }

    @Test
    public void clearCaches(){
        cacheStore.set("test004","004");
        cacheStore.set("test005","005");
        cacheStore.set("test006","006");
        cacheStore.set("test007","007");
        cacheStore.clear();
        System.out.println(cacheStore.get("test007"));
    }

}
