package cn.liguohao.ikaros.enums;

/**分类类型
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/2/13
 */
public enum CategoryTypeEnum {

    /**
     * 文章分类
      */
    ARTICLE("article");

    CategoryTypeEnum(String name) {
        this.name = name;
    }

    /**
     * 名称
     */
    private String name;

    public String getName() {
        return name;
    }

    public CategoryTypeEnum setName(String name) {
        this.name = name;
        return this;
    }
}
