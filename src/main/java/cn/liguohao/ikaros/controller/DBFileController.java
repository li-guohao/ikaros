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

import javax.servlet.http.HttpServletResponse;
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
    @PutMapping("/one/upload")
    public Result<DBFile> uploadPut(MultipartFile file) throws IOException {
        return Result.build().setDSM(dbFileService.upload(file),Status.created,"上传文件成功",Status.serverError,"上传文件失败");
    }

    /**
     * 上传文件
     * @param file 待上传的文件
     * @return 带数据库文件记录对象的结果
     * @throws IOException IO读写异常
     */
    @PostMapping("/one/upload")
    public Result<DBFile> uploadPost(MultipartFile file, HttpServletResponse response) throws IOException {
        Result result = Result.build();
        try {
            result.setData(dbFileService.upload(file));
            result.setStatus(Status.created).setMessage("上传文件成功");
        }catch (Exception exception){
            response.setStatus(500);
            exception.printStackTrace();
            result.setStatus(Status.serverError).setMessage("上传文件失败 ==> "+exception.getMessage());
        }
        return result;
    }

    /**
     * 保存或者更新文件
     * @param dbFile 待保存或更新的文件
     * @return 带数据库文件记录对象的结果
     */
    @PutMapping("/one/save")
    public Result<DBFile> sava(@RequestBody DBFile dbFile){
        return Result.build().setDSM(
                dbFileService.save(dbFile),
                Status.created,"保存或更新成功",
                Status.serverError,"保存或更新失败"
        );
    }

    /**
     * 移除文件
     * @param fileId 文件记录ID
     * @return 是否成功
     * @throws IOException IO读写异常
     */
    @DeleteMapping("/one/{id}")
    public Result deleteFileByFileId(@PathVariable("id") Long fileId) throws IOException {
        dbFileService.deleteById(fileId);
        return Result.build().setStatus(Status.success).setMessage("删除ID为<"+fileId+">的文件成功");
    }

    /**
     * 根据ID查询文件
     * @param fileId 文件记录ID
     * @return 带数据库文件记录的结果
     */
    @GetMapping("/one/{id}")
    public Result<DBFile> findDBFileByFileId(@PathVariable("id") Long fileId){
        return Result.build().setDSM(dbFileService.findById(fileId),Status.success,"查询成功",Status.notFound,"查询文件记录失败==>"+fileId);
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
                dbFileService.findByPaging(dbFilePageQuery),
                Status.success,"分页查询成功",
                Status.notFound,"查询无数据"
        );
    }
}
