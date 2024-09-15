package com.smallfish.weblog.admin.controller;

import com.smallfish.weblog.admin.model.vo.user.UpdateAdminUserPasswordReqVO;
import com.smallfish.weblog.admin.service.AdminUserService;
import com.smallfish.weblog.common.aspect.ApiOperationLog;
import com.smallfish.weblog.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@RestController
@RequestMapping("/admin")
@Api(tags = "Admin 用户模块")
public class AdminUserController {

    @Resource
    private AdminUserService adminUserService;

    @PostMapping("user/info")
    @ApiOperationLog(description = "查询用户信息")
    @ApiOperation(value = "查询用户信息")
    public Result findUserInfo() {
        return adminUserService.findUserInfo();
    }
    /**
     * 修改用户密码
     */
    @PostMapping("/password/update")
    @ApiOperation("修改用户密码")
    @ApiOperationLog(description = "修改用户密码")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result updatePassword(@RequestBody UpdateAdminUserPasswordReqVO updateAdminUserPasswordReqVO) {
        return adminUserService.updatePassword(updateAdminUserPasswordReqVO);
    }

}
