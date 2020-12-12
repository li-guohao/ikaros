package cn.liguohao.ikaros.store;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description TODO 系统设置
 * @Author liguohao
 * @Date 2020/11/25 19:24
 */
@Entity
@Table(name = "setting")
public class Setting {

    /**
     * 系统设置表ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "setting_id")
    private Long settingId;

    /**
     * 设置的名称
     */
    private String name;

    /**
     * 设置名称对应的值
     */
    private String value;

    /**
     * 该项设置最后的更新实现
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 该项设置的类型
     */
    private String type;

    /**
     * 该设置项的补充解释说明
     */
    private String description;

    public Setting() {
    }

    public Setting(String settingType, String name, String value) {
    }

    public Long getSettingId() {
        return settingId;
    }

    public Setting setSettingId(Long settingId) {
        this.settingId = settingId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Setting setName(String name) {
        this.name = name;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Setting setValue(String value) {
        this.value = value;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Setting setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }


    public String getType() {
        return type;
    }

    public Setting setType(String type) {
        this.type = type;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Setting setDescription(String description) {
        this.description = description;
        return this;
    }
}
