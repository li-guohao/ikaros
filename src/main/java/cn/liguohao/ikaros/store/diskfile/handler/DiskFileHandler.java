package cn.liguohao.ikaros.store.diskfile.handler;

import cn.liguohao.ikaros.store.database.DBFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**硬盘文件处理器 处理真实的文件操作
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2020/12/30
 */
public interface DiskFileHandler {
    /**
     *  上传文件
     * @param multipartFile 文件对象，multipart格式的
     * @return 数据库文件记录对象
     * @throws IOException
     */
    DBFile uploadFile(MultipartFile multipartFile) throws IOException;

    /**
     * 删除文件
     * @param relativePath 传的目的地，带完整文件名的相对路径，相对于upload目录，前面带/
     * @return 是否上传成功
     * @throws IOException
     */
    void deleteFile(String relativePath) throws IOException;
}
