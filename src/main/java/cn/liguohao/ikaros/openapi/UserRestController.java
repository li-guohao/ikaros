package cn.liguohao.ikaros.openapi;

import cn.liguohao.ikaros.common.Assert;
import cn.liguohao.ikaros.common.constants.SecurityConstants;
import cn.liguohao.ikaros.common.result.CommonResult;
import cn.liguohao.ikaros.exceptions.RecordNotFoundException;
import cn.liguohao.ikaros.model.dto.AuthUserDTO;
import cn.liguohao.ikaros.model.dto.UserDTO;
import cn.liguohao.ikaros.model.entity.UserEntity;
import cn.liguohao.ikaros.service.UserService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guohao
 * @date 2022/09/08
 */
@RestController
@RequestMapping("/user")
public class UserRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRestController.class);

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public CommonResult<AuthUserDTO> login(@RequestBody AuthUserDTO authUserDTO,
                                           @RequestHeader HttpHeaders httpHeaders)
        throws RecordNotFoundException {
        Assert.notNull(authUserDTO, "'authUser' must not be null");
        LOGGER.debug("receive user info: {}", authUserDTO);
        authUserDTO = userService.login(authUserDTO);
        httpHeaders.set(SecurityConstants.TOKEN_HEADER,
            SecurityConstants.TOKEN_PREFIX + authUserDTO.getToken());
        return CommonResult.ok(authUserDTO);
    }

    private String getTokenFromHttpRequest(HttpServletRequest request) {
        String authorization = request.getHeader(SecurityConstants.TOKEN_HEADER);
        if (authorization == null || !authorization.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            return null;
        }
        return authorization.replace(SecurityConstants.TOKEN_PREFIX, "");
    }

    @GetMapping("/info")
    public CommonResult<UserDTO> getUserInfoByToken(HttpServletRequest request) {
        String token = getTokenFromHttpRequest(request);
        return CommonResult.ok(userService.getUserInfoByToken(token));
    }

    //@PostMapping("/register")
    public CommonResult<UserEntity> register(@RequestBody AuthUserDTO authUserDTO) {
        Assert.notNull(authUserDTO, "'authUser' must not be null");
        String username = authUserDTO.getUsername();
        String password = authUserDTO.getPassword();
        String role = authUserDTO.getRole();
        userService.registerUserByUsernameAndPassword(username, password, role);
        return CommonResult.ok();
    }

    @GetMapping
    public CommonResult<List<UserEntity>> getUsers(@RequestBody UserEntity userEntityCondition) {
        // todo impl find users by condition
        return CommonResult.ok();
    }

    @GetMapping("/{id}")
    public CommonResult<UserEntity> getUserById(@PathVariable Long id)
        throws RecordNotFoundException {
        return CommonResult.ok(userService.findUserById(id));
    }

    @PutMapping
    public CommonResult<UserEntity> updateUser(@RequestBody UserEntity userEntity)
        throws RecordNotFoundException {
        return CommonResult.ok(userService.updateUserInfo(userEntity));
    }

    @DeleteMapping("/{id}")
    public CommonResult<String> deleteUserById(@PathVariable Long id)
        throws RecordNotFoundException {
        userService.deleteUserById(id);
        return CommonResult.ok();
    }

}
