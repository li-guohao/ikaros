package cn.liguohao.ikaros.service;

import cn.liguohao.ikaros.store.Setting;

/** 应用设置
 * @Auther: liguohao
 * @Date: 2020/11/29 12:38
 */
public interface SettingService {

    /**
     * 根据设 置项类型 和 设置项名称 查询具体的设置信息
     * @param settingType 设置项类型
     * @param name 设置项名称
     * @return 应用设置详情
     */
    Setting findSettingByTypeAndName(String type, String name);

    /**
     * 新增和更新设置项，根据 设置项类型 和 设置项名称 判断是更新操作还是新增vaoz
     * @param setting 应用设置项，包含类型和名称和值
     */
    Setting saveSettingItem(Setting setting);
}
