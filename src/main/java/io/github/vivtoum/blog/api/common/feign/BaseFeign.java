package io.github.vivtoum.blog.api.common.feign;

import io.github.vivtoum.blog.api.common.page.PageInfo;
import io.github.vivtoum.blog.api.common.result.ResultModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 通用Feign
 */
public interface BaseFeign<V> {

    /**
     * 获取分页对象
     */
    @PostMapping(value = "page")
    ResultModel<PageInfo<V>> page(V entity);

    /**
     * 获取分页的全部集合  PS：解决微服务降级后 通过Form表单提交格式为application/xml的问题。
     */
    @PostMapping(value = "pageList", produces = "application/json;charset=UTF-8")
    ResultModel<List<V>> pageList(V entity);

    /**
     * 获取集合
     */
    @PostMapping("list")
    ResultModel<List<V>> list(V entity);

    /**
     * 获取单个
     */
    @GetMapping(value = "get/{id}", produces = "application/json;charset=UTF-8")
    ResultModel<V> get(@PathVariable("id") String id);

    /**
     * 获取单个（非空）
     */
    @GetMapping(value = "getShow/{id}", produces = "application/json;charset=UTF-8")
    ResultModel<V> getShow(@PathVariable("id") String id);

    /**
     * 新增/更新操作
     */
    @PostMapping("save")
    ResultModel<V> save(V entity);

    /**
     * 批量新增/更新操作
     */
    @PostMapping("saveList")
    ResultModel<List<V>> saveList(List<V> entityList);

    /**
     * 批量删除
     */
    @DeleteMapping("deleteBatch")
    ResultModel<List<String>> deleteBatch(List<String> ids);


}
