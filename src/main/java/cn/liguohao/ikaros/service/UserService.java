package cn.liguohao.ikaros.service;

import cn.liguohao.ikaros.annotation.IkarosCache;
import cn.liguohao.ikaros.dao.UserDao;
import cn.liguohao.ikaros.dto.UserDTO;
import cn.liguohao.ikaros.exception.IkarosException;
import cn.liguohao.ikaros.store.database.User;
import cn.liguohao.ikaros.util.IkarosAssert;
import cn.liguohao.ikaros.util.MD5Utils;
import cn.liguohao.ikaros.util.StringUtils;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 用户服务层
 *
 * @author <a href="mailto:liguohao_cn@qq.com">liguohao_cn@qq.com</a>
 * @since  2021/1/1
 */
@Service
public class UserService{

    @Autowired
    private UserDao userDao;

    /**
     * 用户登录
     * @param userDTO 用户传输对象
     * @return User 用户信息对象
     */
    @IkarosCache
    public User login(@NotNull UserDTO userDTO){
        Optional<User> userOptional = null;
        switch (userDTO.getType()){
            case UserDTO.Type.USERNAME_PASSWORD: {
                IkarosAssert.isNotEmpty(userDTO.getUsername(),"用户名不能为空");
                IkarosAssert.isNotEmpty(userDTO.getPassword(),"密码不能为空");
                userOptional = userDao.findOne(
                        Example.of(User.build()
                                .setUsername(userDTO.getUsername())
                                .setPassword(StringUtils.isMD5Str(userDTO.getPassword())?userDTO.getPassword():MD5Utils.md5(userDTO.getPassword())
                                )
                        )
                );
                break;
            }
            case UserDTO.Type.EMAIL_PASSWORD: {
                IkarosAssert.isNotEmpty(userDTO.getEmail(),"邮箱不能为空");
                IkarosAssert.isNotEmpty(userDTO.getPassword(),"密码不能为空");
                userOptional = userDao.findOne(
                        Example.of(User.build()
                                .setEmail(userDTO.getEmail())
                                .setPassword(StringUtils.isMD5Str(userDTO.getPassword())?userDTO.getPassword():MD5Utils.md5(userDTO.getPassword()))
                        )
                );
                break;
            }
            case UserDTO.Type.PHONE_NUMBER_PASSWORD: {
                IkarosAssert.isNotEmpty(userDTO.getPhoneNumber(),"手机号不能为空");
                IkarosAssert.isNotEmpty(userDTO.getPassword(),"密码不能为空");
                userOptional = userDao.findOne(
                        Example.of(User.build()
                                .setPhoneNumber(userDTO.getPhoneNumber())
                                .setPassword(StringUtils.isMD5Str(userDTO.getPassword())?userDTO.getPassword():MD5Utils.md5(userDTO.getPassword()))
                        )
                );
                break;
            }
            case UserDTO.Type.PNONE_NUMBER_CAPTCHA: {
                IkarosAssert.isNotEmpty(userDTO.getPhoneNumber(),"手机号不能为空");
                IkarosAssert.isNotEmpty(userDTO.getCaptcha(),"验证码不能为空");
                throw new IkarosException("非常抱歉，目前不支持此种认证方式==>"+userDTO.getType());
            }
            default:
                throw new IkarosException("非常抱歉，目前不支持此种认证方式==>"+userDTO.getType());
        }
        return userOptional.isPresent()?userOptional.get():null;
    }

    /**
     * 校验Token 根据UUID
     * @param userDTO 用户传输对象
     * @return 校验是否成功
     */
    @IkarosCache
    public Boolean checkToken(UserDTO userDTO){
        IkarosAssert.isTrue(UserDTO.Type.TOKEN.equals(userDTO.getType()),"校验Token请指定类型为token");
        IkarosAssert.isNotEmpty(userDTO.getUuid(),"uuid值不能为空");
        IkarosAssert.isNotEmpty(userDTO.getToken(),"token值不能为空");

        String tokenByUuid = findTokenByUuid(userDTO.getUuid());
        return userDTO.getToken().equals(tokenByUuid);
    }

    /**
     * 查询Token 根据用户ID
     * @param uuid 用户ID
     * @return Token信息
     */
    public String findTokenByUuid(Long uuid) {
        IkarosAssert.isNotEmpty(uuid,"执行[findTokenByUuid(Long uuid)]查询Token需要指定uuid的值");
        Optional<User> userOptional = userDao.findById(uuid);
        return userOptional.isPresent()?userOptional.get().getToken():null;
    }

}
