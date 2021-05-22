package cn.liguohao.ikaros.rest.controller;

import cn.liguohao.ikaros.service.ConfigService;
import cn.liguohao.ikaros.vo.Result;
import cn.liguohao.ikaros.vo.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**系统配置
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/1/16
 */
@RestController
@RequestMapping("/config")
public class ConfigRestController{

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
