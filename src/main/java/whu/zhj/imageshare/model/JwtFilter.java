package whu.zhj.imageshare.model;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import whu.zhj.imageshare.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SignatureException;
import java.util.Map;

/**
 * @program: ImageShare
 * @description:
 * @author: 周宏俊
 * @create: 2020-06-06 10:08
 */
public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //将ServletRequest、ServletResponse转为HttpServletRequest、HttpServletResponse
        final HttpServletRequest request = (HttpServletRequest)servletRequest;
        final HttpServletResponse response = (HttpServletResponse)servletResponse;
        //从请求中获取授权信息
        final String authHeader = request.getHeader("Authorization");
        System.out.println(authHeader);
        //如果请求是无需确认的，返回200
        if("OPTIONS".equals(request.getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(servletRequest, servletResponse);
        }else{  //请求需要验证
            //验证请求授权信息
            if(authHeader == null || !authHeader.startsWith("Bearer")){
                response.setStatus(500);
                response.setHeader("warn", "缺少或非法的授权信息");
                //throw new ServletException("缺少或非法的授权信息");
            }
            //从授权信息中获取用户标识（JWT）
            final String token = authHeader.replaceFirst("Bearer", "").trim();
            //检查token
            System.out.println(token);
            Map<String, Claim> claims = JwtUtil.verity(token);
            if(!claims.isEmpty()){
                request.setAttribute("username", claims.get("sub").asString());
                request.setAttribute("role", claims.get("role").asInt());
            }else{
                response.setStatus(500);
                response.setHeader("warn", "缺少或非法的授权信息");
                ///throw new ServletException("非法的权限");
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
