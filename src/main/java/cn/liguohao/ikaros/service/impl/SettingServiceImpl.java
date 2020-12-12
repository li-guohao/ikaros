package cn.liguohao.ikaros.service.impl;

import cn.liguohao.ikaros.dao.SettingDao;
import cn.liguohao.ikaros.exception.DataNotExistException;
import cn.liguohao.ikaros.exception.UserOperateException;
import cn.liguohao.ikaros.service.SettingService;
import cn.liguohao.ikaros.store.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Date;

/** 应用设置表服务层接口实现
 * @Auther: liguohao
 * @Date: 2020/11/29 12:41
 */
@Service
public class SettingServiceImpl implements SettingService {

    @Autowired
    private SettingDao settingDao;

    @Override
    public Setting findSettingByTypeAndName(String type, String name) {
        //校验合法性
        if(StringUtils.isEmpty(type) || StringUtils.isEmpty(name))
            throw new UserOperateException("请输入设置项类型和名称和值");
        Setting setting = settingDao.findSettingByTypeAndName(type, name);
        if (ObjectUtils.isEmpty(setting)) throw new DataNotExistException("找不到对应的设置项信息 ==> 设置类型type="+type+"  设置项名称name="+name);
        return setting;
    }

    @Override
    public Setting saveSettingItem(Setting setting) {
        // 根据设置类型和名称查询是否存在此设置项
        Setting settingExist = settingDao.findSettingByTypeAndName(setting.getType(), setting.getName());
        // 判断是更新操作还是新增操作
        if(ObjectUtils.isEmpty(settingExist) ){ //新增操作
            // 新增设置项
            return settingDao.save(setting);
        }else { //更新操作
            settingExist.setValue(setting.getValue()).setUpdateTime(new Date());
            return settingDao.save(settingExist);
        }
    }

}
