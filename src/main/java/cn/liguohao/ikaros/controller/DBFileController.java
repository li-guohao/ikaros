package cn.liguohao.ikaros.controller;

import cn.liguohao.ikaros.service.DBFileService;
import cn.liguohao.ikaros.store.database.DBFile;
import cn.liguohao.ikaros.vo.Result;
import cn.liguohao.ikaros.vo.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        return Result.build().setDSM(dbFileService.findOne(Example.of(dbFile)),"查询单个文件记录成功","查询单个文件记录失败");
    }

    @PostMapping("/list")
    public Result<List> findList(@RequestBody DBFile dbFile){
        return Result.build().setDSM(dbFileService.findList(Example.of(dbFile)),"查询文件记录成功","查询文件记录失败");
    }

    @PostMapping("/upload")
    public Result<DBFile> upload(MultipartFile file){
        return Result.build().setDSM(dbFileService.upload(file),"上传文件成功","上传文件失败");
    }

}
