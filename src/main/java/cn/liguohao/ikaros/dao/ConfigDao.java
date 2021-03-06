package cn.liguohao.ikaros.dao;

import cn.liguohao.ikaros.store.database.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**系统配置DAO
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2020/12/31
 */
public interface ConfigDao extends JpaRepository<Config,Long>, JpaSpecificationExecutor<Config> {


}
