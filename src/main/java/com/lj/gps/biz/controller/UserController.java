package com.lj.gps.biz.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lj.gps.base.*;
import com.lj.gps.biz.entity.User;
import com.lj.gps.biz.service.UserService;
import com.lj.gps.frame.common.ResultCodeConstants;
import com.lj.gps.frame.utils.ErrorMsgUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 用户controller
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 获取用户列表(带分页)
     *
     * @param pagination
     * @param queryVo
     * @return
     */
    @RequestMapping("/getUserList")
    @ResponseBody
    public ResponseEntity<User> getUserList(Pagination pagination, User user) {
        PageHelper.startPage(pagination.getCurrentPage(), pagination.getPageSize(), true);
        List<User> uList = userService.getUserList(user);
        ResponseEntity<User> responseObject = new ResponseEntity<>();
        long total = ((Page<User>) uList).getTotal();
        responseObject.setData(uList);
        responseObject.setRecordsTotal(total);
        responseObject.setRecordsFiltered(total);
        return responseObject;
    }

    /**
     * 根据用户id获取登录用户信息
     *
     * @return
     */
    /*@RequestMapping(value = "/getUserById/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity<UserRolesModel> getUserById(@PathVariable("userId") Integer userId) {
        ResultEntity<UserRolesModel> resultEntity = new ResultEntity<>();
        UserRolesModel urm = userService.getUserWithRolesByUserId(userId);
        resultEntity.setData(urm);
        resultEntity.setReturnCode(ResultCode.SUCCESS);
        return resultEntity;
    }*/

    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    /*@RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity<UserRolesModel> getUserInfo() {
        ResultEntity<UserRolesModel> resultEntity = new ResultEntity<>();
        UserRolesModel urm = userService.getUserWithRolesByUserId(getLoginUserId());
        resultEntity.setData(urm);
        resultEntity.setReturnCode(ResultCode.SUCCESS);
        return resultEntity;
    }*/

    /**
     * 校验旧密码
     *
     * @return
     */
   /* @RequestMapping(value = "/validateOldPass", method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity<Boolean> validateOldPass(int userId, String password) {
        ResultEntity<Boolean> resultEntity = new ResultEntity<Boolean>();
        boolean flag = userService.validateOldPass(userId, password);
        resultEntity.setReturnCode(ResultCode.SUCCESS);
        resultEntity.setMessage("查询成功!");
        resultEntity.setData(flag);
        return resultEntity;
    }*/

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity addUser(@ModelAttribute User user) {
        ResultEntity resultEntity = new ResultEntity();
        User u = userService.getUserByUsername(user.getUsername());
        if (null != u) {
            ErrorMsgUtils.createErrorMsg(resultEntity, ResultCodeConstants.USERNAME_EXISTED);
        } else {
            userService.addUser(user);
            resultEntity.setReturnCode(ResultCode.SUCCESS);
            resultEntity.setMessage("新增成功!");
        }
        return resultEntity;
    }

    /**
     * 更新用户
     *
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity<User> edit(User user) {
        ResultEntity<User> resultEntity = new ResultEntity<User>();
        userService.updateUser(user);
        resultEntity.setReturnCode(ResultCode.SUCCESS);
        resultEntity.setMessage("修改成功!");
        return resultEntity;
    }
    /**
     * 删除用户（支持批量）
     *
     * @param ids
     * @return
     */
    @RequestMapping("/batchremove")
    @ResponseBody
    public ResultEntity batchremove(String ids) {
        ResultEntity resultEntity = new ResultEntity();
        userService.removeUserById(ids);
        resultEntity.setReturnCode(ResultCode.SUCCESS);
        resultEntity.setMessage("删除成功!");
        return resultEntity;
    }

}
