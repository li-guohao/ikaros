package cn.liguohao.ikaros.vo;

/**分页查询对象
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/1/16
 */
public class PageQuery<T>  {
    /**
     * 源条件对象
     */
    private T original;

    /**
     * 当前页
     */
    private Integer currentPage;
    /**
     * 每页显示条数
     */
    private Integer pageSize;

    public T getOriginal() {
        return original;
    }

    public PageQuery<T> setOriginal(T original) {
        this.original = original;
        return this;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public PageQuery<T> setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public PageQuery<T> setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}
