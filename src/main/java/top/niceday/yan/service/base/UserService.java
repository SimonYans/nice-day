package top.niceday.yan.service.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.niceday.yan.dao.base.UserMapper;
import top.niceday.yan.domain.base.User;

/**
 * @author shuai.yan
 * @date 2024-03-26 星期二 19:16:27
 */

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public Integer insertUser() {
        User user = new User();
        user.setUsername("shuai.yan");
        user.setPassword("123456");
        return userMapper.insert(user);
    }

}
