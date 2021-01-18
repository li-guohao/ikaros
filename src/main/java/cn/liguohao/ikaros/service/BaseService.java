package cn.liguohao.ikaros.service;

import cn.liguohao.ikaros.vo.PageQuery;
import cn.liguohao.ikaros.vo.PagingData;
import org.springframework.data.domain.Example;
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
     * 条件查询单个
     * @param example 查询条件
     * @return 对应的实体类对象
     */
    E findOne(Example<E> example);

    /**
     * 条件查询多个
     * @param example 查询条件
     * @return 对应的实体类集合对象
     */
    List<E> findAll(Example<E> example);

    /**
     * 根据ID删除
     * @param id ID
     * @return 对应的实体类对象
     * @throws IOException IO操作异常
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
    default Specification<E> buildSpecification(E searchEntity){
        return new Specification<E>() {
            @Override
            public Predicate toPredicate(Root<E> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
    }

    /**
     * 根据ID获取
     * @param id 主键ID
     * @return 对应的实体类对象
     */
    E getOne(Long id);

    /**
     * 是否存在
     * @param example 简单条件
     * @return true-存在 false-不存在
     */
    boolean exists(Example<E> example);

    /**
     * 是否存在
     * @param id 主键ID
     * @return true-存在 false-不存在
     */
    boolean existsById(Long id);

}
