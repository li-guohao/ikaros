package cn.liguohao.ikaros.util;


import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: TokenUtils
 * @Description: token工具类
 * @author: liguohao
 * @date: 2020-7-18 1:10:00
 */
public class TokenUtils {

    /**
     * 随机生成token
     * @return token字符串
     */
    public static String gengerToken(){
        //随机生成token字符串
        return UUIDUtils.getUUID64();
    }

    /**
     * 生成token放入session
     * @param request
     * @param tokenServerkey-服务器本地session中对应的token键
     * @return 生成的token
     */
    public static String createToken(HttpServletRequest request, String tokenServerkey){
        String token = gengerToken();
        request.getSession().setAttribute(tokenServerkey,token);
        request.getServletContext().setAttribute(tokenServerkey,token);
        System.out.println(request.getSession().getId());
        System.out.println(request.getServletContext().getAttribute(tokenServerkey));
        return token;
    }

    /**
     * 移除指定token
     * @param request
     * @param tokenServerkey
     */
    public static void removeToken(HttpServletRequest request,String tokenServerkey){
        request.getSession().removeAttribute(tokenServerkey);
    }

    /**
     * 判断请求参数中的token是否和 用户专属token中一致
     * @param request
     * @param userToken 用户专属token
     * @return true-代表一致 false-代表不一致
     */
    public static boolean judgeTokenIsEqual(HttpServletRequest request,String userToken){
        // 获取请求中的token
        String token = request.getHeader("Authorization");
        //非空判断
        if(token==null || "".equals(token)) return false;
        if(userToken==null || "".equals(userToken)) return false;
        //判断是否相等
        if(userToken.equals(token)) return true;
        return false;
    }
}
