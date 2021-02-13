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
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @PutMapping("/one/save")
    public Result<Boolean> save(@RequestBody Article article){
        return Result.build().setDSM(
                articleService.save(article),
                Status.created,"保存或更新成功",
                Status.serverError,"保存或更新失败"
        );
    }

    @PostMapping("/list/paging")
    public Result<PagingData<Config>> findByPaging(@RequestBody PageQuery<Article> articlePageQuery){
        return Result.build().setDSM(
                articleService.findByPaging(articlePageQuery),
                Status.success,"查询成功",
                Status.notFound,"未查询到数据"
        );
    }

    @DeleteMapping("/one/{id}")
    public Result<Boolean> deleteById(@PathVariable("id") Long id) throws IOException {
        return Result.build().setDSM(
                articleService.deleteById(id),
                Status.success,"删除成功",
                Status.serverError,"删除失败"
        );
    }

    @GetMapping("/one/{id}")
    public Result<Boolean> findById(@PathVariable("id") Long id){
        return Result.build().setDSM(
                articleService.findById(id),
                Status.success,"查询成功",
                Status.notFound,"查询失败 => id="+id
        );
    }
}
