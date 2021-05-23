package cn.liguohao.ikaros.store.diskfile.handler;

import cn.liguohao.ikaros.client.AliyunOSSClient;
import cn.liguohao.ikaros.enums.db.config.AliyunOSSKey;
import cn.liguohao.ikaros.enums.db.config.ConfigType;
import cn.liguohao.ikaros.enums.db.config.DiskFileStrategyValue;
import cn.liguohao.ikaros.service.ConfigService;
import cn.liguohao.ikaros.store.database.DBFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * 阿里云对象存储文件处理器
 *
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @see DiskFileStrategyValue#ALIYUN_OSS
 * @since 2020/12/30
 */
@Component
public class AliyunOSSDiskFileHandler extends AbstractDiskFileHandler {

    /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(AliyunOSSDiskFileHandler.class);

    @Autowired
    private AliyunOSSClient aliyunOSSClient;
    @Autowired
    private ConfigService configService;

    /**
     * @see AbstractDiskFileHandler#definiteFileUpload(InputStream, DBFile)
     */
    @Override
    protected DBFile definiteFileUpload(InputStream fileInputStream, DBFile dbFile) throws IOException {
        Map<String, String> configItemsMap = configService.getConfigInfoByType(ConfigType.ALIYUN_OSS);
        aliyunOSSClient.simpleFileUpload(dbFile.getRelativePath(), fileInputStream);
        dbFile.setUploadTime(LocalDateTime.now());
        dbFile.setPlace(DiskFileStrategyValue.ALIYUN_OSS.name());
        String objectKey = configItemsMap.get(AliyunOSSKey.OBJECT_NAME_PREFIX.name()) + dbFile.getRelativePath();
        dbFile.setDiskPath(objectKey);
        dbFile.setWebUrl(configItemsMap.get(AliyunOSSKey.ACCESS_PROTOCOL.name()) + "://" + configItemsMap.get(AliyunOSSKey.ACCESS_DOMAIN.name()) + "/" + objectKey);
        return dbFile;
    }

    /**
     * @see AbstractDiskFileHandler#definiteDiskFileDelete(String)
     */
    @Override
    protected void definiteDiskFileDelete(String relativePath) throws FileNotFoundException {
        aliyunOSSClient.deleteFile(relativePath);
    }
}
