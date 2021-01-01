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
    public User login(@NotNull UserDTO userDTO){
        User user = null;
        switch (userDTO.getType()){
            case UserDTOType.USERNAME_PASSWORD: {
                String cacheKey = User.class.getName()+":Info"+"==>username="+userDTO.getUsername()+"==>password="+userDTO.getPassword();
                if(cacheStore.containsKey(cacheKey)) {
                    user = cacheStore.get(cacheKey);
                }else {
                    Optional<User> userOptional = userDao.findOne(
                            Example.of(User.build()
                                    .setUsername(userDTO.getUsername())
                                    .setPassword(MD5Utils.md5(userDTO.getPassword())
                                    )
                            )
                    );
                    cacheStore.set(cacheKey,userOptional.isPresent()?user = userOptional.get():null);
                }

                break;
            }
            case UserDTOType.EMAil_PASSWORD: {
                String cacheKey = User.class.getName()+":Info"+"==>email="+userDTO.getEmail()+"==>password="+userDTO.getPassword();
                if(cacheStore.containsKey(cacheKey)) {
                    user = cacheStore.get(cacheKey);
                }else {
                    Optional<User>  userOptional = userDao.findOne(
                            Example.of(User.build()
                                    .setEmail(userDTO.getEmail())
                                    .setPassword(MD5Utils.md5(userDTO.getPassword()))
                                    )
                    );
                    cacheStore.set(cacheKey,userOptional.isPresent()?user = userOptional.get():null);
                }
                break;
            }
            case UserDTOType.PHONE_NUMBER_PASSWORD: {
                String cacheKey = User.class.getName()+":Info"+"==>phone_number="+userDTO.getPhoneNumber()+"==>password="+userDTO.getPassword();
                if(cacheStore.containsKey(cacheKey)) {
                    user = cacheStore.get(cacheKey);
                }else {
                    Optional<User>  userOptional = userDao.findOne(
                            Example.of(User.build()
                                    .setPhoneNumber(userDTO.getPhoneNumber())
                                    .setPassword(MD5Utils.md5(userDTO.getPassword()))
                            )
                    );
                    cacheStore.set(cacheKey,userOptional.isPresent()?user = userOptional.get():null);
                }
                break;
            }
            case UserDTOType.PNONE_NUMBER_CAPTCHA: {
                throw IkarosException.build("非常抱歉，目前不支持此种认证方式==>"+userDTO.getType());
            }
            default:
                throw IkarosException.build("非常抱歉，目前不支持此种认证方式==>"+userDTO.getType());
        }
        return user;
    }

    @Override
    public Boolean checkToken(UserDTO userDTO){
        if(ObjectUtils.isEmpty(userDTO.getToken()) || !UserDTOType.TOKEN.equals(userDTO.getType())) throw IkarosException.build("校验Token请指定类型为token");
        if(ObjectUtils.isEmpty(userDTO.getUuid()) || ObjectUtils.isEmpty(userDTO.getToken())) throw IkarosException.build("校验Token需要传递UUID和Token值");
        Boolean isCorrectToken = false; //校验标识符合
        String cacheKey = User.class.getName() + ":"+userDTO.getUuid()+":Token:Check";
        User user = null;
        if(cacheStore.containsKey(cacheKey)) { //缓存查询
            user = cacheStore.get(cacheKey);
            if(user==null) {
                isCorrectToken =false;
            }else {
                isCorrectToken = userDTO.getToken().equals(user.getToken());
            }
        }else { // 数据库查询
            Optional<User> userOptional = userDao.findById(userDTO.getUuid());
            cacheStore.set(cacheKey,userOptional.isPresent()?userOptional.get():null);
            if(userOptional.isEmpty()) {
                isCorrectToken =false;
            }else {
                isCorrectToken = userDTO.getToken().equals(user.getToken());
            }
        }
        return isCorrectToken;
    }

}
