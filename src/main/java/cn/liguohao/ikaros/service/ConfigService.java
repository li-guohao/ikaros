package cn.liguohao.ikaros.service;

import cn.liguohao.ikaros.store.database.Config;
import org.springframework.data.domain.Example;

import java.util.List;

/**服务层-系统配置
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since 2021/1/3
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

    /**
     * 根据指定条件查询对应的配置信息
     * @param configExample 查询添加对象
     * @return 对应的配置信息
     */
    Config findOne(Example<Config> configExample);

    /**
     * 根据添加查询对应的多个配置信息
     * @param configExample 查询条件
     * @return 配置信息集合
     */
    List<Config> findList(Example<Config> configExample);
}
