package cn.liguohao.ikaros.controller;

import cn.liguohao.ikaros.exception.UserOperateException;
import cn.liguohao.ikaros.response.PagingData;
import cn.liguohao.ikaros.response.Result;
import cn.liguohao.ikaros.response.Status;
import cn.liguohao.ikaros.service.FileService;
import cn.liguohao.ikaros.store.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Description TODO
 * @Author liguohao
 * @Date 2020/11/25 20:03
 */
@RestController
@RequestMapping("/file")
public class FileRestController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public Result uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        // 参数合法性判断
        if (multipartFile.isEmpty()) {
            throw  new UserOperateException("请选择需要上传的文件");
        }
        // 执行请求
        File file = fileService.uploadFile(multipartFile);
        // 并响应结果
        return new Result<File>()
                .setData(file)
                .setStatus(Status.created)
                .setMessage("文件上传成功 => fid="+file.getFileId())
                ;//END
    }

    @DeleteMapping("/{fileId}")
    public Result deleFileByFileId(@PathVariable("fileId") Long fileId)  throws IOException {
        // 执行请求
        fileService.deleteFileById(fileId);
        // 响应结果
        return new Result()
                .setStatus(Status.success)
                .setMessage("文件删除成功 => fid="+fileId)
                ;//END
    }

    @GetMapping("/{fileId}")
    public Result<File> findFileByFileId(@PathVariable("fileId") Long fileId)  throws IOException {
        return new Result<File>()
                .setData(fileService.findById(fileId))
                .setStatus(Status.success)
                .setMessage("文件查询成功 => fid="+fileId)
                ;//END
    }

    @PostMapping("/list/{currentPage}/{pageSize}")
    public Result<PagingData<File>> findFilesByPaging(
            @RequestBody File file,
            @PathVariable("currentPage") Integer currentPage,
            @PathVariable("pageSize") Integer pageSize
    ){
        return new Result<PagingData<File>>()
                .setData(fileService.findByPaging(file,currentPage,pageSize))
                .setStatus(Status.success)
                .setMessage("分页条件查询文件成功")
                ;//END
    }
}