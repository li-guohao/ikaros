package cn.liguohao.ikaros.enums.db.config;

/**
 * 主题 对应的key
 *
 * @since 2021/5/23
 * @see ConfigType#THEME
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 */
public enum ThemeKey {
    /**
     * 默认主题下载链接
     */
    DEFAULT_DOWNLOAD_URL("默认主题下载链接")
    ,
    /**
     * 当前主题名称
     */
    CURRENT("当前主题")
    ,

    ;


    /**
     * 此键的描述
     */
    private String description;

    ThemeKey(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
