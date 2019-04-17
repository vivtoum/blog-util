package com.kwdz.blog.svc.common.service;



import com.kwdz.blog.api.common.page.PageInfo;
import com.kwdz.blog.api.common.result.ResultModel;

import java.util.List;

/**
 * 通用Service
 *
 * @param <V> 实体Vo
 * @param <E> 实体
 */
public interface CommonService<V, E> {

    /**
     * 获取分页结果PageInfo
     */
    ResultModel<PageInfo<V>> page(V entity);

    /**
     * 获取分页集合List
     */
    ResultModel<List<V>> pageList(V entity);

    /**
     * 获取普通List
     */
    ResultModel<List<V>> list(V entity);

    /**
     * 获取单个实体
     */
    ResultModel<V> get(String id);

    /**
     * 获取单个实体且非空
     */
    ResultModel<V> getShow(String id);

    /**
     * 新增/保存单个实体
     */
    ResultModel<V> save(V entity);

    /**
     * 新增/保存批量实体
     */
    ResultModel<List<V>> saveList(List<V> entityList);

    /**
     * 批量删除
     */
    ResultModel<List<String>> deleteBatch(List<String> ids);

}
