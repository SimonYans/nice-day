package top.niceday.yan.dao.req;

import lombok.Data;

/**
 * 登录表单
 *
 */
@Data
public class SysLoginFormReq {
    private String username;
    private String password;
}
