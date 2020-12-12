package cn.liguohao.ikaros.service.impl;

import cn.liguohao.ikaros.config.ServerConfig;
import cn.liguohao.ikaros.constant.DiskFilePlace;
import cn.liguohao.ikaros.dao.FileDao;
import cn.liguohao.ikaros.dao.SettingDao;
import cn.liguohao.ikaros.diskfile.builder.DiskFileHandlerBuilder;
import cn.liguohao.ikaros.diskfile.handler.DiskFileHandler;
import cn.liguohao.ikaros.exception.DataNotExistException;
import cn.liguohao.ikaros.exception.UserOperateException;
import cn.liguohao.ikaros.response.PagingData;
import cn.liguohao.ikaros.service.FileService;
import cn.liguohao.ikaros.store.Setting;
import cn.liguohao.ikaros.store.File;
import cn.liguohao.ikaros.util.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

/**
 * @Description TODO
 * @Author liguohao
 * @Date 2020/11/25 20:04
 */
@Service
@Transactional
public class FileServiceImpl implements FileService {

    @Autowired
    private FileDao fileDao;
    @Autowired
    private SettingDao settingDao;
    @Autowired
    private DiskFileHandlerBuilder diskFileHandlerBuilder;

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    /**
     * 根据应用设置获取磁盘文件处理器的具体实现类
     * @return 磁盘文件处理器的具体实现类
     */
    private DiskFileHandler getApplicationSettingDiskFileHandler(){
        // 查询系统设置
        Setting diskFilePlaceSetting = settingDao.findSettingByName("disk_file_place");
        if(ObjectUtils.isEmpty(diskFilePlaceSetting)) throw new DataNotExistException("不存在设置项 ==> disk_file_place");
        // 根据应用设置动态获取 磁盘文件处理器的具体实现类
        return getDiskFileHandlerByDiskFilePlace(diskFilePlaceSetting.getValue());
    }

    /**
     * 根据 磁盘文件位置 动态获取 磁盘文件处理器具体实现类
     * @param diskFilePlace 磁盘文件储存位置
     * @return 磁盘文件处理器具体实现类
     */
    private DiskFileHandler getDiskFileHandlerByDiskFilePlace(String diskFilePlace){
        DiskFileHandler diskFileHandler = null;
        switch (diskFilePlace.toLowerCase()){
            case "local":
                diskFileHandler = diskFileHandlerBuilder.builderDefiniteDiskFileHandler(DiskFilePlace.LOCAL);
                break;
            case "aliyun_oss":
                diskFileHandler = diskFileHandlerBuilder.builderDefiniteDiskFileHandler(DiskFilePlace.ALIYUN_OSS);
                break;
            case "tencent_cloud_cos":
                diskFileHandler = diskFileHandlerBuilder.builderDefiniteDiskFileHandler(DiskFilePlace.TENCENT_CLOUD_COS);
                break;
            default:
                diskFileHandler = diskFileHandlerBuilder.builderDefiniteDiskFileHandler(DiskFilePlace.LOCAL);
        }
        return diskFileHandler;
    }


    @Override
    public File uploadFile(MultipartFile multipartFile) throws IOException{

        // 动态获取文件处理器
        DiskFileHandler diskFileHandler = getApplicationSettingDiskFileHandler();

        // 调用处理器上传文件 获取数据库文件对象
        File dbFile = diskFileHandler.uploadFile(multipartFile);

        // 保存到数据库
        return fileDao.save(dbFile);
    }

    @Override
    public void deleteFileById(Long fileId) throws IOException {
        // 根据id获取dbFile
        Optional<File> dbFileOptional = fileDao.findById(fileId);
        if(ObjectUtils.isEmpty(dbFileOptional)) throw new DataNotExistException("查询不到该文件：fileId="+fileId);
        File dbFile = dbFileOptional.get();

        // 动态获取文件处理器
        DiskFileHandler diskFileHandler = getDiskFileHandlerByDiskFilePlace(dbFile.getPlace());

        // 调用文件处理器删除文件
        diskFileHandler.deleteFile(dbFile.getRelativePath());

        // 删除数据库对应文件项目
        fileDao.delete(dbFile);

    }


    @Override
    public PagingData<cn.liguohao.ikaros.store.File> findByPaging(cn.liguohao.ikaros.store.File file, Integer currentPage, Integer pageSize) {
        //校验合法性
        if(ObjectUtils.isEmpty(file) || ObjectUtils.isEmpty(currentPage) || ObjectUtils.isEmpty(pageSize) || currentPage<0 || pageSize<=0)  throw new UserOperateException("请传入正确的分页参数 ");
        //构建查询条件
        Specification<cn.liguohao.ikaros.store.File> fileSpecification = new Specification<cn.liguohao.ikaros.store.File>() {
            @Override
            public Predicate toPredicate(Root<cn.liguohao.ikaros.store.File> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = null;

                // 根据文件名称模糊查询
                if(!StringUtils.isEmpty(file.getOriginalName())){
                    Predicate originalNamePredicate = criteriaBuilder.like(root.get("originalName"), "%" + file.getOriginalName() + "%");
                    if(predicate==null){
                        predicate = originalNamePredicate;
                    }else {
                        predicate = criteriaBuilder.and(predicate,originalNamePredicate);
                    }
                }

                // 根据文件后缀名模糊查询
                if(!StringUtils.isEmpty(file.getSuffix())){
                    Predicate suffixPredicate = criteriaBuilder.like(root.get("suffix"),  "%" + file.getSuffix()+"%");
                    if(predicate==null){
                        predicate = suffixPredicate;
                    }else {
                        predicate = criteriaBuilder.and(predicate,suffixPredicate);
                    }
                }

                // 根据文件路径模糊查询
                if(!StringUtils.isEmpty(file.getWebUrl())){
                    Predicate urlPredicate = criteriaBuilder.like(root.get("webUrl"), "%" + file.getWebUrl() + "%");
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
        Page<cn.liguohao.ikaros.store.File> pageFile = fileDao.findAll(fileSpecification, pageable);

        // 构建返回条件
        PagingData<cn.liguohao.ikaros.store.File> pagingData = new PagingData<>();
        pagingData.setCurrentPage(currentPage);
        pagingData.setPageSize(pageSize);
        pagingData.setTotal((int)pageFile.getTotalElements());
        pagingData.setDataArray(pageFile.getContent());
        // 返回结果
        return pagingData;
    }

    @Override
    public cn.liguohao.ikaros.store.File findById(Long fileId) {
        Optional<cn.liguohao.ikaros.store.File> dbFileOptional = fileDao.findById(fileId);
        if(ObjectUtils.isEmpty(dbFileOptional)) throw new DataNotExistException("查询不到该文件：fileId="+fileId);
        return dbFileOptional.get();
    }

}
