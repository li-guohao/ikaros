package cn.liguohao.ikaros.vo;

import java.util.List;

/**分页数据
 * @author liguohao_cn
 * @date 2020/12/31
 */
public class PagingData<T> {
    /**
     * 总数
     */
    private Integer total;

    /**
     * 当前页码
     */
    private Integer currentPage;

    /**
     * 每页显示条数
     */
    private Integer pageSize;

    /**
     * 数据数组
     */
    private List<T> dataArray;

    public PagingData() {}

    public static PagingData build(){return new PagingData();}

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

    public PagingData setTotal(Integer total) {
        this.total = total;
        return this;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public PagingData setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public PagingData setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public List<T> getDataArray() {
        return dataArray;
    }

    public PagingData setDataArray(List<T> dataArray) {
        this.dataArray = dataArray;
        return this;
    }
}
