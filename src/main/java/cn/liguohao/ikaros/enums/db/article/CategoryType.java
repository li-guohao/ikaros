package cn.liguohao.ikaros.enums.db.article;

/**分类类型
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/2/13
 */
public enum CategoryType {

    /**
     * 文章分类
      */
    ARTICLE("article");

    CategoryType(String name) {
        this.name = name;
    }

    /**
     * 名称
     */
    private String name;

    public String getName() {
        return name;
    }

    public CategoryType setName(String name) {
        this.name = name;
        return this;
    }
}
