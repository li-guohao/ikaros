package cn.liguohao.ikaros.rest.controller;

import cn.liguohao.ikaros.store.cache.CacheStore;
import cn.liguohao.ikaros.vo.Result;
import cn.liguohao.ikaros.vo.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**缓存控制层
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/1/2
 */
@RestController
@RequestMapping("/cache")
public class CacheRestController {

    @Autowired
    public CacheStore cacheStore;

    @PutMapping("/clear")
    public Result clear(){
        cacheStore.clear();
        return Result.build().setStatus(Status.success).setMessage("清楚全部缓存成功");
    }

}
