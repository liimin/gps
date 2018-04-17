package com.lj.gps.base;

import com.lj.gps.biz.entity.User;
import com.lj.gps.biz.service.RedisService;
import com.lj.gps.biz.service.UserService;
import com.lj.gps.frame.common.Constants;
import com.lj.gps.frame.exception.BusinessException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: liuyf<br/>
 * Date: 2017/9/18<br/>
 * Time: 15:31<br/>
 */
@Controller
public class BaseController {

    @Autowired
    protected HttpSession session;

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @Autowired
    protected UserService userService;

    @Autowired
    private RedisService redisService;

    /**
     * 获取当前登录用户
     * @return
     */
    public User getUser() {
        String token = request.getHeader("Authorization");
        Object obj = redisService.get(token);
        if (obj != null) {
            return (User) obj;
        }
        return null;
    }

}
