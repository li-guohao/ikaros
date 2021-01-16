package cn.liguohao.ikaros.controller;

import cn.liguohao.ikaros.service.DBFileService;
import cn.liguohao.ikaros.store.database.DBFile;
import cn.liguohao.ikaros.vo.PageQuery;
import cn.liguohao.ikaros.vo.PagingData;
import cn.liguohao.ikaros.vo.Result;
import cn.liguohao.ikaros.vo.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**数据库文件记录 控制层
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/1/2
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

    /**
     * 查询文件列表
     * @param dbFile 数据库文件记录对象
     * @return 带数据的结果
     */
    @PostMapping("/list")
    public Result<List<DBFile>> findList(@RequestBody DBFile dbFile){
        return Result.build().setDSM(dbFileService.findList(Example.of(dbFile)),"查询文件记录成功","查询文件记录失败");
    }

    /**
     * 上传文件
     * @param file 待上传的文件
     * @return 带数据库文件记录对象的结果
     * @throws IOException IO读写异常
     */
    @PutMapping("/upload")
    public Result<DBFile> upload(MultipartFile file) throws IOException {
        return Result.build().setDSM(dbFileService.upload(file),Status.created,"上传文件成功",Status.serverError,"上传文件失败");
    }

    /**
     * 移除文件
     * @param fileId 文件记录ID
     * @return 是否成功
     * @throws IOException IO读写异常
     */
    @DeleteMapping("/{id}")
    public Result deleteFileByFileId(@PathVariable("id") Long fileId) throws IOException {
        dbFileService.deleteFileById(fileId);
        return Result.build().setStatus(Status.success).setMessage("删除ID为<"+fileId+">的文件成功");
    }

    /**
     * 根据ID查询文件
     * @param fileId 文件记录ID
     * @return 带数据库文件记录的结果
     */
    @GetMapping("/{id}")
    public Result<DBFile> findDBFileByFileId(@PathVariable("id") Long fileId){
        return Result.build().setDSM(dbFileService.findByFileId(fileId),Status.success,"查询成功",Status.notFound,"查询文件记录失败==>"+fileId);
    }

    /**
     * 分页查询文件
     * @param dbFilePageQuery 分页查询对象
     * @return 带分页数据的结果
     */
    @PostMapping("/list/paging")
    public Result<PagingData<DBFile>> findDBFilesByPaging(
            @RequestBody PageQuery<DBFile> dbFilePageQuery
            ){
        return Result.build().setDSM(
                dbFileService.findDBFilesByPaging(dbFilePageQuery),
                Status.success,"分页查询成功",
                Status.notFound,"查询无数据"
        );
    }
}
