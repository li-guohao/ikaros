package cn.liguohao.ikaros.dao;

import cn.liguohao.ikaros.store.database.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**用户DAO
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2020/12/31
 */
public interface UserDao extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {


}
