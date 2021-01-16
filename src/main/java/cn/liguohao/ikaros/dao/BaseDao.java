package cn.liguohao.ikaros.dao;

import cn.liguohao.ikaros.store.database.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**基础Dao层接口
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/1/16
 */
@NoRepositoryBean
public interface BaseDao<E> extends JpaRepository<E,Long>, JpaSpecificationExecutor<E> {
}
