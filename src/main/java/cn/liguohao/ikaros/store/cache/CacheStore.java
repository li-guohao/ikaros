package cn.liguohao.ikaros.store.cache;

/**缓存仓库
 * @author liguohao_cn
 * @date 2020/12/30
 */
public interface CacheStore<T> {

    /**
     * 往缓存中保存值，键相同覆盖值
     * @param key 缓存键
     * @param value 缓存值
     * @return 是否成功
     */
    boolean set(String key,T value);

    /**
     * 获取指定缓存值
     * @param key 缓存键
     * @return 缓存值
     */
    T get(String key);

    /**
     * 移除指定缓存
     * @param key 缓存键
     * @return
     */
    boolean remove(String key);

    /**
     * 清楚所以缓存
     * @return
     */
    boolean clear();
}
