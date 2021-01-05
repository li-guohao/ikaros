package cn.liguohao.ikaros.controller;

import cn.liguohao.ikaros.store.cache.CacheStore;
import cn.liguohao.ikaros.vo.Result;
import cn.liguohao.ikaros.vo.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**缓存控制层
 * @author liguohao_cn
 * @since 2021/1/2
 */
@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    public CacheStore cacheStore;

    @PutMapping("/clear")
    public Result clear(){
        cacheStore.clear();
        return Result.build().setStatus(Status.success).setMessage("清楚全部缓存成功");
    }

}
