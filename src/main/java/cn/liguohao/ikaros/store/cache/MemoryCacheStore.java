package cn.liguohao.ikaros.store.cache;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**内存中的缓存
 * @author liguohao_cn
 * @date 2020/12/30
 */
@Component
public class MemoryCacheStore<T> implements CacheStore<T>{

//    private final static ConcurrentHashMap<String, CacheWrapper<String>> CACHE_CONTAINER = new ConcurrentHashMap<>();
    @Override
    public boolean set(String key, T value) {
        return false;
    }

    @Override
    public T get(String key) {
        return null;
    }

    @Override
    public boolean remove(String key) {
        return false;
    }

    @Override
    public boolean clear() {
        return false;
    }
}
