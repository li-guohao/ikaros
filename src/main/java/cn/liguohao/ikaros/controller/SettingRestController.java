package cn.liguohao.ikaros.controller;

import cn.liguohao.ikaros.exception.UserOperateException;
import cn.liguohao.ikaros.response.Result;
import cn.liguohao.ikaros.response.Status;
import cn.liguohao.ikaros.service.SettingService;
import cn.liguohao.ikaros.store.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**应用设置RestController
 * @Auther: liguohao
 * @Date: 2020/11/28 16:49
 */
@RestController
@RequestMapping("/setting")
public class SettingRestController {

    @Autowired
    private SettingService settingService;


    @GetMapping("/item/{type}/{name}")
    public Result<String> findSettingItemBySettingTypeAndName(
            @PathVariable("type") String type,
            @PathVariable("name") String name
    ){
        return new Result<String>()
                .setData(settingService.findSettingByTypeAndName(type, name).getValue())
                .setStatus(Status.success)
                .setMessage("查询应用设置项成功")
                ;//END
    }

    @PutMapping("/item")
    public Result<Setting> updateSettingItem(@RequestBody Setting setting){
        return new Result<Setting>()
                .setData(settingService.saveSettingItem(setting))
                .setStatus(Status.success)
                .setMessage("更新应用设置项成功")
                ;//END
    }

    @PostMapping("/item")
    public Result<Setting> addSettingItem(@RequestBody Setting setting){
        return new Result<Setting>()
                .setData(settingService.saveSettingItem(setting))
                .setStatus(Status.created)
                .setMessage("添加应用设置项成功")
                ;//END
    }

}
