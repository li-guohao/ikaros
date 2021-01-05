package cn.liguohao.ikaros.dao;

import cn.liguohao.ikaros.store.database.Config;
import cn.liguohao.ikaros.store.database.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**用户DAO
 * @author liguohao_cn
 * @since 2020/12/31
 */
public interface UserDao extends JpaRepository<User,Long> {


}
