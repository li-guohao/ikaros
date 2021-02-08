package cn.liguohao.ikaros.service.impl;

import cn.liguohao.ikaros.annotation.IkarosCache;
import cn.liguohao.ikaros.annotation.IkarosUpdateCache;
import cn.liguohao.ikaros.dao.BaseDao;
import cn.liguohao.ikaros.exception.UserOperateException;
import cn.liguohao.ikaros.service.BaseService;
import cn.liguohao.ikaros.util.IkarosAssert;
import cn.liguohao.ikaros.vo.PageQuery;
import cn.liguohao.ikaros.vo.PagingData;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**基础服务层实现类
 * 实现通用的一些方法
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/1/16
 */
public class BaseServiceImpl<E> implements BaseService<E> {

    @Autowired
    private BaseDao<E> baseDao;

    @Override
    @Transactional
    @IkarosUpdateCache
    public boolean save(E entity) {
        baseDao.save(entity);
        return true;
    }

    @Override
    @IkarosCache
    public E findById(Long id) {
        Optional<E> optional = baseDao.findById(id);
        return optional.isPresent()?optional.get():null;
    }

    @Override
    @IkarosCache
    public E findOne(Example<E> example) {
        Optional<E> optional = baseDao.findOne(example);
        return optional.isPresent()?optional.get():null;
    }

    @Override
    @IkarosCache
    public List<E> findAll(Example<E> example) {
        return baseDao.findAll(example);
    }

    @Override
    @Transactional
    @IkarosUpdateCache
    public boolean deleteById(Long id) throws IOException {
        IkarosAssert.isTrue(baseDao.existsById(id),"对应的数据不存在 id=>"+id);
        baseDao.deleteById(id);
        return true;
    }

    @Override
    @IkarosCache
    public PagingData<E> findByPaging(PageQuery<E> pageQuery) {
        // 获取对象
        E searchEntity = pageQuery.getOriginal();
        Integer currentPage = pageQuery.getCurrentPage();
        Integer pageSize = pageQuery.getPageSize();
        //校验合法性
        if(ObjectUtils.isEmpty(searchEntity) || ObjectUtils.isEmpty(currentPage) || ObjectUtils.isEmpty(pageSize) || currentPage<0 || pageSize<=0)  {throw new UserOperateException("请传入正确的分页参数 ");}
        //构建查询条件
        Specification<E> specification = buildSpecification(searchEntity);

        // 构建分页条件
        Pageable pageable = PageRequest.of(currentPage-1,pageSize);

        // 查询数据库
        Page<E> page = baseDao.findAll(specification, pageable);

        // 构建返回条件
        PagingData<E> pagingData = new PagingData<>();
        pagingData.setCurrentPage(currentPage);
        pagingData.setPageSize(pageSize);
        pagingData.setTotal((int) baseDao.count(specification));
        pagingData.setDataArray(page.getContent());
        // 返回结果
        return pagingData;
    }

    @Override
    @IkarosCache
    public List<E> findAll() {
        return baseDao.findAll();
    }


    @Override
    @IkarosCache
    public E getOne(Long id) {
        return baseDao.getOne(id);
    }

    @Override
    @IkarosCache
    public boolean exists(Example<E> example) {
        return baseDao.exists(example);
    }

    @Override
    @IkarosCache
    public boolean existsById(Long id) {
        return baseDao.existsById(id);
    }
}
