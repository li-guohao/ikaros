package cn.liguohao.ikaros.store.diskfile.handler;

import cn.liguohao.ikaros.enums.db.config.DiskFileStrategyValue;
import cn.liguohao.ikaros.store.database.DBFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**百度网盘文件处理器
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2020/12/30
 */
@Component
public class PanBaiduDiskFileHandler extends AbstractDiskFileHandler{

    /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(PanBaiduDiskFileHandler.class);


    /**
     * @see AbstractDiskFileHandler#definiteFileUpload(InputStream, DBFile)
     */
    @Override
    protected DBFile definiteFileUpload(InputStream fileInputStream, DBFile dbFile) throws IOException {
        return null;
    }

    /**
     * @see AbstractDiskFileHandler#definiteDiskFileDelete(String)
     */
    @Override
    protected void definiteDiskFileDelete(String relativePath) throws FileNotFoundException {

    }
}
