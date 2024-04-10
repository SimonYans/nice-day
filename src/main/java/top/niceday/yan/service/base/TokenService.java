package top.niceday.yan.service.base;

import com.auth0.jwt.interfaces.Claim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.niceday.yan.common.exception.BusinessException;
import top.niceday.yan.dao.base.TokenMapper;
import top.niceday.yan.dao.req.SysLoginFormReq;
import top.niceday.yan.domain.base.AuditUser;
import top.niceday.yan.domain.base.User;
import top.niceday.yan.domain.enums.MsgErrorEnum;
import top.niceday.yan.utils.JwtUtil;
import top.niceday.yan.utils.RSAUtil;

import java.util.Map;

/**
 * @author shuai.yan
 * @date 2024-03-29 星期五 17:33:56
 */

@Service
public class TokenService {

    @Value("${login.password.privateKey}")
    private String rsaPrivateKey;

    @Autowired
    private TokenMapper tokenMapper;

    public String login(String username, String password, Integer expireHour) throws Exception {
        String token, pwd = "";
        try {
            pwd = RSAUtil.getInstance().decryptByPrivateKey(rsaPrivateKey, password);
        } catch (Exception e) {
//            log.warn("密码解密失败", e);
            throw new BusinessException(MsgErrorEnum.USERNAME_PWD_ERROR.getCode(), MsgErrorEnum.USERNAME_PWD_ERROR.getMsg());
        }
        SysLoginFormReq sysLoginFormReq = new SysLoginFormReq();
        sysLoginFormReq.setUsername(username);
        sysLoginFormReq.setPassword(pwd);

        User loginUser = tokenMapper.login(sysLoginFormReq);
        if(null != loginUser) {
            Long userId = loginUser.getId();
            // 正确生成token
            token = createToken(String.valueOf(userId), expireHour);
            // redisUtils.del(RedisKeys.getLoginErrorCountKey(username));
        } else {
            throw new BusinessException(MsgErrorEnum.USERNAME_PWD_ERROR.getCode(), MsgErrorEnum.USERNAME_PWD_ERROR.getMsg());
        }
        return token;
    }

    private String createToken(String userId, Integer expireHour) throws Exception{
        AuditUser auditUser = new AuditUser();
        // AuditUser auditUser = responseVo.getData();
        return JwtUtil.createToken(userId, auditUser, expireHour);
    }


    public String verifyToken(String token) throws Exception {
        String headerStr = "";
        try {
            Map<String, Claim> checkMap =  JwtUtil.verifyToken(token);
            String userId = checkMap.get(JwtUtil.HEADER_USERID).asString();
            String roleId = checkMap.get(JwtUtil.HEADER_ROLEID).asString();
            String deptId = checkMap.get(JwtUtil.HEADER_DEPTID).asString();
            headerStr = userId + "|" + roleId + "|" + deptId;
        }catch (Exception e){
            throw new BusinessException(MsgErrorEnum.NO_RIGHTS.getCode(), MsgErrorEnum.NO_RIGHTS.getMsg());
        }
        return headerStr;
    }
}
