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
     * 应用初始化配置
     * INITED-已初始化 NOT_INITED-未初始化
     * @see AppIsInitedValue#INITED
     * @see AppIsInitedValue#NOT_INITED
     */
    IS_INITED("应用初始化配置 INITED-已初始化 NOT_INITED-未初始化")
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
