package cn.liguohao.ikaros.service;

/**服务层-系统配置
 * @author liguohao_cn
 * @version 2021/1/3
 */
public interface ConfigService {

    /**
     * 应用是否已经初始化
     * @return true-已经初始化 false-未初始化
     */
    boolean isInited();

    /**
     * 应用初始化
     * @return true-初始化成功 false-初始化失败
     */
    boolean init();
}
