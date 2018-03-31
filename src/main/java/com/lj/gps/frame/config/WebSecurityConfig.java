package com.lj.gps.frame.config;

/**
 * liuyf
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

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

        /*@Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
//            String url = request.getRequestURL().toString();
            String method = request.getMethod();
            String uri = request.getRequestURI();
            String queryString = request.getQueryString();
//            logger.info(String.format("请求参数, url: %s, method: %s, uri: %s, params: %s", url, method, uri, queryString));
            logger.info(String.format("请求参数：method: %s, uri: %s, params: %s", method, uri, queryString));

            Object obj = request.getSession().getAttribute(Constants.SESSION_LOGIN_USER);
            if (null == obj) { //未登录
                String ajaxXRequestedWith = request.getHeader("x-requested-with");
                if (StringUtils.isNullOrEmpty(ajaxXRequestedWith)) ajaxXRequestedWith = request.getHeader("X-Requested-With");
                if ("XMLHttpRequest".equalsIgnoreCase(ajaxXRequestedWith)) { //如果是ajax请求响应头会有，x-requested-with
                    //在响应头设置session状态
                    response.setHeader("sessionstatus", "timeout");
                } else {
                    //跳转登录
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                return false;
            }
            return super.preHandle(request, response, handler);
        }*/
    }
}