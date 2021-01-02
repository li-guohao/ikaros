package cn.liguohao.ikaros.dao;

import cn.liguohao.ikaros.store.database.Config;
import cn.liguohao.ikaros.store.database.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;

/**数据库文件DAO
 * @author liguohao_cn
 * @date 2020/12/31
 */
public interface DBFileDao extends JpaRepository<DBFile,Long> {


}
