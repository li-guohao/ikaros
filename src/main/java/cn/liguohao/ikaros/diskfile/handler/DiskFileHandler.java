package cn.liguohao.ikaros.diskfile.handler;

import cn.liguohao.ikaros.store.File;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**硬盘文件处理器 处理真实的文件操作
 * @Author liguohao
 * @Date 2020/11/27 14:51
 */
public interface DiskFileHandler {

    /**
     *  上传文件
     * @param multipartFile 需要上传的文件数据 此方法要求MultipartFile形式的文件
     * @return 数据库文件对象
     * @throws IOException
     */
    File uploadFile(MultipartFile multipartFile) throws IOException;

    /**
     * 删除文件
     * @param relativePath 传的目的地，带完整文件名的相对路径，相对于upload目录，前面带/
     * @return 是否上传成功
     * @throws IOException
     */
    void deleteFile(String relativePath) throws IOException;

}
