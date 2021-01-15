package cn.liguohao.ikaros.service.impl;

import cn.liguohao.ikaros.annotation.IkarosCache;
import cn.liguohao.ikaros.dao.DBFileDao;
import cn.liguohao.ikaros.service.DBFileService;
import cn.liguohao.ikaros.store.database.DBFile;
import cn.liguohao.ikaros.store.diskfile.handler.DiskFileHandler;
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


    @Override
    @Transactional
    public void save(DBFile dbFile) {
        dbFileDao.save(dbFile);
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
    @IkarosCache
    public DBFile upload(MultipartFile file) throws IOException {
        diskFileHandler.uploadFile(file.getBytes());

        return null;
    }
}
