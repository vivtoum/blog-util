package com.kwdz.blog.svc.common.dao;

import com.kwdz.blog.api.common.page.*;
import com.kwdz.blog.api.common.util.FastCopy;
import com.kwdz.blog.api.common.util.FastLocale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import javax.persistence.Transient;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 分页工具类
 */
@Component
public class FastPage {


    /**
     * 获取JPA分页条件
     */
    public static Pageable getPageable(Object entityVo) {
        //若不存在分页 则抛出异常信息
        if (!(entityVo instanceof PageCondition)) {
            throw new RuntimeException(entityVo.getClass().getSimpleName() + "未继承PageCondition，无法获取分页条件。");
        }
        //获取分页条件
        PageCondition pageCondition = (PageCondition) entityVo;
        Integer page = pageCondition.getPage();
        Integer rows = pageCondition.getRows();
        String sidx = pageCondition.getSidx();
        String sord = pageCondition.getSord();
        //处理非法页码
        if (page == null || page < 0) {
            page = 1;
        }
        //处理非法页面大小
        if (rows == null || rows < 0) {
            rows = 10;
        }
        //获取排序信息
        if (!StringUtils.isEmpty(sidx)) {
            return new PageRequest(page - 1, rows, new Sort(sord.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sidx));
        }
        return new PageRequest(page - 1, rows);
    }

    /**
     * 获取统一分页结果
     */
    public static <V> PageInfo<V> getPageInfo(Page page, Class<V> entityVoClass) {
        int records = (int) page.getTotalElements();
        int pageSize = page.getSize();
        int total = records % pageSize == 0 ? records / pageSize : records / pageSize + 1;

        PageInfo<V> pageInfo = new PageInfo<>();
        pageInfo.setPage(page.getNumber() + 1);//页码
        pageInfo.setPageSize(pageSize);//页面大小
        List pageList = page.getContent();
        pageInfo.setRows(FastCopy.copyList(pageList, entityVoClass));//分页结果
        if (pageList.size() > 0 && pageList.get(0) instanceof Object[]) {
            pageInfo.setRows(FastCopy.copyListByObjectArray(pageList, entityVoClass));//分页结果
        }
        pageInfo.setRecords(records);//总记录数
        pageInfo.setTotal(total);//总页数
        return pageInfo;
    }

    /**
     * 获取JPA查询条件
     */
    public static <E> Specification<E> getSpecification(Object entityVo, Class<E> entityClass) {
        return (root, query, cb) -> {
            try {
                //拼接查询条件
                List<Predicate> predicates = new ArrayList<>();
                E entity = FastCopy.copy(entityVo, entityClass);
                for (Field field : entity.getClass().getDeclaredFields()) {
                    field.setAccessible(true);//获取授权
                    String fieldName = field.getName();
                    Object fieldValue = field.get(entity);
                    if (!field.isAnnotationPresent(Transient.class)) {
                        if (!StringUtils.isEmpty(fieldValue)) {
                            //开启模糊查询
                            if (field.isAnnotationPresent(Like.class)) {
                                Path fieldColumn = root.get(fieldName);
                                String fieldValuePattern = ("%" + StringUtils.trimWhitespace(String.valueOf(fieldValue)) + "%").toUpperCase();
                                //开启模糊查询（严格匹配查询、转大写简体查询、转大写繁体查询）
                                predicates.add(cb.and(cb.or(
                                        cb.like(cb.upper(fieldColumn), fieldValuePattern),
                                        cb.like(cb.upper(fieldColumn), FastLocale.getSimplifiedChinese(fieldValuePattern)),
                                        cb.like(cb.upper(fieldColumn), FastLocale.getTraditionalChinese(fieldValuePattern))
                                )));
                            }
                            //开启等值查询
                            else {
                                predicates.add(cb.equal(root.get(fieldName), fieldValue));
                            }
                        } else {
                            if (field.isAnnotationPresent(Between.class)) {
                                //获取最小值
                                Field minField = entity.getClass().getDeclaredField(field.getAnnotation(Between.class).min());
                                minField.setAccessible(true);
                                Object minVal = minField.get(entity);
                                //获取最大值
                                Field maxField = entity.getClass().getDeclaredField(field.getAnnotation(Between.class).max());
                                maxField.setAccessible(true);
                                Object maxVal = maxField.get(entity);
                                //开启区间查询
                                if (field.getType().getName().equals("java.util.Date") || field.getType().getName().equals("java.sql.Timestamp")) {
                                    if (!StringUtils.isEmpty(minVal)) {
                                        predicates.add(cb.greaterThanOrEqualTo(root.get(fieldName).as(Date.class), (Date) minVal));
                                    }
                                    if (!StringUtils.isEmpty(maxVal)) {
                                        predicates.add(cb.lessThanOrEqualTo(root.get(fieldName).as(Date.class), (Date) maxVal));
                                    }
                                }
                            }
                        }
                    }
                }
                return cb.and(predicates.toArray(new Predicate[]{}));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    /**
     * 获取JPA分页结果
     */
    public static Page readPage(Query query, Pageable pageable, Query countQuery) {
        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        return PageableExecutionUtils.getPage(query.getResultList(), pageable, () -> executeCountQuery(countQuery));
    }

    //计算总数
    private static Long executeCountQuery(Query countQuery) {
        Assert.notNull(countQuery, "TypedQuery must not be null!");

        List<Number> totals = countQuery.getResultList();
        Long total = 0L;
        for (Number number : totals) {
            if (number != null) {
                total += number.longValue();
            }
        }
        return total;
    }
}
