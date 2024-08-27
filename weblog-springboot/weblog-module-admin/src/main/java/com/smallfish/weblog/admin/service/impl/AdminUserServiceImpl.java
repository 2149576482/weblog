package com.smallfish.weblog.admin.service.impl;

import com.smallfish.weblog.admin.model.vo.user.FindUserInfoRspVO;
import com.smallfish.weblog.admin.model.vo.user.UpdateAdminUserPasswordReqVO;
import com.smallfish.weblog.admin.service.AdminUserService;
import com.smallfish.weblog.common.domain.mapper.UserMapper;
import com.smallfish.weblog.common.enums.ResultCodeEnum;
import com.smallfish.weblog.common.utils.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description: 用户实现类
 **/
@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 修改密码
     */
    @Override
    public Result updatePassword(UpdateAdminUserPasswordReqVO userPasswordReqVO) {

        String username = userPasswordReqVO.getUsername();
        String password = userPasswordReqVO.getPassword();

        // 加密密码
        String encodePassword = passwordEncoder.encode(password);

        // 更新到数据库
        int count = userMapper.updateAdminUserPassword(username, encodePassword);

        // 返回数据
        return count == 1 ? Result.ok() : Result.fail(ResultCodeEnum.USERNAME_NOT_FOUND);
    }

    @Override
    public Result findUserInfo() {

        // 获取 存储在 请求头中的信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 拿到用户名
        String username = authentication.getName();

        // 返回
        return Result.ok(FindUserInfoRspVO.builder().username(username).build());
    }
}
