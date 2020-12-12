package cn.liguohao.ikaros.service.impl;

import cn.liguohao.ikaros.dao.DynamicDao;
import cn.liguohao.ikaros.exception.DataNotExistException;
import cn.liguohao.ikaros.exception.UserOperateException;
import cn.liguohao.ikaros.response.PagingData;
import cn.liguohao.ikaros.service.DynamicService;
import cn.liguohao.ikaros.store.Dynamic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

/**动态服务层具体实现类
 * @Auther: liguohao
 * @Date: 2020/11/30 15:22
 */
@Service
public class DynamicServiceImpl implements DynamicService {

    @Autowired
    private DynamicDao dynamicDao;

    @Override
    public Dynamic save(Dynamic dynamic) {
        if(ObjectUtils.isEmpty(dynamic) ) throw new UserOperateException("请传入<动态>的数据 ==> "+Dynamic.class.getSimpleName());
        return dynamicDao.save(dynamic);
    }

    @Override
    public void delete(Dynamic dynamic) {
        if(ObjectUtils.isEmpty(dynamic) || ObjectUtils.isEmpty(dynamic.getDynamicId())) throw new UserOperateException("请传入正确的参数 ==> "+Dynamic.class.getSimpleName());
        dynamicDao.delete(dynamic);
    }

    @Override
    public PagingData<Dynamic> findByPaging(Integer currentPage, Integer pageSize) {
        if(ObjectUtils.isEmpty(currentPage) || ObjectUtils.isEmpty(pageSize) || currentPage<0 || pageSize<=0)  throw new UserOperateException("请传入正确的分页参数 ");
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        Page<Dynamic> dynamics = dynamicDao.findAll(pageable);
        // 封装参数
        PagingData<Dynamic> dynamicPagingData = new PagingData<>();
        dynamicPagingData.setCurrentPage(currentPage);
        dynamicPagingData.setPageSize(pageSize);
        dynamicPagingData.setTotal((int)dynamics.getTotalElements());
        dynamicPagingData.setDataArray(dynamics.getContent());
        return dynamicPagingData;
    }

    @Override
    public void deleteById(Long dynamicId) {
        Dynamic dynamic = findById(dynamicId);
        delete(dynamic);
    }

    @Override
    public Dynamic findById(Long dynamicId) {
        // 查询是否存在
        Optional<Dynamic> optional = dynamicDao.findById(dynamicId);
        if(ObjectUtils.isEmpty(optional)) throw new DataNotExistException("查询不到该动态：dynamicId="+dynamicId);
        return optional.get();
    }
}
