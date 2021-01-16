package cn.liguohao.ikaros.service;

import cn.liguohao.ikaros.store.database.Config;
import cn.liguohao.ikaros.vo.PageQuery;
import cn.liguohao.ikaros.vo.PagingData;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.List;

/**基础服务接口
 * 封装一些通用的方法
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/1/16
 */
public interface BaseService<E> {

    /**
     * 保存或更新
     * @param entity 系统配置对象
     * @return 是否操作成功
     */
    boolean save(E entity);

    /**
     * 根据ID查询
     * @param id ID
     * @return 对应的实体类对象
     */
    E findById(Long id);

    /**
     * 根据ID删除
     * @param id ID
     * @return 对应的实体类对象
     */
    boolean deleteById(Long id) throws IOException;

    /**
     * 分页查询
     * @param pageQuery 分页查询对象
     * @return 查询到的分页结果
     */
    PagingData<E> findByPaging(PageQuery<E> pageQuery);

    /**
     * 查询所有
     * @return 集合
     */
    List<E> findAll();

    /**
     * 构建具体的分页条件
     * @return 具体的分页添加对象
     */
    default Specification<E> buildSpecification(){
        return new Specification<E>() {
            @Override
            public Predicate toPredicate(Root<E> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
    }
}
