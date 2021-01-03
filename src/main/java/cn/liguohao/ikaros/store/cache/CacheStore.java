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
    void set(String key,T value);

    /**
     * 往缓存中保存值，键相同覆盖值，可设置有效时间(单位秒)
     * @param key 缓存键
     * @param value 缓存值
     * @param expireSeconds 有效时间(单位秒)
     */
    void set(String key,T value,Integer expireSeconds);

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
    T remove(String key);

    /**
     * 移除key匹配前缀的所有key-value
     * @param keyprofix key的前缀
     */
    void removeByKeyprofix(String keyprofix);

    /**
     * 清楚所以缓存
     * @return
     */
    void clear();

    /**
     * 判断缓存是否存在
     * @param key 缓存键
     * @return 是否存在
     */
    Boolean containsKey(String key);
}
