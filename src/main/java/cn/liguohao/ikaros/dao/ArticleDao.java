package cn.liguohao.ikaros.dao;

import cn.liguohao.ikaros.store.database.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**文章(专栏)Dao
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/2/13
 */
public interface ArticleDao extends JpaRepository<Article,Long>, JpaSpecificationExecutor<Article> {
}
