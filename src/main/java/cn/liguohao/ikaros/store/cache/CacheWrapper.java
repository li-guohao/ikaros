package cn.liguohao.ikaros.store.cache;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**缓存包装类
 * @author liguohao_cn
 * @since 2020/12/31
 */
public class CacheWrapper<T> implements Serializable {

    /**
     * 数据
     */
    private T data;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    public CacheWrapper() {
    }

    public static CacheWrapper build(){
        return new CacheWrapper();
    }

    public CacheWrapper(T data, LocalDateTime createTime, LocalDateTime expireTime) {
        this.data = data;
        this.createTime = createTime;
        this.expireTime = expireTime;
    }

    public T getData() {
        return data;
    }

    public CacheWrapper setData(T data) {
        this.data = data;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public CacheWrapper setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public CacheWrapper setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
        return this;
    }
}
