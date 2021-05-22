package cn.liguohao.ikaros.rest.controller;

import cn.liguohao.ikaros.service.DBFileService;
import cn.liguohao.ikaros.store.database.DBFile;
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
public class DBFileRestController {

    @Autowired
    private DBFileService dbFileService;

    /**
     * 根据精确条件查询单个对象
     * @param dbFile 数据库文件记录对象
     * @return 带数据库文件记录对象的结果
     */
    @PostMapping("/one")
    public Result<DBFile> findOne(@RequestBody DBFile dbFile){
        return Result.build().setDSM(dbFileService.findOne(dbFile),"查询单个文件记录成功","查询单个文件记录失败");
    }

    /**
     * 查询文件列表
     * @param dbFile 数据库文件记录对象
     * @return 带数据的结果
     */
    @PostMapping("/list")
    public Result<List<DBFile>> findList(@RequestBody DBFile dbFile){
        return Result.build().setDSM(dbFileService.findAll(dbFile),"查询文件记录成功","查询文件记录失败");
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

}
