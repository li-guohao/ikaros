package cn.liguohao.ikaros.controller;

import cn.liguohao.ikaros.enums.Oath2Enum;
import cn.liguohao.ikaros.exception.IllegalArgumentIkarosException;
import cn.liguohao.ikaros.service.Oauth2Service;
import cn.liguohao.ikaros.util.IkarosAssert;
import cn.liguohao.ikaros.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Optional;

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
     * @param oath2Enum 要请求的目标 具体看下方的枚举
     * @return 对应的ftl名称
     * @see Oath2Enum
     */
    @GetMapping("/auth/{oath2Enum}")
    public String auth(@PathVariable String oath2Enum) {
        IkarosAssert.isNotEmpty(oath2Enum, "oath2Enum 为空");
        Optional<Oath2Enum> oath2EnumOptional = Arrays.stream(Oath2Enum.values()).filter(oe -> oe.name().equals(oath2Enum)).findFirst();
        IkarosAssert.isTrue(oath2EnumOptional.isPresent(), "不存在的Oath2验证方式 ==> " + oath2Enum);
        return "redirect:" + oauth2Service.getAuthUrl(oath2EnumOptional.get());
    }
}
