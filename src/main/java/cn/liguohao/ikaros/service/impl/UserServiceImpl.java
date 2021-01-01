package cn.liguohao.ikaros.service.impl;

import cn.liguohao.ikaros.constant.UserDTOType;
import cn.liguohao.ikaros.dao.UserDao;
import cn.liguohao.ikaros.dto.UserDTO;
import cn.liguohao.ikaros.exception.IkarosException;
import cn.liguohao.ikaros.service.UserService;
import cn.liguohao.ikaros.store.cache.CacheStore;
import cn.liguohao.ikaros.store.database.User;
import cn.liguohao.ikaros.util.MD5Utils;
import cn.liguohao.ikaros.util.TokenUtils;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
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
    @Autowired
    private CacheStore<User> cacheStore;

    @Override
    public User login(@NotNull UserDTO userDTO) {
        String cacheKey = User.class.getName()+":Info";
        if(cacheStore.containsKey(cacheKey)) return cacheStore.get(cacheKey);
        Optional<User> userOptional = null;
        switch (userDTO.getType()){
            case UserDTOType.USERNAME_PASSWORD: {
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
                userOptional = userDao.findOne(
                        Example.of(
                                User.build()
                                        .setEmail(userDTO.getEmail())
                                        .setPassword(MD5Utils.md5(userDTO.getPassword()))
                        )
                );
                break;
            }
            case UserDTOType.PHONE_NUMBER_PASSWORD: {
                userOptional = userDao.findOne(
                        Example.of(
                                User.build()
                                        .setPhoneNumber(userDTO.getPhoneNumber())
                                        .setPassword(MD5Utils.md5(userDTO.getPassword()))
                        )
                );
                break;
            }
            case UserDTOType.PNONE_NUMBER_CAPTCHA: {
                break;
            }
            default:
                throw IkarosException.build("非常抱歉，目前不支持此种认证方式==>"+userDTO.getType());
        }
        User user = userOptional.isPresent()?userOptional.get():null;
        user.setToken(TokenUtils.gengerToken()); //每次登录更新Token
        userDao.save(user);
        cacheStore.set(cacheKey,user);
        return user;
    }

    @Override
    public Boolean checkToken(UserDTO userDTO) {
        if(UserDTOType.TOKEN.equals(userDTO.getType())) throw IkarosException.build("校验Token请指定类型为token");
        if(ObjectUtils.isEmpty(userDTO.getUuid()) || ObjectUtils.isEmpty(userDTO.getToken())) throw IkarosException.build("校验Token需要传递UUID和Token值");
        String cacheKey = User.class.getName() + ":"+userDTO.getUuid()+":Token:Check";
        User user = null;
        if(cacheStore.containsKey(cacheKey)) {
            user = cacheStore.get(cacheKey);
        }else {
            Optional<User> userOptional = userDao.findById(userDTO.getUuid());
            if(userOptional.isEmpty()) throw IkarosException.build("未查询到用户 ==> uuid="+userDTO.getUuid());
            user = userOptional.get();
            cacheStore.set(cacheKey,user);
        }
        return userDTO.getToken().equals(user.getToken());
    }

}
