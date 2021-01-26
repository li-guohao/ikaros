package cn.liguohao.ikaros.controller;

import cn.liguohao.ikaros.service.ConfigService;
import cn.liguohao.ikaros.store.database.Config;
import cn.liguohao.ikaros.vo.PageQuery;
import cn.liguohao.ikaros.vo.PagingData;
import cn.liguohao.ikaros.vo.Result;
import cn.liguohao.ikaros.vo.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**系统配置
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/1/16
 */
@RestController
@RequestMapping("/config")
public class ConfigController {

    /**
     * 日志对象
     */
    private Logger logger = LoggerFactory.getLogger(ConfigController.class);


    @Autowired
    private ConfigService configService;

    @PostMapping("/list/paging")
    public Result<PagingData<Config>> findByPaging(@RequestBody PageQuery<Config> configPageQuery){
        return Result.build().setDSM(
                configService.findByPaging(configPageQuery),
                Status.success,"查询成功",
                Status.notFound,"未查询到数据"
        );
    }

    @PutMapping("/one/save")
    public Result<Boolean> save(@RequestBody Config config){
        return Result.build().setDSM(
                configService.save(config),
                Status.created,"保存或更新成功",
                Status.serverError,"保存或更新失败"
        );
    }

    @DeleteMapping("/one/{id}")
    public Result<Boolean> deleteById(@PathVariable("id") Long id) throws IOException {
        return Result.build().setDSM(
                configService.deleteById(id),
                Status.success,"删除成功",
                Status.serverError,"删除失败"
        );
    }

    @GetMapping("/one/{id}")
    public Result<Boolean> findById(@PathVariable("id") Long id){
        return Result.build().setDSM(
                configService.findById(id),
                Status.success,"查询成功",
                Status.notFound,"查询失败 => id="+id
        );
    }

    @GetMapping("/types")
    public Result<List<String>> findTypes(){
        return Result.build().setDSM(
                configService.findTypes(),
                Status.success,"查询成功",
                Status.notFound,"所有配置项不存在, 查询失败"
        );
    }


}
