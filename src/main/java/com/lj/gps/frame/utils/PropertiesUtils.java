package com.lj.gps.frame.utils;/*
package com.lj.gps.frame.utils;

import com.lj.gps.frame.common.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

*/
/**
 * 主要是@Configuration，实现接口：EnvironmentAware就能获取到系统环境信息;
 *
 * @PostConstruct 的方法获取不到属性，因为比当前类执行更早
 * http://www.cnblogs.com/hyl8218/p/5927337.html
 * Created by IntelliJ IDEA.<br/>
 * User: liuyf<br/>
 * Date: 2017/8/24<br/>
 * Time: 23:23<br/>
 *//*

@Configuration
public class PropertiesUtils implements EnvironmentAware {

    private static Environment environment;

    //注入application.properties的属性到指定变量中.
    @Value("${static.resource}")
    private String resourceUrl;

    */
/**
     * 注意重写的方法 setEnvironment 是在系统启动的时候被执行。
     *//*

    @Override
    public void setEnvironment(Environment environment) {

//        //打印注入的属性信息.
//        System.out.println("myUrl=" + resourceUrl);
//
//        //通过 environment 获取到系统属性.
//        System.out.println(environment.getProperty("JAVA_HOME"));
//
//        //通过 environment 同样能获取到application.properties配置的属性.
//        System.out.println(environment.getProperty("spring.datasource.url"));
//
//        //获取到前缀是"spring.datasource." 的属性列表值.
//        RelaxedPropertyResolver relaxedPropertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");
//        System.out.println("spring.datasource.url=" + relaxedPropertyResolver.getProperty("url"));
//        System.out.println("spring.datasource.driverClassName=" + relaxedPropertyResolver.getProperty("driverClassName"));

        this.environment = environment;
    }

    public static String getRequiredProperty(String propertyName) {
        return environment.getRequiredProperty(propertyName);
    }

    public static String getProperty(String propertyName) {
        return environment.getProperty(propertyName);
    }

    public static String getString(String propertyName) {
        return environment.getProperty(propertyName);
    }

    */
/**
     * 获取系统根目录,用于在非Controller获取根目录
     *
     * @return
     *//*

    public static String getContextPath() {
        return System.getProperty(Constants.CONTEXT_PATH_VALUE);
    }

}

//或这种方式
//@Configuration
//@PropertySource({ "classpath:/properties/email_${environment}.properties" })
//@PropertySource({ "classpath:/properties/appconfig.properties" })
//@PropertySource({ "classpath:/properties/ApplicationResources.properties" })
//@PropertySource({ "classpath:/properties/Database_${environment}.properties"})
//@PropertySource({ "classpath:/properties/log4j.properties" })
//@PropertySource({ "classpath:/properties/system.properties" })
//public class PropertiesConfig {
//
//    @Autowired
//    private Environment env;
//
//    private static Environment environment;
//
//    @PostConstruct
//    public void init(){
//        environment = env;
//        System.out.println(environment == env);
//    }
//
//    public static String getPopertyValue(String property){
//        return environment.getProperty(property);
//    }
//}*/
