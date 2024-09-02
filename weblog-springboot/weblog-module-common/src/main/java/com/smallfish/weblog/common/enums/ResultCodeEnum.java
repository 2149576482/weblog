package com.smallfish.weblog.common.enums;

import com.smallfish.weblog.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description: 定义 异常响应码 枚举类
 **/
public enum ResultCodeEnum implements BaseExceptionInterface {

    // ----------- 通用异常状态码 -----------
    SYSTEM_ERROR("10000", "出错啦，后台小哥正在努力修复中..."),

    // ----------- 业务异常状态码 -----------
    PRODUCT_NOT_FOUND("20000", "该产品不存在（测试使用）"),
    PARAM_NOT_VALID("10001", "参数错误"),
    LOGIN_FAIL("20000", "登录失败"),
    USERNAME_OR_PWD_ERROR("20001", "用户名或密码错误"),
    UNAUTHORIZED("20002", "无访问权限，请先登录！"),
    USERNAME_NOT_FOUND("20003", "用户名未找到"),
    FORBIDDEN("20004", "演示账号仅支持查询操作!"),
    CATEGORY_NAME_IS_EXISTED("20005", "该分类已存在"),
    TAG_NAME_IS_EXISTED("20006", "该标签已存在"),
    FILE_UPLOAD_FAILED("20007", "文件上传失败!"),
    CATEGORY_NOT_EXISTED("20008", "分类id不存在"),
    ARTICLE_NOT_FOUND("20009", "该文章不存在!"),
    CATEGORY_CAN_NOT_DELETE("20010", "此分类下包含文章, 请先删除对应的文章, 才能删除此分类!"),
    TAG_CAN_NOT_DELETE("20011", "此标签下包含文章，请先删除对应的文章, 才能删除此标签!"),
    TAG_NOT_EXISTED("20012", "标签id不存在！");

    // 异常码
    private String errorCode;
    // 错误信息
    private String errorMessage;

    ResultCodeEnum(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
