package cn.liguohao.ikaros.service;

import cn.liguohao.ikaros.annotation.IkarosCache;
import cn.liguohao.ikaros.annotation.IkarosUpdateCache;
import cn.liguohao.ikaros.dao.ConfigDao;
import cn.liguohao.ikaros.dao.DBFileDao;
import cn.liguohao.ikaros.enums.db.config.ConfigType;
import cn.liguohao.ikaros.enums.db.config.DiskFileKey;
import cn.liguohao.ikaros.enums.db.config.DiskFileStrategyValue;
import cn.liguohao.ikaros.exception.IkarosNotFoundException;
import cn.liguohao.ikaros.exception.UserOperateException;
import cn.liguohao.ikaros.store.database.Config;
import cn.liguohao.ikaros.store.database.DBFile;
import cn.liguohao.ikaros.store.diskfile.builder.DiskFileHandlerBuilder;
import cn.liguohao.ikaros.store.diskfile.handler.DiskFileHandler;
import cn.liguohao.ikaros.util.StringUtils;
import cn.liguohao.ikaros.vo.PageQuery;
import cn.liguohao.ikaros.vo.PagingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * 数据库文件服务层
 *
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/1/2
 */
@Service
public class DBFileService {

    @Autowired
    private DBFileDao dbFileDao;
    @Autowired
    private DiskFileHandlerBuilder diskFileHandlerBuilder;
    @Autowired
    private ConfigDao configDao;



    /**
     * 根据应用设置获取磁盘文件处理器的具体实现类
     * @return 磁盘文件处理器的具体实现类
     */
    private DiskFileHandler getApplicationSettingDiskFileHandler(){
        // 查询系统设置
        Optional<Config> configOptional = configDao.findOne(Example.of(
                Config.build().setType(ConfigType.DISK_FILE.name())
                        .setKey(DiskFileKey.STRATEGY.name())
        ));
        return getDiskFileHandlerByDiskFilePlace(DiskFileStrategyValue.valueOf(configOptional.get().getValue()));
    }

    /**
     * 根据 磁盘文件储存策略 动态获取 磁盘文件处理器具体实现类
     * @param diskFileStrategyValue 磁盘文件储存策略值
     * @return 磁盘文件处理器具体实现类
     */
    private DiskFileHandler getDiskFileHandlerByDiskFilePlace(DiskFileStrategyValue diskFileStrategyValue){
        return diskFileHandlerBuilder.builderDefiniteDiskFileHandler(diskFileStrategyValue);
    }

    /**
     * 上传文件
     * @param file 待上传的文件[MultipartFile]格式
     * @return 文件记录
     * @throws IOException 文件操作异常
     */
    @IkarosUpdateCache
    public DBFile upload(MultipartFile file) throws IOException {
        return dbFileDao.save(getApplicationSettingDiskFileHandler().uploadFile(file));
    }

    /**
     * 调用磁盘处理器 删除对应的文件
     * @param id 文件ID
     * @return 是否删除成功
     * @throws IOException IO异常
     */
    @IkarosUpdateCache
    public boolean deleteById(Long id) throws IOException {
        // 根据id获取dbFile
        Optional<DBFile> dbFileOptional = dbFileDao.findById(id);
        if(dbFileOptional.isEmpty()) {throw new IkarosNotFoundException("查询不到该文件：fileId="+id);}
        DBFile dbFile = dbFileOptional.get();

        // 动态获取文件处理器
        DiskFileHandler diskFileHandler = getDiskFileHandlerByDiskFilePlace(DiskFileStrategyValue.valueOf(dbFile.getPlace()));

        // 调用文件处理器删除文件
        diskFileHandler.deleteFile(dbFile.getRelativePath());

        // 删除数据库对应文件项目
        dbFileDao.delete(dbFile);

        return true;
    }

