package cn.liguohao.ikaros.diskfile.handler;

import cn.liguohao.ikaros.constant.DiskFilePlace;
import cn.liguohao.ikaros.store.File;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

/**阿里云OSS对象存储 磁盘文件处理器
 * @Auther: liguohao
 * @Date: 2020/11/27 18:37
 */
@Component
public class AliyunOSSDiskFileHandler extends AbstractDiskFileHandler{

    /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(AliyunOSSDiskFileHandler.class);


    @Override
    protected Map<String, String> getDefiniteTypeObjectStorageInfoMap() {
        return getObjectStorageInfoMap(DiskFilePlace.ALIYUN_OSS);
    }

    @Override
    public File definiteObjectStorageFileUpload(Map<String, String> aliyunOSSInfoMap, byte[] dataBtyes, File file) throws IOException {
        // 获取相关信息
        // 配置OSS储存 ObjectName <ObjectName>表示上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg
        String objectNamePrefix = aliyunOSSInfoMap.get("object_name_prefix");
        // ObjectNamePrefix 第一个不允许有/符，如果有需要去掉
        if('/' == objectNamePrefix.charAt(0)) objectNamePrefix = objectNamePrefix.substring(1);
        // 拼接中间目录和文件名称
        String objectName =  objectNamePrefix+ file.getRelativePath();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(aliyunOSSInfoMap.get("endpoint"), aliyunOSSInfoMap.get("access_key_id"), aliyunOSSInfoMap.get("access_key_secret"));

        // 上传文件
        logger.info("文件上传到阿里云OSS==正在进行 ===> "+objectName);
        InputStream dataInputStream = new ByteArrayInputStream(dataBtyes);
        ossClient.putObject(aliyunOSSInfoMap.get("bucket_name"), objectName, dataInputStream);
        logger.info("文件上传到阿里云OSS==已完成 ===> "+objectName);
        String webUrl = aliyunOSSInfoMap.get("access_protocol") + "://" +aliyunOSSInfoMap.get("access_domain") + "/" +objectName;
        logger.info("文件上传到阿里云OSS==访问路径 ==> "+webUrl);

        // 关闭OSSClient。
        ossClient.shutdown();

        // 文件属性设置
        file.setPlace(DiskFilePlace.ALIYUN_OSS.getName());
        file.setDiskPath(webUrl);
        file.setWebUrl(webUrl);
        file.setUploadTime(new Date());

        // 返回结果
        return file;
    }


    @Override
    protected void definiteObjectStorageFileDelete(Map<String, String> aliyunOSSInfoMap, String relativePath) throws IOException {
        // 构建对象名
        String objectName = aliyunOSSInfoMap.get("object_name_prefix") + relativePath;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(aliyunOSSInfoMap.get("endpoint"), aliyunOSSInfoMap.get("access_key_id"), aliyunOSSInfoMap.get("access_key_secret"));

        // 删除文件
        ossClient.deleteObject(aliyunOSSInfoMap.get("bucket_name"),objectName);
        logger.info("已删除阿里云OSS文件 ==> "+objectName);
        // 关闭OSSClient。
        ossClient.shutdown();
    }

}
