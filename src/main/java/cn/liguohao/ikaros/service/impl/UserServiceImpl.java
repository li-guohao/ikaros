package cn.liguohao.ikaros.service.impl;

import cn.liguohao.ikaros.annotation.IkarosCache;
import cn.liguohao.ikaros.constant.UserDTOType;
import cn.liguohao.ikaros.dao.UserDao;
import cn.liguohao.ikaros.dto.UserDTO;
import cn.liguohao.ikaros.exception.IkarosException;
import cn.liguohao.ikaros.service.UserService;
import cn.liguohao.ikaros.store.cache.CacheStore;
import cn.liguohao.ikaros.store.database.User;
import cn.liguohao.ikaros.util.IkarosAssert;
import cn.liguohao.ikaros.util.MD5Utils;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

/**
 * @author liguohao_cn
 * @date 2021/1/1
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @IkarosCache
    public User login(@NotNull UserDTO userDTO){
        Optional<User> userOptional = null;
        switch (userDTO.getType()){
            case UserDTOType.USERNAME_PASSWORD: {
                IkarosAssert.isEmpty(userDTO.getUsername(),"用户名不能为空");
                IkarosAssert.isEmpty(userDTO.getPassword(),"密码不能为空");
                userOptional = userDao.findOne(
                        Example.of(User.build()
                                .setUsername(userDTO.getUsername())
                                .setPassword(MD5Utils.md5(userDTO.getPassword())
                                )
                        )
                );
                break;
            }
            case UserDTOType.EMAil_PASSWORD: {
                IkarosAssert.isEmpty(userDTO.getEmail(),"邮箱不能为空");
                IkarosAssert.isEmpty(userDTO.getPassword(),"密码不能为空");
                userOptional = userDao.findOne(
                        Example.of(User.build()
                                .setEmail(userDTO.getEmail())
                                .setPassword(MD5Utils.md5(userDTO.getPassword()))
                        )
                );
                break;
            }
            case UserDTOType.PHONE_NUMBER_PASSWORD: {
                IkarosAssert.isEmpty(userDTO.getPhoneNumber(),"手机号不能为空");
                IkarosAssert.isEmpty(userDTO.getPassword(),"密码不能为空");
                userOptional = userDao.findOne(
                        Example.of(User.build()
                                .setPhoneNumber(userDTO.getPhoneNumber())
                                .setPassword(MD5Utils.md5(userDTO.getPassword()))
                        )
                );
                break;
            }
            case UserDTOType.PNONE_NUMBER_CAPTCHA: {
                IkarosAssert.isEmpty(userDTO.getPhoneNumber(),"手机号不能为空");
                IkarosAssert.isEmpty(userDTO.getCaptcha(),"验证码不能为空");
                throw new IkarosException("非常抱歉，目前不支持此种认证方式==>"+userDTO.getType());
            }
            default:
                throw new IkarosException("非常抱歉，目前不支持此种认证方式==>"+userDTO.getType());
        }
        return userOptional.isPresent()?userOptional.get():null;
    }

    @Override
    public Boolean checkToken(UserDTO userDTO){
        IkarosAssert.isTrue(!UserDTOType.TOKEN.equals(userDTO.getType()),"校验Token请指定类型为token");
        IkarosAssert.isEmpty(userDTO.getUuid(),"uuid值不能为空");
        IkarosAssert.isEmpty(userDTO.getToken(),"token值不能为空");

        String tokenByUuid = findTokenByUuid(userDTO.getUuid());
        return userDTO.getToken().equals(tokenByUuid);
    }

    @Override
    @IkarosCache
    public String findTokenByUuid(Long uuid) {
        IkarosAssert.isNull(uuid,"执行[findTokenByUuid(Long uuid)]查询Token需要指定uuid的值");
        Optional<User> userOptional = userDao.findById(uuid);
        return userOptional.isPresent()?userOptional.get().getToken():null;
    }

}
