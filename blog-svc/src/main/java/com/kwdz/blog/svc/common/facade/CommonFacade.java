package com.kwdz.blog.svc.common.facade;

import com.kwdz.blog.api.common.page.PageInfo;
import com.kwdz.blog.api.common.result.ResultModel;
import com.kwdz.blog.svc.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 通用控制器
 *
 * @param <V> 实体Vo
 * @param <E> 实体
 */
public class CommonFacade<V, E> {

    @Autowired
    private CommonService<V, E> commonService;

    /**
     * 获取分页结果PageInfo
     */
    @PostMapping("page")
    public ResultModel<PageInfo<V>> page(@RequestBody V entity) {
        return commonService.page(entity);
    }

    /**
     * 获取分页集合List
     */
    @PostMapping("pageList")
    public ResultModel<List<V>> pageList(@RequestBody V entity) {
        return commonService.pageList(entity);
    }

    /**
     * 获取普通List
     */
    @PostMapping("list")
    public ResultModel<List<V>> list(@RequestBody V entity) {
        return commonService.list(entity);
    }

    /**
     * 获取单个实体
     */
    @GetMapping("get/{id}")
    public ResultModel<V> get(@PathVariable("id") String id) {
        return commonService.get(id);
    }

    /**
     * 获取单个实体且非空
     */
    @GetMapping("getShow/{id}")
    public ResultModel<V> getShow(@PathVariable("id") String id) {
        return commonService.getShow(id);
    }

    /**
     * 新增/保存单个实体
     */
    @PostMapping("save")
    public ResultModel<V> save(@RequestBody V entity) {
        return commonService.save(entity);
    }

    /**
     * 新增/保存批量实体
     */
    @PostMapping("saveList")
    public ResultModel<List<V>> saveList(@RequestBody List<V> entityList) {
        return commonService.saveList(entityList);
    }

    /**
     * 批量删除
     */
    @DeleteMapping("deleteBatch")
    public ResultModel<List<String>> deleteBatch(@RequestBody List<String> ids) {
        return commonService.deleteBatch(ids);
    }

}
