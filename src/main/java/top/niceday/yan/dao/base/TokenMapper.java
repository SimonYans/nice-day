package top.niceday.yan.dao.base;

import org.apache.ibatis.annotations.Param;
import top.niceday.yan.dao.req.SysLoginFormReq;
import top.niceday.yan.domain.base.User;

/**
 * @author shuai.yan
 * @date 2024-03-26 星期二 19:09:47
 */
public interface TokenMapper {

    User login(@Param("loginForm") SysLoginFormReq sysLoginFormReq);

}
