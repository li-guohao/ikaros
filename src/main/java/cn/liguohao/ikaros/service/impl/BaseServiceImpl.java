package cn.liguohao.ikaros.service.impl;

import cn.liguohao.ikaros.annotation.IkarosCache;
import cn.liguohao.ikaros.annotation.IkarosUpdateCache;
import cn.liguohao.ikaros.dao.BaseDao;
import cn.liguohao.ikaros.exception.UserOperateException;
import cn.liguohao.ikaros.service.BaseService;
import cn.liguohao.ikaros.store.database.DBFile;
import cn.liguohao.ikaros.util.StringUtils;
import cn.liguohao.ikaros.vo.PageQuery;
import cn.liguohao.ikaros.vo.PagingData;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
        if(ObjectUtils.isEmpty(searchEntity) || ObjectUtils.isEmpty(currentPage) || ObjectUtils.isEmpty(pageSize) || currentPage<0 || pageSize<=0)  throw new UserOperateException("请传入正确的分页参数 ");
        //构建查询条件
        Specification<E> fileSpecification = buildSpecification(searchEntity);

        // 构建分页条件
        Pageable pageable = PageRequest.of(currentPage-1,pageSize);

        // 查询数据库
        Page<E> page = baseDao.findAll(fileSpecification, pageable);

        // 构建返回条件
        PagingData<E> pagingData = new PagingData<>();
        pagingData.setCurrentPage(currentPage);
        pagingData.setPageSize(pageSize);
        pagingData.setTotal((int) baseDao.count());
        pagingData.setDataArray(page.getContent());
        // 返回结果
        return pagingData;
    }

    @Override
    @IkarosCache
    public List<E> findAll() {
        return baseDao.findAll();
    }
}
