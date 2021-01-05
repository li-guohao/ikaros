package cn.liguohao.ikaros.filter;

import cn.liguohao.ikaros.dto.UserDTO;
import cn.liguohao.ikaros.service.UserService;
import cn.liguohao.ikaros.vo.Result;
import cn.liguohao.ikaros.vo.Status;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**全局过滤器 如权限设置
 * @author liguohao_cn
 * @since 2021/1/1
 */
@Component
public class GlobalFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(GlobalFilter.class);

    @Autowired
    private UserService userService;

    @Value("${spring.profiles.active}")
    private String  springProfilesActive;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        // 强转为对应的HTTP类
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // 设置字符集编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 跨域处理
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers","*");
        response.setHeader("ACCEPT","application/json");

        // 获取请求头信息
        String uuid = request.getHeader("UUID");
        String authorization = request.getHeader("Authorization");


        // 是否放行标识符
        boolean isAuth = true;

        // 获取请求路径
        String requestURL = request.getRequestURL().toString();

        // 对特定的URL[api]进行权限控制
        if (requestURL.indexOf("api")>0){
            // 校验Token
            isAuth = userService.checkToken(UserDTO.build()
                    .setType(UserDTO.Type.TOKEN)
                    .setUuid(Long.valueOf(uuid==null?"-1":uuid))
                    .setToken(authorization==null?"authorization":authorization)
            );
            // 如是用户登录则放行
            if(requestURL.indexOf("login")>0) isAuth = true;
        }

        // 测试环境放行
        if("dev".equals(springProfilesActive)) isAuth = true;

        // 根据结果处理
        if(isAuth){
            chain.doFilter(request,response);// 放行
        }else {     //返回结果JSON
            response.setContentType("text/html;charset=utf-8");
            String reponseMsg = JSON.toJSONString(Result.build()
                    .setStatus(Status.serverError)
                    .setMessage("无权访问，请登录后重试")
            );
            response.getWriter().write(reponseMsg);
        }

    }
}
