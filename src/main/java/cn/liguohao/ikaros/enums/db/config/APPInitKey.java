package cn.liguohao.ikaros.enums.db.config;

/**
 * 应用初始化 对应的KEY
 *
 * @since 2021/5/23
 * @see ConfigType#APP_INIT
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 */
public enum APPInitKey {

    /**
     * 是否已经初始化
     * 1-已初始化 0-未初始化
     */
    IS_INITED("应用初始化配置 1-已初始化 0-未初始化")
    ,

    ;



    /**
     * 此键的描述
     */
    private String description;

    APPInitKey(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public APPInitKey setDescription(String description) {
        this.description = description;
        return this;
    }
}
