package com.lj.gps.frame.config;

import com.lj.gps.frame.common.Constants;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.WebAppRootListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Description 类描述：WebAppRootListener
 * <p>
 * Created by IntelliJ IDEA.<br/>
 * User: liuyf<br/>
 * Date: 2018/2/12<br/>
 * Time: 10:11<br/>
 */
@Configuration
public class WebAppRootContext implements ServletContextInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.addListener(WebAppRootListener.class);
        servletContext.setInitParameter(
                //这里是注入参数的名称
                Constants.CONTEXT_PATH_KEY,
                Constants.CONTEXT_PATH_VALUE
        );
    }

}
