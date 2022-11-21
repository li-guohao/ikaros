package run.ikaros.server.openapi;

import run.ikaros.server.utils.AssertUtils;
import run.ikaros.server.constants.SecurityConst;
import run.ikaros.server.result.CommonResult;
import run.ikaros.server.exceptions.RecordNotFoundException;
import run.ikaros.server.model.dto.AuthUserDTO;
import run.ikaros.server.model.dto.UserDTO;
import run.ikaros.server.entity.UserEntity;
import run.ikaros.server.service.UserServiceImpl;

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

    private final UserServiceImpl userServiceImpl;

    public UserRestController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/login")
    public CommonResult<AuthUserDTO> login(@RequestBody AuthUserDTO authUserDTO,
                                           @RequestHeader HttpHeaders httpHeaders)
        throws RecordNotFoundException {
        AssertUtils.notNull(authUserDTO, "'authUser' must not be null");
        LOGGER.debug("receive user info: {}", authUserDTO);
        authUserDTO = userServiceImpl.login(authUserDTO);
        httpHeaders.set(SecurityConst.TOKEN_HEADER,
            SecurityConst.TOKEN_PREFIX + authUserDTO.getToken());
        return CommonResult.ok(authUserDTO);
    }

    private String getTokenFromHttpRequest(HttpServletRequest request) {
        String authorization = request.getHeader(SecurityConst.TOKEN_HEADER);
        if (authorization == null || !authorization.startsWith(SecurityConst.TOKEN_PREFIX)) {
            return null;
        }
        return authorization.replace(SecurityConst.TOKEN_PREFIX, "");
    }

    @GetMapping("/info")
    public CommonResult<UserDTO> getUserInfoByToken(HttpServletRequest request) {
        String token = getTokenFromHttpRequest(request);
        return CommonResult.ok(userServiceImpl.getUserInfoByToken(token));
    }

    //@PostMapping("/register")
    public CommonResult<UserEntity> register(@RequestBody AuthUserDTO authUserDTO) {
        AssertUtils.notNull(authUserDTO, "'authUser' must not be null");
        String username = authUserDTO.getUsername();
        String password = authUserDTO.getPassword();
        userServiceImpl.registerUserByUsernameAndPassword(username, password);
        return CommonResult.ok();
    }


    @GetMapping("/{id}")
    public CommonResult<UserEntity> getUserById(@PathVariable Long id)
        throws RecordNotFoundException {
        return CommonResult.ok(userServiceImpl.getById(id).hiddenSecretField());
    }

    @PutMapping
    public CommonResult<UserEntity> updateUser(@RequestBody UserEntity userEntity)
        throws RecordNotFoundException {
        return CommonResult.ok(userServiceImpl.updateUserInfo(userEntity));
    }

    @DeleteMapping("/{id}")
    public CommonResult<String> deleteUserById(@PathVariable Long id)
        throws RecordNotFoundException {
        userServiceImpl.deleteUserById(id);
        return CommonResult.ok();
    }

}