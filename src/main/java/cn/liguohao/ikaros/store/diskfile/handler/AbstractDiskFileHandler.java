package cn.liguohao.ikaros.store.diskfile.handler;

import cn.liguohao.ikaros.annotation.IkarosCache;
import cn.liguohao.ikaros.constant.ConfigItemEnum;
import cn.liguohao.ikaros.constant.DiskFilePlaceEnum;
import cn.liguohao.ikaros.service.ConfigService;
import cn.liguohao.ikaros.store.database.Config;
import cn.liguohao.ikaros.store.database.DBFile;
import cn.liguohao.ikaros.util.IkarosAssert;
import cn.liguohao.ikaros.util.StringUtils;
import cn.liguohao.ikaros.util.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**抽象磁盘文件处理器
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2020/12/30
 */
public abstract class AbstractDiskFileHandler implements DiskFileHandler {

    /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(AbstractDiskFileHandler.class);

    @Autowired
    private ConfigService configService;

    /**
     * 获取具体类型的对象存储信息Map集合
     * 由子类实现
     * @return 具体类型信息的Map集合
     */
    protected abstract Map<String,String> getDefiniteTypeObjectStorageInfoMap();

    /**
     * 从数据库获取对应平台的对象存储相关连接信息
     * @param diskFilePlaceConfig 磁盘文件储存策略配置信息 如ConfigItemEnum.DISK_FILE_PLACE_LOCAL
     * @return 对应存储策略的配置项信息
     */
    @IkarosCache
    protected Map<String, String> getObjectStorageInfoMap(ConfigItemEnum diskFilePlaceConfig){
        if(diskFilePlaceConfig == ConfigItemEnum.DISK_FILE_PLACE_LOCAL) return null;
        HashMap<String, String> objectStorage = new HashMap<>();
        // 将储存策略的值作为类型 查询 多条对应策略的配置项
        List<Config> configs = configService.findList(Example.of(
                Config.build().setType(diskFilePlaceConfig.getValue())
        ));
        // 将储存策略的配置项储存到集合中
        for(Config config : configs) objectStorage.put(config.getName(),config.getValue());

        // 缺省提醒
        IkarosAssert.isFalse(StringUtils.isEmpty(objectStorage.get("access_protocol")) || StringUtils.isEmpty(objectStorage.get("access_domain")),"您还没有设置对象存储的相关信息");
        return objectStorage;
    };

    /**
     * @see DiskFileHandler#uploadFile(MultipartFile)
     */
    @Override
    public DBFile uploadFile(MultipartFile multipartFile) throws IOException {
        IkarosAssert.isNotEmpty(multipartFile,"上传的文件不能为空");
        // 获取信息
        Map<String, String> objectStorageInfoMap = getDefiniteTypeObjectStorageInfoMap();

        // 根据multipartFile 构建 dBFile
        DBFile dbFile = new DBFile();
        // 获取原来的文件名称
        String originalName = multipartFile.getOriginalFilename();
        dbFile.setOriginalName(originalName);

        // 获取文件的后缀格式
        String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
        dbFile.setSuffix(suffix);

        //生成文件名 UUID
        String diksFileName = UUIDUtils.getId() + "." + suffix;
        dbFile.setDiskName(diksFileName);

        // 文件大小
        long size = multipartFile.getSize();
        dbFile.setSize(size);

        //生成文件多层路径,日期 /2020/11/14
        Calendar now = Calendar.getInstance();
        String middle = "";
        middle += "/" + now.get(Calendar.YEAR); // /2020
        middle += "/" + (now.get(Calendar.MONTH) + 1);// /11
        middle += "/" + now.get(Calendar.DAY_OF_MONTH); //14
        middle += "/" + now.get(Calendar.HOUR_OF_DAY); //15

        // 构建文件相对于upload目录的路径 /2020/11/14/simple.jpg
        String realtivePath = middle + "/" + diksFileName;
        dbFile.setRelativePath(realtivePath);
        if(StringUtils.isEmpty(dbFile.getDescription())) dbFile.setDescription("这是一段默认的对于该文件的描述QAQ");

        // 执行具体的文件上传
        return definiteObjectStorageFileUpload(objectStorageInfoMap,multipartFile.getBytes(),dbFile);
    }

    /**
     * 具体的文件操作
     * 由子类实现
     * @param objectStorageInfoMap 储存策略配置项信息Map集合
     * @param bytes 文件数据
     * @param dbFile 数据库文件记录对象
     * @return 完整的数据库文件记录对象
     */
    protected abstract DBFile definiteObjectStorageFileUpload(Map<String, String> objectStorageInfoMap, byte[] bytes, DBFile dbFile) throws IOException;

    @Override
    public void deleteFile(String relativePath) throws IOException {
        // 获取信息
        Map<String, String> objectStorageInfoMap = getDefiniteTypeObjectStorageInfoMap();
        // 具体的删除文件操作
        definiteObjectStorageFileDelete(objectStorageInfoMap,relativePath);
    }

    /**
     * 具体的文件移除操作
     * @param objectStorageInfoMap 储存策略配置项信息Map集合
     * @param relativePath 文件的相对路径
     */
    protected abstract void definiteObjectStorageFileDelete(Map<String, String> objectStorageInfoMap, String relativePath) throws FileNotFoundException;
}
