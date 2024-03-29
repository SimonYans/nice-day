package top.niceday.yan.domain.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum MsgErrorEnum {

    /**
     * 无权限
     */
    NO_PARAMS(400, "参数错误","参数错误"),
    NO_RIGHTS(401, "token错误", "token错误"),
    UUID_NOT_NULL(402, "uuid不能为空", "uuid不能为空"),
    CODE_NOT_RIGHTS(405, "code错误", "code错误"),
    SCOPE_NOT_RIGHTS(406, "scope错误", "scope错误"),
    CAPTCHA_PARAM_MISS(409, "图形验证码参数缺失", "图形验证码参数缺失"),
    CAPTCHA_CACHE_ERROR(500, "图形验证码生成失败", "图形验证码生成失败"),
    SMS_ERROR(501, "短信发送失败", "短信发送失败"),
    VERIFY_MS_ERROR(502, "短信验证码输入有误", "短信验证码输入有误"),
    NO_USER(503, "用户不存在", "用户不存在"),
    MORE_THEN_THREE(504, "密码输错超过三次", "密码输错超过三次"),
    CAPTCHA_VERIFY_ERROR(505, "图形验证码输入有误", "图形验证码输入有误"),
    HAVE_REG_ERROR(506, "手机号已注册", "手机号已注册"),
    SMS_CODE_LIMIT(507, "当天获取验证码次数达到上限", "当天获取验证码次数达到上限"),
    USERNAME_PWD_ERROR(508, "账号或密码错误", "账号或密码错误")
    ;

    @Setter
    private int code;
    @Setter
    private String msg;
    @Setter
    private String desc;

    MsgErrorEnum(int code, String msg, String desc) {
        this.code = code;
        this.msg = msg;
        this.desc = desc;
    }
}
