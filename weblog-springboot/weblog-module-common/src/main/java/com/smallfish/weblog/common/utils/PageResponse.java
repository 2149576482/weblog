package com.smallfish.weblog.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description: 分页响应类
 **/
@Data
public class PageResponse<T> extends Result<List<T>> {

    /**
     * 总记录数
     */
    private Long total = 0L;

    /**
     * 每页记录数
     */
    private Long size = 10L;

    /**
     * 当前页
     */
    private Long current;

    /**
     * 总页数
     */
    private Long pages;

    /**
     * 成功响应
     */
    public static <T> PageResponse<T> success(IPage page, List<T> data) {
        PageResponse<T> pageResponse = new PageResponse<>();
        pageResponse.setSuccess(true);
        pageResponse.setCurrent(Objects.isNull(page) ? 1L : page.getCurrent());
        pageResponse.setSize(Objects.isNull(page) ? 10L : page.getSize());
        pageResponse.setTotal(Objects.isNull(page) ? 0L : page.getTotal());
        pageResponse.setPages(Objects.isNull(page) ? 0L : page.getPages());
        pageResponse.setData(data);
        return pageResponse;
    }

    public static <T> PageResponse<T> success(long total, long size, long current, List<T> data) {
        PageResponse<T> pageResponse = new PageResponse<>();
        pageResponse.setSuccess(true);
        pageResponse.setCurrent(current);
        pageResponse.setSize(size);
        long pages = total % size == 0 ? total : total / size + 1;
        pageResponse.setTotal(total);
        pageResponse.setPages(pages);
        pageResponse.setData(data);
        return pageResponse;
    }

}
