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
import java.util.Set;

/**系统配置
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/1/16
 */
@RestController
@RequestMapping("/config")
public class ConfigController extends BaseController<Config>{

    @Autowired
    private ConfigService configService;

    @GetMapping("/types")
    public Result<Set<String>> findTypes(){
        return Result.build().setDSM(
                configService.findTypes(),
                Status.success,"查询成功",
                Status.notFound,"所有配置项不存在, 查询失败"
        );
    }


}
