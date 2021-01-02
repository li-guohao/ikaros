package cn.liguohao.ikaros.controller;

import cn.liguohao.ikaros.dto.DBFileDTO;
import cn.liguohao.ikaros.service.DBFileService;
import cn.liguohao.ikaros.store.database.DBFile;
import cn.liguohao.ikaros.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**数据库文件记录 控制层
 * @author liguohao_cn
 * @date 2021/1/2
 */
@RestController
@RequestMapping("/dbfile")
public class DBFileController {

    @Autowired
    private DBFileService dbFileService;

    public Result<DBFile> findOne(@RequestBody DBFileDTO dbFileDTO){

        return Result.build();
    }
}
