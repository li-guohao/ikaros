package cn.liguohao.ikaros.service;

import cn.liguohao.ikaros.dto.UserDTO;
import cn.liguohao.ikaros.store.database.User;
import com.sun.istack.NotNull;

/**
 * @author liguohao_cn
 * @date 2021/1/1
 */
public interface UserService {

    /**
     * 用户登录
     * @param userDTO 用户传输对象
     * @return User 用户信息对象
     */
    User login(@NotNull UserDTO userDTO);

    /**
     * 校验Token 根据UUID
     * @param userDTO 用户传输对象
     * @return 校验是否成功
     */
    Boolean checkToken(@NotNull UserDTO userDTO);

    /**
     * 查询Token 根据用户ID
     * @param uuid 用户ID
     * @return Token信息
     */
    String findTokenByUuid(@NotNull Long uuid);
}
