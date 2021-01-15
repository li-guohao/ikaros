package cn.liguohao.ikaros.service;

import cn.liguohao.ikaros.store.database.DBFile;
import org.springframework.data.domain.Example;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**文件数据服务层
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/1/2
 */
public interface DBFileService {

    /**
     * 保存
     * @param dbFile 文件数据
     */
    void save(DBFile dbFile);

    /**
     * 查询文件数据
     * @param dbFileExample 查询条件
     * @return 文件数据
     */
    DBFile findOne(Example<DBFile> dbFileExample);

    /**
     * 查询数据库文件
     * @param dbFileExample 查询条件
     * @return 文件数据集合
     */
    List<DBFile> findList(Example<DBFile> dbFileExample);

    /**
     * 上传文件
     * @param file 待上传的文件[MultipartFile]格式
     * @return 文件记录
     */
    DBFile upload(MultipartFile file) throws IOException;
}
