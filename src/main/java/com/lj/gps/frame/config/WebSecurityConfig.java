package com.lj.gps.frame.config;

/**
 * liuyf
 */

import com.lj.gps.biz.service.RedisService;
import com.lj.gps.frame.common.Constants;
import com.lj.gps.frame.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录配置
 * create by liuyf
 */
@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    @Autowired
    private RedisService redisService;

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
//        registry.addResourceHandler("/templates/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/templates/");
//
//        super.addResourceHandlers(registry);
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        // 排除配置
        //addInterceptor.excludePathPatterns("/");
        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/login**");
        addInterceptor.excludePathPatterns("/dync_captcha");

        addInterceptor.excludePathPatterns("/api/**");

        // 拦截配置
        addInterceptor.addPathPatterns("/**");
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
            String method = request.getMethod();
            String uri = request.getRequestURI();
            String queryString = request.getQueryString();
            String token = request.getHeader("Authorization");
            logger.info(String.format("请求参数：method: %s, uri: %s, params: %s ,token: %s", method, uri, queryString,token));
            Object obj = redisService.get(token);
            if (null == obj) { //未登录
                request.getRequestDispatcher("/login").forward(request, response);
            }
            return super.preHandle(request, response, handler);
        }
    }
}