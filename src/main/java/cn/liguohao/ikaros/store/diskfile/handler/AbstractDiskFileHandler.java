package cn.liguohao.ikaros.store.diskfile.handler;

import cn.liguohao.ikaros.annotation.IkarosCache;
import cn.liguohao.ikaros.enums.db.config.AliyunOSSKey;
import cn.liguohao.ikaros.enums.db.config.DiskFileStrategyValue;
import cn.liguohao.ikaros.service.ConfigService;
import cn.liguohao.ikaros.store.database.Config;
import cn.liguohao.ikaros.store.database.DBFile;
import cn.liguohao.ikaros.util.IkarosAssert;
import cn.liguohao.ikaros.util.StringUtils;
import cn.liguohao.ikaros.util.UUIDUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 抽象磁盘文件处理器
 *
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2020/12/30
 */
public abstract class AbstractDiskFileHandler implements DiskFileHandler {


    @Autowired
    private ConfigService configService;

    /**
     * 获取具体类型的对象存储信息Map集合
     * 由子类实现
     *
     * @return 具体类型信息的Map集合
     */
    protected abstract Map<String, String> getDefiniteDiskFileConfigMap();

    /**
     * 根据文件存储策略 查询对应的策略配置信息键值对
     * 文件存储策略的值 对应着 配置的类型
     * @param currentDiskFileStrategyValue 当前的文件存储策略
     * @return 磁盘文件存储相关配置键值对
     */
    @IkarosCache
    protected Map<String, String> getDiskFileConfigMap(DiskFileStrategyValue currentDiskFileStrategyValue) {
        HashMap<String, String> diskFileConfigMap = new HashMap<>(25);

        // 文件存储策略的值 对应着 配置的类型
        List<Config> configs = configService.findAll(
                Config.build().setType(currentDiskFileStrategyValue.name())
        );
        // 将储存策略的配置项储存到集合中
        for (Config config : configs) {
            diskFileConfigMap.put(config.getKey(), config.getValue());
        }

        // 参数缺省提醒
        if(currentDiskFileStrategyValue == DiskFileStrategyValue.ALIYUN_OSS) {
            // 阿里云对象存储，确保参数有值
            IkarosAssert.isFalse(
                    (StringUtils.isEmpty(diskFileConfigMap.get(AliyunOSSKey.ACCESS_DOMAIN.name())) || StringUtils.isEmpty(diskFileConfigMap.get(AliyunOSSKey.ACCESS_INTERNAL_DOMAIN.name())))
                    || StringUtils.isEmpty(diskFileConfigMap.get(AliyunOSSKey.ACCESS_KEY_SECRET.name()))
                    || StringUtils.isEmpty(diskFileConfigMap.get(AliyunOSSKey.ACCESS_PROTOCOL.name()))
                    || StringUtils.isEmpty(diskFileConfigMap.get(AliyunOSSKey.ACCESS_KEY_ID.name()))
                    || StringUtils.isEmpty(diskFileConfigMap.get(AliyunOSSKey.OBJECT_NAME_PREFIX.name()))
                    || StringUtils.isEmpty(diskFileConfigMap.get(AliyunOSSKey.ENDPOINT.name()))
                    || StringUtils.isEmpty(diskFileConfigMap.get(AliyunOSSKey.BUCKET_NAME.name()))
                    , "阿里云对象存储配置信息缺失，需要的信息有：" + JSON.toJSON(AliyunOSSKey.values())
            );
        }

        // 返回结果
        return diskFileConfigMap;
    }

    /**
     * @see DiskFileHandler#uploadFile(MultipartFile)
     */
    @Override
    public DBFile uploadFile(MultipartFile multipartFile) throws IOException {

        // 默认的文件描述
        final String defaultDbfileDescription = "这是一段默认的对于该文件的描述QAQ";

        IkarosAssert.isNotEmpty(multipartFile, "上传的文件不能为空");
        // 获取信息
        Map<String, String> diskFileConfigMap = getDefiniteDiskFileConfigMap();

        // 根据multipartFile 构建 dBFile
        DBFile dbFile = new DBFile();
        // 获取原来的文件名称
        String originalName = multipartFile.getOriginalFilename();
        dbFile.setOriginalName(originalName);

        // 获取文件的后缀格式
        String suffix = originalName == null ? "" : originalName.substring(originalName.lastIndexOf(".") + 1).toLowerCase();
        dbFile.setSuffix(suffix);

        //生成文件名 UUID
        String diskFileName = UUIDUtils.getId() + "." + suffix;
        dbFile.setDiskName(diskFileName);

        // 文件大小
        long size = multipartFile.getSize();
        dbFile.setSize(size);

        //生成文件多层路径,日期 /2020/11/14
        Calendar now = Calendar.getInstance();
        String middle = "";
        middle += "/" + now.get(Calendar.YEAR);
        middle += "/" + (now.get(Calendar.MONTH) + 1);
        middle += "/" + now.get(Calendar.DAY_OF_MONTH);
        middle += "/" + now.get(Calendar.HOUR_OF_DAY);

        // 构建文件相对于upload目录的路径 /2020/11/14/simple.jpg
        String relativePath = middle + "/" + diskFileName;
        dbFile.setRelativePath(relativePath);
        if (StringUtils.isEmpty(dbFile.getDescription())) {
            dbFile.setDescription(defaultDbfileDescription);
        }

        // 执行具体的文件上传
        return definiteFileUpload(diskFileConfigMap, multipartFile.getInputStream(), dbFile);
    }

    /**
     * 具体的文件上传操作
     * 由子类实现
     *
     * @param diskFileConfigMap 储存策略配置项信息Map集合
     * @param fileInputStream 文件数据输入流
     * @param dbFile 数据库文件记录对象
     * @return 完整的数据库文件记录对象
     * @throws IOException
     */
    protected abstract DBFile definiteFileUpload(Map<String, String> diskFileConfigMap, InputStream fileInputStream, DBFile dbFile) throws IOException;

    @Override
    public void deleteFile(String relativePath) throws IOException {
        // 获取信息
        Map<String, String> objectStorageInfoMap = getDefiniteDiskFileConfigMap();
        // 具体的删除文件操作
        definiteDiskFileDelete(objectStorageInfoMap, relativePath);
    }

    /**
     * 具体的文件移除操作
     *
     * @param objectStorageInfoMap 储存策略配置项信息Map集合
     * @param relativePath         文件的相对路径
     * @throws FileNotFoundException 删除操作文件未找到
     */
    protected abstract void definiteDiskFileDelete(Map<String, String> objectStorageInfoMap, String relativePath) throws FileNotFoundException;
}
