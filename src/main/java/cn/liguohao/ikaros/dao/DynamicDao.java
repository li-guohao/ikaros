package cn.liguohao.ikaros.dao;

import cn.liguohao.ikaros.store.Dynamic;
import cn.liguohao.ikaros.store.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**动态表数据库持久层
 * @Author liguohao
 * @Date 2020/11/25 20:24
 */
public interface DynamicDao extends JpaRepository<Dynamic,Long>, PagingAndSortingRepository<Dynamic,Long>, JpaSpecificationExecutor<Dynamic> {



}
