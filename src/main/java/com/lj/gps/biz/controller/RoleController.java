package com.lj.gps.biz.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lj.gps.base.*;
import com.lj.gps.biz.entity.Role;
import com.lj.gps.biz.service.RoleService;
import com.lj.gps.frame.common.ResultCodeConstants;
import com.lj.gps.frame.utils.ErrorMsgUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;


/**
 * 角色Controller
 *
 * @author liuyf
 * @version 1.0
 * @history 2018-1-26
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    /**
     * 获取角色列表(带分页)
     *
     * @param pagination
     * @param role
     * @return
     */
    @RequestMapping("/getRoleList")
    @ResponseBody
    public ResponseEntity<Role> getRoleList(Pagination pagination, Role role) {
        PageHelper.startPage(pagination.getCurrentPage(), pagination.getPageSize(), true);
        List<Role> rList = roleService.getRoleList(role);
        ResponseEntity<Role> responseObject = new ResponseEntity<>();
        long total = ((Page<Role>) rList).getTotal();
        responseObject.setData(rList);
        responseObject.setRecordsTotal(total);
        responseObject.setRecordsFiltered(total);
        return responseObject;
    }


    /**
     * 获取所有角色
     *
     * @return
     */
    @RequestMapping("/getAllRoles")
    @ResponseBody
    public ResultEntity<List<Role>> getAllRoles() {
        ResultEntity<List<Role>> resultEntity = new ResultEntity<>();
        resultEntity.setData(roleService.getRoleList(null));
        return resultEntity;
    }

    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResultEntity addRole(@ModelAttribute Role role,String sns) {
        ResultEntity resultEntity = new ResultEntity();
        role.setCreatetime(new Date());
        Role tempRole = roleService.getByName(role.getRolename());
        if (null != tempRole) {
            ErrorMsgUtils.createErrorMsg(resultEntity, ResultCodeConstants.ROLE_NAME_EXISTED);
        } else {
            roleService.addRole(role,sns);
            resultEntity.setReturnCode(ResultCode.SUCCESS);
            resultEntity.setMessage("新增成功!");
            resultEntity.setData(role.getRoleid());
        }
        return resultEntity;
    }



    /**
     * 更新角色
     *
     * @param role
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public ResultEntity editRole(@ModelAttribute Role role,String sns) {
        ResultEntity resultEntity = new ResultEntity();
        Role r = roleService.getById(role.getRoleid());
        if (null != r) {
            if (role.getRolename().equals(r.getRolename())) {
                roleService.updateRole(role,sns);
                resultEntity.setReturnCode(ResultCode.SUCCESS);
                resultEntity.setMessage("修改成功!");
            } else {
                Role ug = roleService.getByName(role.getRolename());
                if (null != ug) {
                    ErrorMsgUtils.createErrorMsg(resultEntity, ResultCodeConstants.ROLE_NAME_EXISTED);
                } else {
                    roleService.updateRole(role,sns);
                    resultEntity.setReturnCode(ResultCode.SUCCESS);
                    resultEntity.setMessage("修改成功!");
                }
            }
        }
        return resultEntity;
    }


    /**
     * 删除角色（支持批量）
     *
     * @param ids
     * @return
     */
    @RequestMapping("/remove")
    @ResponseBody
    public ResultEntity removeRole(String ids) {
        ResultEntity resultEntity = new ResultEntity();
        roleService.removeRole(ids);
        resultEntity.setReturnCode(ResultCode.SUCCESS);
        resultEntity.setMessage("删除成功!");
        return resultEntity;
    }

}
