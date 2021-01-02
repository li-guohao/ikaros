package cn.liguohao.ikaros.dao;

import cn.liguohao.ikaros.store.database.Config;
import org.springframework.data.jpa.repository.JpaRepository;

/**系统配置DAO
 * @author liguohao_cn
 * @date 2020/12/31
 */
public interface ConfigDao extends JpaRepository<Config,Long> {


}
