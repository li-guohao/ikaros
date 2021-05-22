package cn.liguohao.ikaros.store.database;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**配置
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2020/12/30
 */
@Entity
@Table(name = "config")
@EntityListeners(AuditingEntityListener.class)
public class Config {


    /**
     * 配置ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "config_id")
    private Long configId;

    /**
     * 配置项键
     */
    @Column(name = "item_key")
    private String key;

    /**
     * 配置项值
     */
    @Column(name = "item_value")
    private String value;

    /**
     * 更新时间
     */
    @LastModifiedDate
    @Column(name = "update_time")
    private LocalDateTime updateTime;

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

    public String getKey() {
        return key;
    }

    public Config setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Config setValue(String value) {
        this.value = value;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public Config setUpdateTime(LocalDateTime updateTime) {
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
