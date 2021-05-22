package cn.liguohao.ikaros.service;

import cn.liguohao.ikaros.enums.Oath2Enum;
import org.springframework.stereotype.Service;

/**
 * Oauth2 验证
 *
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/5/22
 */
@Service
public class Oauth2Service {

    /**
     * 根据验证类型 返回对应的重定向URL
     * @param oath2Enum 验证类型
     * @return 重定向URL
     * @see Oath2Enum
     */
    public String getAuthUrl(Oath2Enum oath2Enum) {
        StringBuilder authUrlBuilder = new StringBuilder();
        authUrlBuilder.append("https://liguohao.cn");
        

        return authUrlBuilder.toString();
    }

}
