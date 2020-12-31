package cn.liguohao.ikaros.store.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**内存中的缓存
 * @author liguohao_cn
 * @date 2020/12/30
 */
@Component
public class MemoryCacheStore<T> implements CacheStore<T>{

    private Logger logger = LoggerFactory.getLogger(MemoryCacheStore.class);

    /**
     * 缓存数据结构
     */
    private  ConcurrentHashMap<String, CacheWrapper<T>> CACHE_CONTAINER = new ConcurrentHashMap<String, CacheWrapper<T>>();

    /**
     * 默认缓存过期时间 5分钟
     */
    private final Integer DEFAULT_EXIRE_TIEM = 5*60;

    @Override
    public void set(String key, T value) {
        set(key,value,DEFAULT_EXIRE_TIEM);
    }

    @Override
    public void set(String key, T value, Integer expireSeconds) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expireTime = now.plusSeconds(expireSeconds);
        logger.info("[伊卡洛斯]往缓存中存入 ===> {}:{}",key,value);
        CACHE_CONTAINER.put(key,new CacheWrapper<T>()
                .setCreateTime(now)
                .setExpireTime(expireTime)
                .setData(value));
    }

    /**
     * 定时任务 每隔1分钟清除失效的缓存
     */
    @Scheduled(fixedRate = 60000)
    private void clearExpireDate(){
        logger.info("[伊卡洛斯]正在清理失效缓存");
        // 待移除的列表
        List<String> keys = new ArrayList<String>();
        CACHE_CONTAINER.forEach((k,v)->{
            if(LocalDateTime.now().isAfter(v.getExpireTime())) //超时缓存失效
                keys.add(k); //将key添加到待移除的列表中
        });
        int count = keys.size();
        if(!keys.isEmpty()) {
            // 根据列表移除失效的缓存元素
            keys.forEach(key -> CACHE_CONTAINER.remove(key));
            // 清空待移除列表
            keys.clear();
        }
        logger.info("[伊卡洛斯]清理失效缓存完毕,本次共清理{}个失效缓存",count);
    }


    @Override
    public T get(String key) {
        return !CACHE_CONTAINER.isEmpty() && CACHE_CONTAINER.containsKey(key)?CACHE_CONTAINER.get(key).getData():null;
    }

    @Override
    public T remove(String key) {
        logger.info("[伊卡洛斯]将要移除key为{}的缓存数据",key);
        return !CACHE_CONTAINER.isEmpty() && CACHE_CONTAINER.containsKey(key)?CACHE_CONTAINER.remove(key).getData():null;
    }

    @Override
    public void clear() {
        if(!CACHE_CONTAINER.isEmpty()) CACHE_CONTAINER.clear();
        logger.info("[伊卡洛斯]清楚全部缓存数据");
    }
}
