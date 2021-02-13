package cn.liguohao.ikaros.controller;

import cn.liguohao.ikaros.service.ArticleService;
import cn.liguohao.ikaros.store.database.Article;
import cn.liguohao.ikaros.store.database.Config;
import cn.liguohao.ikaros.vo.PageQuery;
import cn.liguohao.ikaros.vo.PagingData;
import cn.liguohao.ikaros.vo.Result;
import cn.liguohao.ikaros.vo.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**文章(专栏)
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/2/13
 */
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController<Article>{

    @Autowired
    private ArticleService articleService;

}
