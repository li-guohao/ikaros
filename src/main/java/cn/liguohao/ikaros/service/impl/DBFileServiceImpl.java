package cn.liguohao.ikaros.service.impl;

import cn.liguohao.ikaros.annotation.IkarosCache;
import cn.liguohao.ikaros.annotation.IkarosUpdateCache;
import cn.liguohao.ikaros.constant.ConfigItemEnum;
import cn.liguohao.ikaros.constant.DiskFilePlaceEnum;
import cn.liguohao.ikaros.dao.DBFileDao;
import cn.liguohao.ikaros.exception.IkarosNotFoundException;
import cn.liguohao.ikaros.exception.UserOperateException;
import cn.liguohao.ikaros.service.ConfigService;
import cn.liguohao.ikaros.service.DBFileService;
import cn.liguohao.ikaros.store.database.Config;
import cn.liguohao.ikaros.store.database.DBFile;
import cn.liguohao.ikaros.store.diskfile.builder.DiskFileHandlerBuilder;
import cn.liguohao.ikaros.store.diskfile.handler.DiskFileHandler;
import cn.liguohao.ikaros.util.StringUtils;
import cn.liguohao.ikaros.vo.PageQuery;
import cn.liguohao.ikaros.vo.PagingData;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
    @IkarosUpdateCache
    public DBFile upload(MultipartFile file) throws IOException {
        return dbFileDao.saveAndFlush(diskFileHandler.uploadFile(file));
    }

    @Override
    @IkarosUpdateCache
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

    /**
     * @see DBFileService#findByFileId(Long)
     */
    @Override
    @IkarosCache
    public DBFile findByFileId(Long fileId) {
        Optional<DBFile> dbFileOptional = dbFileDao.findById(fileId);
        return dbFileOptional.isPresent()?dbFileOptional.get():null;
    }

    /**
     * @see DBFileService#findDBFilesByPaging(cn.liguohao.ikaros.vo.PageQuery)
     */
    @Override
    @IkarosCache
    public PagingData<DBFile> findDBFilesByPaging(PageQuery<DBFile> dbFilePageQuery) {
        // 获取对象
        DBFile dbFileSearch = dbFilePageQuery.getOriginal();
        Integer currentPage = dbFilePageQuery.getCurrentPage();
        Integer pageSize = dbFilePageQuery.getPageSize();
        //校验合法性
        if(ObjectUtils.isEmpty(dbFileSearch) || ObjectUtils.isEmpty(currentPage) || ObjectUtils.isEmpty(pageSize) || currentPage<0 || pageSize<=0)  throw new UserOperateException("请传入正确的分页参数 ");
        //构建查询条件
        Specification<DBFile> fileSpecification = new Specification<DBFile>() {
            @Override
            public Predicate toPredicate(Root<DBFile> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = null;

                // 根据文件名称模糊查询
                if(!StringUtils.isEmpty(dbFileSearch.getOriginalName())){
                    Predicate originalNamePredicate = criteriaBuilder.like(root.get("originalName"), "%" + dbFileSearch.getOriginalName() + "%");
                    if(predicate==null){
                        predicate = originalNamePredicate;
                    }else {
                        predicate = criteriaBuilder.and(predicate,originalNamePredicate);
                    }
                }

                // 根据文件后缀名模糊查询
                if(!StringUtils.isEmpty(dbFileSearch.getSuffix())){
                    Predicate suffixPredicate = criteriaBuilder.like(root.get("suffix"),  "%" + dbFileSearch.getSuffix()+"%");
                    if(predicate==null){
                        predicate = suffixPredicate;
                    }else {
                        predicate = criteriaBuilder.and(predicate,suffixPredicate);
                    }
                }

                // 根据文件路径模糊查询
                if(!StringUtils.isEmpty(dbFileSearch.getWebUrl())){
                    Predicate urlPredicate = criteriaBuilder.like(root.get("webUrl"), "%" + dbFileSearch.getWebUrl() + "%");
                    if(predicate==null){
                        predicate = urlPredicate;
                    }else {
                        predicate = criteriaBuilder.and(predicate,urlPredicate);
                    }
                }

                return predicate;
            }
        };

        // 构建分页条件
        Pageable pageable = PageRequest.of(currentPage-1,pageSize);

        // 查询数据库
        Page<DBFile> pageFile = dbFileDao.findAll(fileSpecification, pageable);

        // 构建返回条件
        PagingData<DBFile> pagingData = new PagingData<>();
        pagingData.setCurrentPage(currentPage);
        pagingData.setPageSize(pageSize);
        pagingData.setTotal((int)pageFile.getTotalElements());
        pagingData.setDataArray(pageFile.getContent());
        // 返回结果
        return pagingData;
    }
}
