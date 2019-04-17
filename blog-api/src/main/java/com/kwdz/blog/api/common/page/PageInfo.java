package com.kwdz.blog.api.common.page;

import lombok.Data;

import java.util.List;

/**
 * 分页结果（针对JqGrid前端插件）
 */
@Data
public class PageInfo<V> {
    private int page;//当前页码
    private int pageSize;//页面大小
    private String sidx;//排序字段
    private String sord;//排序方式
    private V postData;//查询参数

    private List<V> rows;//分页结果
    private int records;//总记录数
    private int total;//总页数
}
