package cn.liguohao.ikaros.client;

import cn.liguohao.ikaros.enums.db.config.AliyunOSSKey;
import cn.liguohao.ikaros.enums.db.config.ConfigType;
import cn.liguohao.ikaros.service.ConfigService;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.InputStream;
import java.util.Map;

/**
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/5/23
 */
@Component
public class AliyunOSSClient implements InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(AliyunOSSClient.class);

    @Autowired
    private ConfigService configService;

    private OSS oss;
    private Map<String, String> configMap;

    @Override
    public void afterPropertiesSet() throws Exception {
        configMap = configService.getConfigInfoByType(ConfigType.ALIYUN_OSS);
        oss = new OSSClientBuilder().build(
                configMap.get(AliyunOSSKey.ENDPOINT.name())
                , configMap.get(AliyunOSSKey.ACCESS_KEY_ID.name())
                , configMap.get(AliyunOSSKey.ACCESS_KEY_SECRET.name())
        );
    }

    @PreDestroy
    public void releaseResource() {
        oss.shutdown();
    }

    /**
     * 简单文件上传
     *
     * @param relativePath    文件相对upload文件夹的路径(带文件后缀名)
     * @param fileInputStream 文件的输入流
     */
    public void simpleFileUpload(String relativePath, InputStream fileInputStream) {
        String objKey = configMap.get(AliyunOSSKey.OBJECT_NAME_PREFIX.name()) + relativePath;
        logger.info("开始上传文件到阿里云对象存储 ==> " + objKey);
        PutObjectRequest putObjectRequest = new PutObjectRequest(
                configMap.get(AliyunOSSKey.BUCKET_NAME.name())
                , objKey
                , fileInputStream
        );
        oss.putObject(putObjectRequest);
        logger.info("成功上传文件到阿里云对象存储 ==> " + objKey);
    }

    /**
     * 移除文件
     *
     * @param relativePath 文件相对upload文件夹的路径(带文件后缀名)
     */
    public void deleteFile(String relativePath) {
        String objectKey = configMap.get(AliyunOSSKey.OBJECT_NAME_PREFIX.name()) + relativePath;
        if (oss.doesObjectExist(configMap.get(AliyunOSSKey.BUCKET_NAME.name()), objectKey)) {
            oss.deleteObject(configMap.get(AliyunOSSKey.BUCKET_NAME.name()), objectKey);
        }
        logger.info("删除阿里云对象存储文件成功 ==> " + objectKey);
    }


}
