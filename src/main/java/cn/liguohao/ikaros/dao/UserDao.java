package cn.liguohao.ikaros.dao;

import cn.liguohao.ikaros.store.database.Config;
import cn.liguohao.ikaros.store.database.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author liguohao_cn
 * @date 2020/12/31
 */
public interface UserDao extends JpaRepository<User,Long> {


}
