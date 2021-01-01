package cn.liguohao.ikaros.filter;

import cn.liguohao.ikaros.service.UserService;
import cn.liguohao.ikaros.store.cache.CacheStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**权限过滤器
 * @author liguohao_cn
 * @date 2021/1/1
 */
@Component
public class AuthFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Autowired
    private UserService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        chain.doFilter(request,response);
    }
}
