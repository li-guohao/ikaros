package cn.liguohao.ikaros.response;

import java.util.List;

/**
 * @ClassName: PagingData
 * @Description: 分页数据
 * @author: liguohao
 * @date: 2020-7-18 1:06:59
 * @param <T>
 */
public class PagingData<T> {

    private Integer total;          //总数

    private Integer currentPage;    //当前页码

    private Integer pageSize;       //每页显示条数

    private List<T> dataArray; //数据数组


    public PagingData() {
    }

    public PagingData(Integer currentPage, Integer pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public PagingData(Integer total, Integer currentPage, Integer pageSize) {
        this.total = total;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public PagingData(Integer total, Integer currentPage, Integer pageSize, List<T> dataArray) {
        this.total = total;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.dataArray = dataArray;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getDataArray() {
        return dataArray;
    }

    public void setDataArray(List<T> dataArray) {
        this.dataArray = dataArray;
    }
}
