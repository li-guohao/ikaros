package cn.liguohao.ikaros.store.database;

import javax.persistence.*;
import java.util.Date;

/**
 * @author liguohao_cn
 * @date 2020/12/30
 */
@Entity
@Table(name = "config")
public class Config {


    /**
     * 配置ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "config_id")
    private Long configId;

    /**
     * 名称
     */
    private String name;

    /**
     * 名称对应的值
     */
    private String value;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 类型
     */
    private String type;

    /**
     * 补充解释说明
     */
    private String description;

    public static Config build(){return new Config();}

    public Long getConfigId() {
        return configId;
    }

    public Config setConfigId(Long configId) {
        this.configId = configId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Config setName(String name) {
        this.name = name;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Config setValue(String value) {
        this.value = value;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Config setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getType() {
        return type;
    }

    public Config setType(String type) {
        this.type = type;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Config setDescription(String description) {
        this.description = description;
        return this;
    }
}
