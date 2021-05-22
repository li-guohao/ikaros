package cn.liguohao.ikaros.store.diskfile.handler;

import cn.liguohao.ikaros.config.ServerConfig;
import cn.liguohao.ikaros.enums.db.config.DiskFileStrategyValue;
import cn.liguohao.ikaros.store.database.DBFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Map;

/**本地磁盘文件处理器
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2020/12/30
 */
@Component
public class LocalDiskFileHandler extends AbstractDiskFileHandler{
    /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(LocalDiskFileHandler.class);

    /**
     *  获取用户路径
     */
    private final static String CURRENT_USER_DIRECTORY_PATH = System.getProperty("user.home") + "/.ikaros/upload";

    /**
     * 获取操作系统名字
     */
    private final static String CURRENT_OS_NAME = System.getProperties().get("os.name").toString();

    @Autowired
    private ServerConfig serverConfig;

    static {
        logger.info("系统运行于操作系统 --> " + CURRENT_OS_NAME);
    }

    /**
     * @see AbstractDiskFileHandler#getDefiniteDiskFileConfigMap()
     */
    @Override
    protected Map<String, String> getDefiniteDiskFileConfigMap() {
        return getDiskFileConfigMap(DiskFileStrategyValue.LOCAL);
    }

    /**
     * @see AbstractDiskFileHandler#definiteFileUpload(Map, InputStream, DBFile)
     */
    @Override
    protected DBFile definiteFileUpload(Map<String, String> objectStorageInfoMap, InputStream fileInputStream, DBFile dbFile) throws IOException {
        // 构建磁盘路径文件
        String diskPath = CURRENT_USER_DIRECTORY_PATH + dbFile.getRelativePath();
        // 判断操作系统 Windows系统适配
        diskPath = osDirectoryAdaptator(diskPath);
        // 构建文件
        java.io.File dest = new java.io.File(diskPath);
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdirs();
        }
        logger.info("将要上传文件到：" + diskPath);
        dbFile.setDiskPath(diskPath);
        // 上传文件
        FileOutputStream fileOutputStream = new FileOutputStream(dest);
        fileInputStream.transferTo(fileOutputStream);
        String fileWebUrl = serverConfig.getUrl() + "/upload" + dbFile.getRelativePath();
        logger.info("上传文件成功，访问路径为：" + fileWebUrl);
        dbFile.setWebUrl(fileWebUrl);
        // 上传时间
        dbFile.setUploadTime(LocalDateTime.now());
        // 其它默认值设置
        dbFile.setPlace(DiskFileStrategyValue.LOCAL.name());

        // 释放资源
        fileOutputStream.close();
        fileInputStream.close();

        // 返回数据库文件对象
        return dbFile;
    }

    /**
     * @see AbstractDiskFileHandler#definiteDiskFileDelete(Map, String)
     */
    @Override
    protected void definiteDiskFileDelete(Map<String, String> objectStorageInfoMap, String relativePath) throws FileNotFoundException {
        File destFile = new File(CURRENT_USER_DIRECTORY_PATH + relativePath);
        if(!destFile.exists()) {throw new FileNotFoundException("文件为在磁盘上无法找到 路径 ==> "+relativePath);}
        destFile.delete();
        logger.info("文件删除成功，被删除的文件磁盘路径为 ==> "+relativePath);
    }


    /**
     * 系统目录适配
     * @param absolutePath 绝对路径
     */
    private String osDirectoryAdaptator(String absolutePath){
        if (CURRENT_OS_NAME.indexOf("Windows") >= 0) {
            // 系统目录符适配
            return absolutePath.replace('/', '\\');
        }
        return  absolutePath;
    }
}
