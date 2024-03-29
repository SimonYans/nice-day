package top.niceday.yan.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import top.niceday.yan.domain.base.AuditUser;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class JwtUtil {
    public static final String SECRET = "qazwsx123444$#%#()*&& asdaswwi1235 ?;!@#kmmmpom in***xx**&";
    public static final String ITS_SECRET = "jDY7rFv$NYbp3xpPqG7vC9rmjLjuWoNfstNWY$^oyvnk5RcdeLARUsFQMc";

    public static final String HEADER_USERID = "userId";
    public static final String HEADER_ROLEID = "roleId";
    public static final String HEADER_DEPTID = "departmentId";
    public static final String HEADER_USERTYPE = "userType";
    //token超时时间
    public static final int TOKEN_EXPIRATION = 24;
    public static final int ITS_TOKEN_EXPIRATION = 2;

    public static String createToken(String userId, AuditUser auditUser, Integer expireHour) throws Exception {
//        log.info("生产toke. userId: {}{}", userId,auditUser);
        Date iatDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, expireHour);
        Date expiresDate = calendar.getTime();
        HashMap<String, Object> map = new HashMap<>();
        map.put("alg","HS256");
        map.put("typ","JWT");
        String roleId = "";
        List<String> roleIdList = auditUser.getRoleIdList();
        if(null!=roleIdList){
            roleId = roleIdList.toString();
        }
        String token = JWT.create()
                .withHeader(map)
                .withClaim(HEADER_USERID,userId)//设置userId
                .withClaim(HEADER_ROLEID,roleId)//设置roleId
                //设置departmentId
                .withClaim(HEADER_DEPTID, String.valueOf(auditUser.getSysOrgId()))
                .withClaim(HEADER_USERTYPE, String.valueOf(auditUser.getUserType()))
                .withExpiresAt(expiresDate)//过期时间
                .withIssuedAt(iatDate)//签发时间
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    public static String createToken(String userId, AuditUser auditUser) throws Exception {
        return createToken(userId, auditUser, TOKEN_EXPIRATION);
    }

    public static Map<String, Claim> verifyToken(String token) throws Exception{
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        try{
            return verifier.verify(token).getClaims();
        }catch(Exception e){
            throw new RuntimeException("token无效");
        }
    }

    public static Map<String, Claim> verifyDriverToken(String token, String secret) throws Exception{
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
        try{
            return verifier.verify(token).getClaims();
        }catch(Exception e){
            throw new RuntimeException("token无效");
        }
    }

    public static void tokenCookie(String token, HttpServletResponse response) {
        if (StringUtils.isNotEmpty(token)) {
            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setMaxAge(JwtUtil.TOKEN_EXPIRATION * 60 * 60);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
    }
}
