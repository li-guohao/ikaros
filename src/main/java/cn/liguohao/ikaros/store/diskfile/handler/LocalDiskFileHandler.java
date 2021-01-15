package cn.liguohao.ikaros.store.diskfile.handler;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**本地磁盘文件处理器
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2020/12/30
 */
@Component
public class LocalDiskFileHandler extends AbstractDiskFileHandler{
    /**
     * @see DiskFileHandler#uploadFile(byte[])
     */
    @Override
    public File uploadFile(byte[] fileBytes) throws IOException {
        return null;
    }
    /**
     * @see DiskFileHandler#deleteFile(String) 
     */
    @Override
    public void deleteFile(String relativePath) throws IOException {

    }
}
