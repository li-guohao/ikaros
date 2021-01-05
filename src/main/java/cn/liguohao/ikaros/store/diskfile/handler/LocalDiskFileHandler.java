package cn.liguohao.ikaros.store.diskfile.handler;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**本地磁盘文件处理器
 * @author liguohao_cn
 * @since 2020/12/30
 */
@Component
public class LocalDiskFileHandler extends AbstractDiskFileHandler{
    /**
     * @see DiskFileHandler#uploadFile(Byte[]) 
     */
    @Override
    public File uploadFile(Byte[] fileBytes) throws IOException {
        return null;
    }
    /**
     * @see DiskFileHandler#deleteFile(String) 
     */
    @Override
    public void deleteFile(String relativePath) throws IOException {

    }
}
