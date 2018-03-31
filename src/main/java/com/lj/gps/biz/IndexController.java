
package com.lj.gps.biz;

import com.google.code.kaptcha.Producer;
import com.lj.gps.base.BaseController;
import com.lj.gps.base.BaseQueryVo;
import com.lj.gps.base.ResultEntity;
import com.lj.gps.biz.entity.User;
import com.lj.gps.frame.common.Constants;
import com.lj.gps.frame.common.ResultCodeConstants;
import com.lj.gps.frame.config.StaticProperty;
import com.lj.gps.frame.utils.ErrorMsgUtils;
import com.lj.gps.frame.utils.encryption.EncryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
class IndexController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private StaticProperty staticProperty;

    @Autowired
    private Producer captchaProducer;

    @GetMapping( { "/" } )
    public String index(Map<String, Object> model) {
        //model.put("cdnPath", staticProperty.getResource());
        return "index";
    }

    @GetMapping("/login")
    public String login(Map<String, Object> model) {
        ///model.put("cdnPath", staticProperty.getResource());
        return "login";
    }

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/loginPost")
    @ResponseBody
    public ResultEntity<User> loginPost(String username, String password) {
        ResultEntity<User> resultEntity = new ResultEntity<>();
        User user = userService.getUserByUsername(username);
        if (user == null || user.getStatus() == Constants.USER_UNAVAILABLE_STATUS) {
            ErrorMsgUtils.createErrorMsg(resultEntity, ResultCodeConstants.USERNAME_NOT_EXIST);
        } else if (!user.getPassword().equals(EncryptUtil.PBEEncrypt(password))) {
            ErrorMsgUtils.createErrorMsg(resultEntity, ResultCodeConstants.PASSWORD_ERROR);
        } else {
            resultEntity.setData(user);
            session.setAttribute(Constants.SESSION_LOGIN_USER, user);
        }
        return resultEntity;
    }


    @GetMapping("/logout")
    public String logout() {
        // 移除session
        session.removeAttribute(Constants.SESSION_LOGIN_USER);
        session.removeAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        return "redirect:/login";
    }

}