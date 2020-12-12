package cn.liguohao.ikaros.controller;

import cn.liguohao.ikaros.service.SettingService;
import cn.liguohao.ikaros.store.Setting;
import cn.liguohao.ikaros.util.HttpClientUtils;
import cn.liguohao.ikaros.util.MultipartFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @Description TODO
 * @Author liguohao
 * @Date 2020/11/25 19:09
 */
@Controller
public class MainController {

    @Autowired
    private SettingService settingService;

    /**
     * 首页
     */
    @RequestMapping({"/","/index"})
    public String index(){
        // 查询是否已经初始化 -- 插入设置项 初始化设置<initialization> ==> <is_init> 默认<false>
        String isInit = settingService.findSettingByTypeAndName("initialization","is_init").getValue();
        // 未初始化 进行初始化操作
        if("false".equals(isInit)) return "redirect:/install";

        // 已初始化查询当前主题
        Setting setting = settingService.findSettingByTypeAndName("theme", "current_theme");
        return setting.getValue() + "/index";
    }

    /**
     * 初始化安装
     */
    @RequestMapping("/install")
    public String install() throws IOException {
        // 在用户目录下创建主题文件夹
        String ikarosUserHome = System.getProperty("user.home") + "/.ikaros";
        String iakrosThemeDir = ikarosUserHome + "/theme";
        String iakrosThemeFilePath = iakrosThemeDir + "/simple.zip";

        // 默认主题ZIP文件
        File ikarosThemeDefaultFile = new File(iakrosThemeFilePath); // .ikaros/theme/simple.zip
        if(!ikarosThemeDefaultFile.getParentFile().exists()){
            ikarosThemeDefaultFile.getParentFile().mkdirs();
        }
        // 获取主题文件URL
        String themeSimpleZipUrl = settingService.findSettingByTypeAndName("theme", "theme_simple_url").getValue();
        // 下载默认的主题文件 文件到错误目录之下
        iakrosThemeFilePath = iakrosThemeFilePath.replace("/","\\");
        HttpClientUtils.downloadFile(themeSimpleZipUrl,iakrosThemeFilePath);

        // 解压缩到指定目录下
        HttpClientUtils.unzip(new File(iakrosThemeFilePath),iakrosThemeDir);

        // 初始化完毕更新数据库应用设置字段
        Setting setting = settingService.findSettingByTypeAndName("initialization", "is_init");
        setting.setValue("true");
        settingService.saveSettingItem(setting);

        return "redirect:/";
    }

    /**
     * 后台管理页
     */
    @RequestMapping("/admin")
    public String admin(){
        return "redirect:/admin/index.html";
    }


}
