package top.niceday.yan.controller.base;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.niceday.yan.common.ResponseVo;
import top.niceday.yan.domain.base.LoginForm;
import top.niceday.yan.service.base.TokenService;
import top.niceday.yan.utils.JwtUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author shuai.yan
 * @date 2024-03-29 星期五 17:28:54
 */

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @ApiOperation("login get token")
    @RequestMapping(value = "/login", method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
    public ResponseVo<String> login(@RequestBody LoginForm loginForm, HttpServletResponse response) throws Exception{
        String token = tokenService.login(loginForm.getUsername(), loginForm.getPassword(), JwtUtil.TOKEN_EXPIRATION);
        if (StringUtils.isNotEmpty(token)) {
            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return ResponseVo.ok("Token设置成功");
    }
}
