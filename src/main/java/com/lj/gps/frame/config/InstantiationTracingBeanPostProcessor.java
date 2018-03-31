package com.lj.gps.frame.config;

import com.lj.gps.frame.exception.GlobalExceptionHandler;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Description 类描述：
 * <p>
 * Created by IntelliJ IDEA.<br/>
 * User: liuyf<br/>
 * Date: 2018/2/12<br/>
 * Time: 9:52<br/>
 */
//@Component
public class InstantiationTracingBeanPostProcessor implements
        ApplicationListener<ContextRefreshedEvent> {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static boolean initialized;

    @Autowired
//    private ManageResolver manageResolver;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            //只在初始化“根上下文”的时候执行
            final ApplicationContext app = event.getApplicationContext();
            if (null == app.getParent()
                    && ("Root WebApplicationContext".equals(app.getDisplayName())
                    || app.getDisplayName().contains("AnnotationConfigEmbeddedWebApplicationContext"))
                    && "/xweb".equals(app.getApplicationName())
                    ) { // 当存在父子容器时，此判断很有用
                logger.info("*************:" + event.getSource());
                logger.info("*************:" + app.getDisplayName());
                logger.info("*************:" + app.getApplicationName());
                logger.info("*************:" + app.getBeanDefinitionCount());
                logger.info("*************:" + app.getEnvironment());
                logger.info("*************:" + app.getParent());
                logger.info("*************:" + app.getParentBeanFactory());
                logger.info("*************:" + app.getId());
                logger.info("*************:" + app.toString());
                logger.info("*************:" + app);
//                if (!initialized && !manageResolver.IsInitialCompleted()) {
//                    manageResolver.initLater();
//                    initialized = true;
//                }
            }
        } catch (Exception e) {
            logger.error("((XmlWebApplicationContext) event.getSource()).getDisplayName() 执行失败，请检查Spring版本是否支持");
        }
    }

}
