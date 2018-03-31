package com.lj.gps.frame.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.lj.gps.frame.exception.GlobalExceptionHandler;
import com.lj.gps.frame.utils.DateUtils;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import javax.servlet.MultipartConfigElement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * 这里存放对系统全局或局部设置的bean
 * Created by IntelliJ IDEA.<br/>
 * User: liuyf<br/>
 * Date: 2017/9/19<br/>
 * Time: 11:43<br/>
 */
@Configuration
public class BeansConfig {

    final String[] parsePatterns = new String[]{"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"};
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @Bean
    public Converter<String, Date> addDateConvertor() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                try {
                    return org.apache.commons.lang.time.DateUtils.parseDate(source, parsePatterns);
                } catch (ParseException e) {
//                    e.printStackTrace();
                    logger.error("日期解析错误,当前日期为:"+source);
                }
                return null;
            }
        };
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return new Jackson2ObjectMapperBuilderCustomizer() {
            @Override
            public void customize(Jackson2ObjectMapperBuilder builder) {
                builder.dateFormat(new SimpleDateFormat(DateUtils.YMDHMS));
            }
        };
    }

    @Bean
    public DefaultKaptcha captchaProducer() {
        DefaultKaptcha captchaProducer = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", "yes");
        properties.setProperty("kaptcha.border.color", "105,179,90");
        properties.setProperty("kaptcha.textproducer.font.color", "blue");
        properties.setProperty("kaptcha.image.width", "125");
        properties.setProperty("kaptcha.image.height", "45");
        properties.setProperty("kaptcha.textproducer.font.size", "45");
        properties.setProperty("kaptcha.session.key", "code");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config config = new Config(properties);
        captchaProducer.setConfig(config);
        return captchaProducer;
    }

    /**
     * 上传附件容量限制
     *
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("102400KB");
        factory.setMaxRequestSize("112400KB");
        return factory.createMultipartConfig();
    }

}
