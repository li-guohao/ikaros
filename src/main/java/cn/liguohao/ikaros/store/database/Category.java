package cn.liguohao.ikaros.store.database;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**分类
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/2/13
 */
@Entity
@Table(name = "category")
@EntityListeners(AuditingEntityListener.class)
public class Category {


    /**
     * 分类ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private Long categoryId;


    /**
     * 类型
     * @see cn.liguohao.ikaros.enums.CategoryTypeEnum
     */
    private String type;

    /**
     * 名称
     */
    private String name;

    public Long getCategoryId() {
        return categoryId;
    }

    public Category setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getType() {
        return type;
    }

    public Category setType(String type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public Category setName(String name) {
        this.name = name;
        return this;
    }
}
