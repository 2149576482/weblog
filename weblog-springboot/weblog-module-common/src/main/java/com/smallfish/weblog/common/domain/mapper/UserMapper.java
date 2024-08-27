package com.smallfish.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smallfish.weblog.common.domain.dos.UserDO;

import java.time.LocalDateTime;

public interface UserMapper extends BaseMapper<UserDO> {

    /**
     * 从数据库中根据名字查询用户
     */
    default UserDO findByUsername(String username) {
        LambdaQueryWrapper<UserDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserDO::getUsername, username);
        return selectOne(wrapper);
    }

    /**
     * 根据用户修改密码
     * @param username
     * @param password
     * @return
     */
    default int updateAdminUserPassword(String username, String password) {
        LambdaUpdateWrapper<UserDO> wrapper = new LambdaUpdateWrapper<>();

        // 设置更新的字段
        wrapper.set(UserDO::getPassword, password);
        wrapper.set(UserDO::getUpdateTime, LocalDateTime.now());

        wrapper.eq(UserDO::getUsername, username);

        return update(null, wrapper);
    }
}