    /**
     * 构建分页条件
     * @param searchEntity 查询参数
     * @return 分页条件
     */
    public Specification<DBFile> buildSpecification(DBFile searchEntity) {
        return new Specification<DBFile>() {
            @Override
            public Predicate toPredicate(Root<DBFile> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = null;

                // 根据文件名称模糊查询
                if(!StringUtils.isEmpty(searchEntity.getOriginalName())){
                    Predicate originalNamePredicate = criteriaBuilder.like(root.get("originalName"), "%" + searchEntity.getOriginalName() + "%");
                    if(predicate==null){
                        predicate = originalNamePredicate;
                    }else {
                        predicate = criteriaBuilder.and(predicate,originalNamePredicate);
                    }
                }

                // 根据文件后缀名模糊查询
                if(!StringUtils.isEmpty(searchEntity.getSuffix())){
                    Predicate suffixPredicate = criteriaBuilder.like(root.get("suffix"),  "%" + searchEntity.getSuffix()+"%");
                    if(predicate==null){
                        predicate = suffixPredicate;
                    }else {
                        predicate = criteriaBuilder.and(predicate,suffixPredicate);
                    }
                }

                // 根据文件路径模糊查询
                if(!StringUtils.isEmpty(searchEntity.getWebUrl())){
                    Predicate urlPredicate = criteriaBuilder.like(root.get("webUrl"), "%" + searchEntity.getWebUrl() + "%");
                    if(predicate==null){
                        predicate = urlPredicate;
                    }else {
                        predicate = criteriaBuilder.and(predicate,urlPredicate);
                    }
                }

                // 根据文件储存位置模糊查询
                if(!StringUtils.isEmpty(searchEntity.getPlace())){
                    Predicate placePredicate = criteriaBuilder.like(root.get("place"), "%" + searchEntity.getPlace() + "%");
                    if(predicate==null){
                        predicate = placePredicate;
                    }else {
                        predicate = criteriaBuilder.and(predicate,placePredicate);
                    }
                }

                return predicate;
            }
        };
    }

    /**
     *
     * @param dbFile 查询条件
     * @return 结果
     */
    @IkarosCache
    public DBFile findOne(DBFile dbFile) {
        return dbFileDao.findOne(Example.of(dbFile)).get();
    }

    /**
     * @param dbFile 查询条件
     * @return 结果List集合
     */
    @IkarosCache
    public List<DBFile> findAll(DBFile dbFile) {
        return dbFileDao.findAll(Example.of(dbFile));
    }

    /**
     * 分页查询
     * @param pageQuery 查询参数
     * @return 分页结果集
     */
    @IkarosCache
    public PagingData<DBFile> findByPaging(PageQuery<DBFile> pageQuery) {

        // 获取对象
        DBFile searchEntity = pageQuery.getOriginal();
        Integer currentPage = pageQuery.getCurrentPage();
        Integer pageSize = pageQuery.getPageSize();
        //校验合法性
        if(ObjectUtils.isEmpty(searchEntity) || ObjectUtils.isEmpty(currentPage) || ObjectUtils.isEmpty(pageSize) || currentPage<0 || pageSize<=0)  {throw new UserOperateException("请传入正确的分页参数 ");}
        //构建查询条件
        Specification<DBFile> specification = buildSpecification(searchEntity);

        // 构建分页条件
        Pageable pageable = PageRequest.of(currentPage-1,pageSize);

        // 查询数据库
        Page<DBFile> page = dbFileDao.findAll(specification, pageable);

        // 构建返回条件
        PagingData<DBFile> pagingData = new PagingData<DBFile>();
        pagingData.setCurrentPage(currentPage);
        pagingData.setPageSize(pageSize);
        pagingData.setTotal((int) dbFileDao.count(specification));
        pagingData.setDataArray(page.getContent());
        // 返回结果
        return pagingData;
    }

    /**
     * 保存
     * @param dbFile 待保存的对象
     */
    @IkarosUpdateCache
    public void save(DBFile dbFile) {
        dbFileDao.save(dbFile);
    }
}
