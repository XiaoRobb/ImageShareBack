package whu.zhj.imageshare.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import whu.zhj.imageshare.model.Role;
import whu.zhj.imageshare.model.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: ImageShare
 * @description:
 * @author: 周宏俊
 * @create: 2020-05-30 15:27
 */
public class JwtUtil {
    /**
     * 过期时间为一天
     */
    private static final long EXPIRE_TIME = 24*60*60*1000;

    /**
     * token私钥
     */
    private static final String TOKEN_SECRET = "123456789";

    /**
     * 生成签名
     * @param role 用户身份
     * @return
     */
    public static String sign(User user){
        //过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //私钥加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        //设置头信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("type", "JWT");
        header.put("alg", "HS256");
        //附带username和userID
        return JWT.create().withHeader(header).withSubject(user.getUsername()).withClaim("role", user.getRole().getCode()).withExpiresAt(date).sign(algorithm);
    }

    /**
     * 验证token
     * @param token ：传入的token
     * @return 是否合法
     */
    public static Map<String, Claim> verity(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaims();
        }catch(IllegalArgumentException e){
            e.printStackTrace();
            return null;
        }catch(JWTVerificationException e){
            e.printStackTrace();
            return null;
        }
    }
}
