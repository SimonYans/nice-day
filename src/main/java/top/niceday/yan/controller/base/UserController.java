package top.niceday.yan.controller.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.niceday.yan.common.ResponseVo;
import top.niceday.yan.common.exception.BusinessException;
import top.niceday.yan.service.base.UserService;

/**
 * @author shuai.yan
 * @date 2024-03-26 星期二 19:19:25
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/insertUser")
    public ResponseVo<Boolean> insertUser() {
        Integer result = userService.insertUser();
        if (result > 0) {
            return ResponseVo.ok(true);
        }
        throw new BusinessException("用户创建失败");
    }
}
