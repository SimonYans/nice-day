package top.niceday.yan.domain.base;

import lombok.Data;

/**
 * @author shuai.yan
 * @date 2024-03-29 星期五 17:32:56
 */
@Data
public class LoginForm {
    private String username;
    private String password;
    private String verificationCode;
    private String uuid;

}
