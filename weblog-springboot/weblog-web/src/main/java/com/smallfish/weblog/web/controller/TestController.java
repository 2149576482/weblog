package com.smallfish.weblog.web.controller;

import com.smallfish.weblog.common.aspect.ApiOperationLog;
import com.smallfish.weblog.common.domain.dos.UserDO;
import com.smallfish.weblog.common.domain.mapper.UserMapper;
import com.smallfish.weblog.common.enums.ResultCodeEnum;
import com.smallfish.weblog.common.exception.BusinessException;
import com.smallfish.weblog.common.utils.JsonUtil;
import com.smallfish.weblog.common.utils.Result;
import com.smallfish.weblog.web.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@RestController
@Slf4j
@Api(tags = "前台模块")
public class TestController {

    @Resource
    private UserMapper userMapper;

    @PostMapping("/admin/test")
    @ApiOperationLog(description = "测试接口")
    @ApiOperation(value = "测试接口")
    public Result<Object> test(@RequestBody @Validated User user) {
        log.info(JsonUtil.toJsonString(user));
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateDate(LocalDate.now());
        user.setTime(LocalTime.now());
        return Result.ok(user);
    }

    @PostMapping("/admin/update")
    @ApiOperationLog(description = "测试更新接口")
    @ApiOperation(value = "测试更新接口")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result testUpdate() {
        log.info("更新成功...");
        return Result.ok();
    }

    @PostMapping("add")
    @ApiOperationLog(description = "添加用户")
    @ApiOperation(value = "添加用户")
    public Result<Object> insert() {
        // userMapper.insert(UserDO.builder()
        //         .username("small")
        //         .updateTime(new Date())
        //         .createTime(new Date())
        //         .password("root")
        //         .isDeleted(false)
        //         .build());
        // return Result.ok();
        return null;
    }

}
