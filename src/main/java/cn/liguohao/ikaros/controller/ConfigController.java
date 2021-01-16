package cn.liguohao.ikaros.controller;

import cn.liguohao.ikaros.service.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**系统配置
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/1/16
 */
@RestController
@RequestMapping("/config")
public class ConfigController {

    /**
     * 日志对象
     */
    private Logger logger = LoggerFactory.getLogger(ConfigController.class);


    @Autowired
    private ConfigService configService;


}
