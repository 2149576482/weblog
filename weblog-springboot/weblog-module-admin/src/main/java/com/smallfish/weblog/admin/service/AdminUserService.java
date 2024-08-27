package com.smallfish.weblog.admin.service;

import com.smallfish.weblog.admin.model.vo.user.UpdateAdminUserPasswordReqVO;
import com.smallfish.weblog.common.utils.Result;

public interface AdminUserService {

    /**
     * 修改密码
     */
    Result updatePassword(UpdateAdminUserPasswordReqVO updateAdminUserPasswordReqVO);

    /**
     * 查询用户信息
     * @return
     */
    Result findUserInfo();
}
