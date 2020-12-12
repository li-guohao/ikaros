package cn.liguohao.ikaros.dao;

import cn.liguohao.ikaros.store.File;
import cn.liguohao.ikaros.store.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Set;

/**
 * @Description TODO 设置表数据库持久层
 * @Author liguohao
 * @Date 2020/11/25 20:24
 */
public interface SettingDao extends JpaRepository<Setting,Long>, PagingAndSortingRepository<Setting,Long>, JpaSpecificationExecutor<Setting> {
    /**
     * 根据设置项名称查询设置项具体信息
     * @param name
     * @return
     */
    Setting findSettingByName(String name);

    /**
     * 根据设置项类型查询对应类型的所有设置集合
     * @param type
     * @return
     */
    List<Setting> findSettingByType(String type);

    /**
     * 根据设 置项类型 和 设置项名称 查询具体的设置信息
     * @param settingType 设置项类型
     * @param name 设置项名称
     * @return
     */
    Setting findSettingByTypeAndName(String type, String name);
}
