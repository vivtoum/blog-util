package com.kwdz.blog.api.common.page;

import lombok.Data;

/**
 * 分页条件（针对JqGrid前端插件）
 */
@Data
public class PageCondition {
    private Integer page;//当前页码
    private Integer rows;//页面大小
    private String sidx;//排序字段
    private String sord;//排序方式
}
