package cn.liguohao.ikaros.diskfile.handler;

import cn.liguohao.ikaros.constant.DiskFilePlace;
import cn.liguohao.ikaros.dao.SettingDao;
import cn.liguohao.ikaros.exception.DataNotExistException;
import cn.liguohao.ikaros.exception.UserOperateException;
import cn.liguohao.ikaros.store.File;
import cn.liguohao.ikaros.store.Setting;
import cn.liguohao.ikaros.util.AESEncrptUtils;
import cn.liguohao.ikaros.util.MultipartFileUtils;
import cn.liguohao.ikaros.util.RSAEncrptUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyPair;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**抽象磁盘文件处理器
 * @Auther: liguohao
 * @Date: 2020/11/27 18:08
 */
public abstract class AbstractDiskFileHandler implements DiskFileHandler {

    /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(AbstractDiskFileHandler.class);

    /**
     * 应用设置Dao
     */
    @Autowired
    private SettingDao settingDao;


    /**
     * 从数据库获取对应平台的对象存储相关连接信息
     * @param diskFilePlace 磁盘文件储存位置 如DiskFilePlace.LOCAL
     */
    protected Map<String, String> getObjectStorageInfoMap(DiskFilePlace diskFilePlace){
        if(diskFilePlace == DiskFilePlace.LOCAL) return null;
        HashMap<String, String> objectStorage = new HashMap<>();
        List<Setting> settingList = settingDao.findSettingByType(diskFilePlace.getName());
        for(Setting setting: settingList){
            objectStorage.put(setting.getName(),setting.getValue());
        }
        if(StringUtils.isEmpty(objectStorage.get("access_protocol")) || StringUtils.isEmpty(objectStorage.get("access_domain")) ) throw new UserOperateException("您还没有设置对象存储的相关信息");
        return objectStorage;
    };

    /**
     * 获取具体类型的对象存储信息Map集合
     * @return 具体类型信息的Map集合
     */
    protected abstract Map<String,String> getDefiniteTypeObjectStorageInfoMap();

    /**
     * 具体的对象存储文件上传操作
     * @param objectStorageInfoMap 具体类型的对象存储信息Map集合
     * @param dataBtyes 需要上传的数据字节数组
     * @param dbFile 数据库文件对象
     * @return 数据库文件对象
     */
    public abstract File definiteObjectStorageFileUpload(Map<String,String> objectStorageInfoMap,byte[] dataBtyes,File dbFile) throws IOException;

    @Override
    public File uploadFile(MultipartFile multipartFile) throws IOException {
        // 获取具体类型的对象存储信息Map集合
        Map<String, String> objectStorageInfoMap = getDefiniteTypeObjectStorageInfoMap();

        // 获取文件数据对象
        File dbFile = MultipartFileUtils.generateDatabaseFile(multipartFile);

        // 具体的创建上传客户端并上传文件的操作
        return definiteObjectStorageFileUpload(objectStorageInfoMap,multipartFile.getBytes(),dbFile);
    }

    protected abstract void definiteObjectStorageFileDelete(Map<String,String> objectStorageInfoMap,String relativePath) throws IOException;

    @Override
    public void deleteFile(String relativePath) throws IOException {
        // 获取具体类型的对象存储信息Map集合
        Map<String, String> objectStorageInfoMap = getDefiniteTypeObjectStorageInfoMap();
        // 具体的删除文件操作
        definiteObjectStorageFileDelete(objectStorageInfoMap,relativePath);
    }
}
