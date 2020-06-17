package whu.zhj.imageshare.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import whu.zhj.imageshare.model.JwtFilter;

/**
 * @program: ImageShare
 * @description:
 * @author: 周宏俊
 * @create: 2020-06-06 10:29
 */
@Configuration
public class JwtCfg {
    @Bean
    public FilterRegistrationBean jwtFilter(){
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/image/*");
        return registrationBean;
    }
}
