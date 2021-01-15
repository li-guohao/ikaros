package cn.liguohao.ikaros.service.impl;

import cn.liguohao.ikaros.annotation.IkarosCache;
import cn.liguohao.ikaros.annotation.IkarosUpdateCache;
import cn.liguohao.ikaros.constant.ConfigItemEnum;
import cn.liguohao.ikaros.constant.DiskFilePlaceEnum;
import cn.liguohao.ikaros.dao.DBFileDao;
import cn.liguohao.ikaros.exception.IkarosException;
import cn.liguohao.ikaros.exception.IkarosNotFoundException;
import cn.liguohao.ikaros.service.ConfigService;
import cn.liguohao.ikaros.service.DBFileService;
import cn.liguohao.ikaros.store.database.Config;
import cn.liguohao.ikaros.store.database.DBFile;
import cn.liguohao.ikaros.store.diskfile.builder.DiskFileHandlerBuilder;
import cn.liguohao.ikaros.store.diskfile.handler.DiskFileHandler;
import cn.liguohao.ikaros.util.IkarosAssert;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**数据库文件服务层实现类
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/1/2
 */
@Service
public class DBFileServiceImpl implements DBFileService {

    @Autowired
    private DBFileDao dbFileDao;
    @Autowired
    private DiskFileHandler diskFileHandler;
    @Autowired
    private DiskFileHandlerBuilder diskFileHandlerBuilder;
    @Autowired
    private ConfigService configService;


    @Override
    @Transactional
    public void save(DBFile dbFile) {
        dbFileDao.save(dbFile);
    }

    /**
     * 根据应用设置获取磁盘文件处理器的具体实现类
     * @return 磁盘文件处理器的具体实现类
     */
    private DiskFileHandler getApplicationSettingDiskFileHandler(){
        // 查询系统设置
        Config diskFileStrategy = configService.findOne(Example.of(
                Config.build().setType(ConfigItemEnum.DISK_FILE_PLACE_STRATEGY.getType())
                    .setName(ConfigItemEnum.DISK_FILE_PLACE_STRATEGY.getName())
        ));
        return getDiskFileHandlerByDiskFilePlace(diskFileStrategy.getValue());
    }

    /**
     * 根据 磁盘文件储存策略 动态获取 磁盘文件处理器具体实现类
     * @param diskFileStrategyValue 磁盘文件储存策略值
     * @return 磁盘文件处理器具体实现类
     */
    private DiskFileHandler getDiskFileHandlerByDiskFilePlace(String diskFileStrategyValue){
        DiskFileHandler diskFileHandler = null;
        if(diskFileStrategyValue.equals(DiskFilePlaceEnum.ALIYUN_OSS.getName())){ //阿里云存储
            diskFileHandler = diskFileHandlerBuilder.builderDefiniteDiskFileHandler(DiskFilePlaceEnum.ALIYUN_OSS);
        }else { //默认本地存储
            diskFileHandler = diskFileHandlerBuilder.builderDefiniteDiskFileHandler(DiskFilePlaceEnum.LOCAL);
        }
        return diskFileHandler;
    }

    @Override
    @IkarosCache
    public DBFile findOne(Example<DBFile> dbFileExample) {
        Optional<DBFile> dbFileOptional = dbFileDao.findOne(dbFileExample);
        return dbFileOptional.isPresent()?dbFileOptional.get():null;
    }

    @Override
    @IkarosCache
    public List<DBFile> findList(Example<DBFile> dbFileExample) {
        return dbFileDao.findAll(dbFileExample);
    }

    @Override
    public DBFile upload(MultipartFile file) throws IOException {
        return dbFileDao.saveAndFlush(diskFileHandler.uploadFile(file));
    }

    @Override
    public void deleteFileById(Long fileId) throws IOException {
        // 根据id获取dbFile
        Optional<DBFile> dbFileOptional = dbFileDao.findById(fileId);
        if(dbFileOptional.isEmpty()) throw new IkarosNotFoundException("查询不到该文件：fileId="+fileId);
        DBFile dbFile = dbFileOptional.get();

        // 动态获取文件处理器
        DiskFileHandler diskFileHandler = getDiskFileHandlerByDiskFilePlace(dbFile.getPlace());

        // 调用文件处理器删除文件
        diskFileHandler.deleteFile(dbFile.getRelativePath());

        // 删除数据库对应文件项目
        dbFileDao.delete(dbFile);
    }
}
