package cn.liguohao.ikaros.controller;

import cn.liguohao.ikaros.service.DBFileService;
import cn.liguohao.ikaros.store.database.DBFile;
import cn.liguohao.ikaros.vo.Result;
import cn.liguohao.ikaros.vo.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**数据库文件记录 控制层
 * @author liguohao_cn
 * @date 2021/1/2
 */
@RestController
@RequestMapping("/dbfile")
public class DBFileController {

    @Autowired
    private DBFileService dbFileService;

    /**
     * 根据精确条件查询单个对象
     * @param dbFile 数据库文件记录对象
     * @return 带数据库文件记录对象的结果
     */
    @PostMapping("/one")
    public Result<DBFile> findOne(@RequestBody DBFile dbFile){
        DBFile dbFileRes = dbFileService.findOne(Example.of(dbFile));
        Result result = Result.build().setData(dbFileRes);
        if(dbFileRes!=null){
           result.setStatus(Status.success).setMessage("查询文件记录成功");
        }else {
           result.setStatus(Status.notFound).setMessage("未查询到目标文件记录");
        }
        return result;
    }

    @PostMapping("/list")
    public Result<List> findList(@RequestBody DBFile dbFile){
        List<DBFile> dbFiles = dbFileService.findList(Example.of(dbFile));
        Result result = Result.build().setData(dbFiles);
        if(dbFiles.size()>0){
            result.setStatus(Status.success).setMessage("查询文件记录成功");
        }else {
            result.setStatus(Status.notFound).setMessage("未查询到目标文件记录");
        }
        return result;
    }
}
