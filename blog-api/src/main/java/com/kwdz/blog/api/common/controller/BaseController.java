package com.kwdz.blog.api.common.controller;

import com.kwdz.blog.api.common.feign.BaseFeign;
import com.kwdz.blog.api.common.page.PageInfo;
import com.kwdz.blog.api.common.result.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 通用控制器
 */
@Slf4j
public class BaseController<V> {

    @Autowired
    private BaseFeign<V> baseFeign;//通用Feign

    /**
     * 通用分页
     */
    @PostMapping("page")
    public ResultModel<PageInfo<V>> page(V entity) {
        return baseFeign.page(entity);
    }

    /**
     * 通用分页List
     */
    @PostMapping("pageList")
    public ResultModel<List<V>> pageList(V entity) {
        return baseFeign.pageList(entity);
    }

    /**
     * 通用集合List
     */
    @PostMapping("list")
    public ResultModel<List<V>> list(V entity) {
        return baseFeign.list(entity);
    }

    /**
     * 获取单个实体Vo
     */
    @GetMapping("get/{id}")
    public ResultModel<V> get(@PathVariable("id") String id) {
        return baseFeign.get(id);
    }

    /**
     * 获取单个实体Vo且不为空
     */
    @GetMapping("getShow/{id}")
    public ResultModel<V> getShow(@PathVariable("id") String id) {
        return baseFeign.getShow(id);
    }

    /**
     * 保存单个实体
     */
    @PostMapping("save")
    public ResultModel<V> save(V entity) {
        return baseFeign.save(entity);
    }

    /**
     * 保存集合List
     */
    @PostMapping("saveList")
    public ResultModel<List<V>> saveList(@RequestBody List<V> entityList) {
        return baseFeign.saveList(entityList);
    }

    /**
     * 批量删除IDS
     */
    @DeleteMapping("deleteBatch")
    public ResultModel<List<String>> deleteBatch(@RequestBody List<String> ids) {
        return baseFeign.deleteBatch(ids);
    }

}
