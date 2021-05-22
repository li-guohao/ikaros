package cn.liguohao.ikaros.rest.controller;

import cn.liguohao.ikaros.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**文章(专栏)
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/2/13
 */
@RestController
@RequestMapping("/article")
public class ArticleRestRestController {

    @Autowired
    private ArticleService articleService;

}
