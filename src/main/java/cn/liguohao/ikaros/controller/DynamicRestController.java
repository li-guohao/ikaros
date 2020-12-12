package cn.liguohao.ikaros.controller;

import cn.liguohao.ikaros.exception.UserOperateException;
import cn.liguohao.ikaros.response.PagingData;
import cn.liguohao.ikaros.response.Result;
import cn.liguohao.ikaros.response.Status;
import cn.liguohao.ikaros.service.DynamicService;
import cn.liguohao.ikaros.store.Dynamic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

/**动态控制层
 * @Auther: liguohao
 * @Date: 2020/11/30 15:34
 */
@RestController
@RequestMapping("/dynamic")
public class DynamicRestController {

    @Autowired
    private DynamicService dynamicService;

    @PostMapping
    public Result<Dynamic> add(@RequestBody Dynamic dynamic){
        return new Result<Dynamic>()
                .setData(dynamicService.save(dynamic))
                .setStatus(Status.created)
                .setMessage("动态保存成功")
                ;//END
    }

    @PutMapping
    public Result<Dynamic> update(@RequestBody Dynamic dynamic){
        return new Result<Dynamic>()
                .setData(dynamicService.save(dynamic))
                .setStatus(Status.success)
                .setMessage("动态更新成功")
                ;//END
    }

    @DeleteMapping("/{dynamicId}")
    public Result deleteDynamicByDynamicId(@PathVariable("dynamicId") Long dynamicId){
        dynamicService.deleteById(dynamicId);
        return new Result()
                .setStatus(Status.success)
                .setMessage("删除动态成功 ==> dynamicId"+dynamicId)
                ;//END
    }

    @GetMapping("/{dynamicId}")
    public Result findDynamicByDynamicId(@PathVariable("dynamicId") Long dynamicId){
        return new Result()
                .setData(dynamicService.findById(dynamicId))
                .setStatus(Status.success)
                .setMessage("查询动态成功 ==> dynamicId="+dynamicId)
                ;//END
    }

    @GetMapping("/list/{currentPage}/{pageSize}")
    public Result<PagingData<Dynamic>> findDynamicByPaging(@PathVariable("currentPage") Integer currentPage,@PathVariable("pageSize") Integer pageSize){
        return new Result<PagingData<Dynamic>>()
                .setData(dynamicService.findByPaging(currentPage,pageSize))
                .setStatus(Status.success)
                .setMessage("分页条件查询动态成功")
                ;//END
    }

}
