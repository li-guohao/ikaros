package cn.liguohao.ikaros.service.impl;

import cn.liguohao.ikaros.annotation.IkarosCache;
import cn.liguohao.ikaros.dao.DBFileDao;
import cn.liguohao.ikaros.service.DBFileService;
import cn.liguohao.ikaros.store.cache.CacheStore;
import cn.liguohao.ikaros.store.database.DBFile;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;
import java.util.Optional;

/**数据库文件服务层实现类
 * @author liguohao_cn
 * @date 2021/1/2
 */
@Service
public class DBFileServiceImpl implements DBFileService {

    @Autowired
    private DBFileDao dbFileDao;

    @Override
    @Transactional
    public void save(DBFile dbFile) {
        dbFileDao.save(dbFile);
    }

    @Override
    @IkarosCache
    public DBFile findOne(Example<DBFile> dbFileExample) {
        return dbFileDao.findOne(dbFileExample).get();
    }

    @Override
    @IkarosCache
    public List<DBFile> findList(Example<DBFile> dbFileExample) {
        return dbFileDao.findAll(dbFileExample);
    }
}
