package cn.liguohao.ikaros.service;

import cn.liguohao.ikaros.response.PagingData;
import cn.liguohao.ikaros.store.Dynamic;

/**动态服务层
 * @Auther: liguohao
 * @Date: 2020/11/30 15:20
 */
public interface DynamicService {

    /**
     * 保存和更新动态
     * @param dynamic 待保存和更新的动态
     */
    Dynamic save(Dynamic dynamic);

    /**
     * 删除动态
     * @param dynamic 待删除的动态
     */
    void delete(Dynamic dynamic);

    /**
     * 分页查询动态(动态查询不带条件，只有分页参数)
     * @param currentPage 当前页
     * @param pageSize 每页显示条数
     * @return 分页数据
     */
    PagingData<Dynamic> findByPaging(Integer currentPage,Integer pageSize);

    /**
     * 删除动态，根据动态ID
     * @param dynamicId 动态ID
     */
    void deleteById(Long dynamicId);

    /**
     * 查询动态，根据动态ID
     * @param dynamicId 动态ID
     * @return 查询到的动态
     */
    Dynamic findById(Long dynamicId);
}
