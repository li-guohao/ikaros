package cn.liguohao.ikaros.service;

import cn.liguohao.ikaros.response.PagingData;
import cn.liguohao.ikaros.store.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Description TODO
 * @Author liguohao
 * @Date 2020/11/25 20:02
 */
public interface FileService {

    File uploadFile(MultipartFile multipartFile) throws IOException;

    void deleteFileById(Long fileId) throws IOException;

    PagingData<File> findByPaging(File file,Integer currentPage,Integer pageSize);

    File findById(Long fileId);

}
