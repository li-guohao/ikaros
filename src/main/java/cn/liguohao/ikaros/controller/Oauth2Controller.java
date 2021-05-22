package cn.liguohao.ikaros.controller;

import cn.liguohao.ikaros.enums.Oath2Enum;
import cn.liguohao.ikaros.exception.IllegalArgumentIkarosException;
import cn.liguohao.ikaros.service.Oauth2Service;
import cn.liguohao.ikaros.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 获取第三方授权信息
 *
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/5/22
 */
@Controller
@RequestMapping("/oath2")
public class Oauth2Controller {

    @Autowired
    private Oauth2Service oauth2Service;

    /**
     * 获取授权信息
     *
     * @param target 要请求的目标 具体看下方的枚举
     * @return 对应的ftl名称
     * @see Oath2Enum
     */
    @GetMapping("/auth/{target}")
    public String auth(@PathVariable String target) {
        Oath2Enum oath2Enum = null;
        Oath2Enum[] oath2Enums = Oath2Enum.values();
        for( Oath2Enum tempOath2Enum : oath2Enums) {
            if(tempOath2Enum.name().equalsIgnoreCase(target)) {
                oath2Enum = tempOath2Enum;
            }
        }

        if(StringUtils.isEmpty(target) || oath2Enum == null) {
            throw new IllegalArgumentIkarosException("参数不合法 ==> target=" + target);
        }

        return "redirect:" + oauth2Service.getAuthUrl(oath2Enum);
    }
}
