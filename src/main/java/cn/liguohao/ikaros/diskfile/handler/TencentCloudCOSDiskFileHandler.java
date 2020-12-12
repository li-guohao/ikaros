package cn.liguohao.ikaros.diskfile.handler;

import cn.liguohao.ikaros.constant.DiskFilePlace;
import cn.liguohao.ikaros.dao.SettingDao;
import cn.liguohao.ikaros.exception.DataNotExistException;
import cn.liguohao.ikaros.exception.UserOperateException;
import cn.liguohao.ikaros.store.File;
import cn.liguohao.ikaros.store.Setting;
import cn.liguohao.ikaros.util.MultipartFileUtils;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**腾讯云COS对象存储 磁盘文件处理器
 * @Auther: liguohao
 * @Date: 2020/11/27 18:37
 */
@Component
public class TencentCloudCOSDiskFileHandler extends AbstractDiskFileHandler{

    /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(AliyunOSSDiskFileHandler.class);


    @Override
    protected Map<String, String> getDefiniteTypeObjectStorageInfoMap() {
        return getObjectStorageInfoMap(DiskFilePlace.TENCENT_CLOUD_COS);
    }

    @Override
    public File definiteObjectStorageFileUpload(Map<String,String> tencentCloudCOSInfoMap, byte[] dataBtyes, File file) throws IOException {
        // 获取中间目录和文件名称
        String objectName = tencentCloudCOSInfoMap.get("object_name_prefix") + file.getRelativePath();

        // 创建上传客户端
        COSClient cosClient = new COSClient(new BasicCOSCredentials(tencentCloudCOSInfoMap.get("secret_id"), tencentCloudCOSInfoMap.get("secret_key")), new ClientConfig(new Region(tencentCloudCOSInfoMap.get("region"))));

        // 上传文件
        PutObjectRequest putObjectRequest = new PutObjectRequest(tencentCloudCOSInfoMap.get("bucket_name"), objectName,new ByteArrayInputStream(dataBtyes),new ObjectMetadata());
        cosClient.putObject(putObjectRequest);
        logger.info("文件上传到腾讯云COS ===> "+objectName);

        // 关闭客户端连接
        cosClient.shutdown();

        // 获取访问URL
        String accessUrl = tencentCloudCOSInfoMap.get("access_protocol") + "://" +tencentCloudCOSInfoMap.get("access_domain") + "/" +objectName;;
        logger.info("腾讯云COS文件的web访问路径为 ===> "+accessUrl);

        //文件属性设置
        file.setPlace(DiskFilePlace.TENCENT_CLOUD_COS.getName());
        file.setDiskPath(accessUrl);
        file.setWebUrl(accessUrl);
        file.setUploadTime(new Date());

        // 返回结果
        return file;
    }

    @Override
    protected void definiteObjectStorageFileDelete(Map<String,String> tencentCloudCOSInfoMap, String relativePath) throws IOException {
        // 获取中间目录和文件名称
        String objectName = tencentCloudCOSInfoMap.get("object_name_prefix") + relativePath;

        // 创建客户端
        COSClient cosClient = new COSClient(new BasicCOSCredentials(tencentCloudCOSInfoMap.get("secret_id"), tencentCloudCOSInfoMap.get("secret_key")), new ClientConfig(new Region(tencentCloudCOSInfoMap.get("region"))));

        // 删除文件
        cosClient.deleteObject(tencentCloudCOSInfoMap.get("bucket_name"),objectName);
        logger.info("已删除腾讯云COS文件 ===> "+objectName);

        // 关闭客户端连接
        cosClient.shutdown();
    }

}
