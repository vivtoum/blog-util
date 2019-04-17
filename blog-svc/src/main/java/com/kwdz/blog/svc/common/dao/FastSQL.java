package com.kwdz.blog.svc.common.dao;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.kwdz.blog.api.common.page.Between;
import com.kwdz.blog.api.common.page.Like;
import com.kwdz.blog.api.common.page.PageCondition;
import com.kwdz.blog.api.common.util.FastDateFormat;
import com.kwdz.blog.api.common.util.FastLocale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.Transient;
import javax.persistence.criteria.Predicate;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class FastSQL {

    /**
     * 自动拼接原生SQL的“and”查询条件
     *
     * @param src              实体对象
     * @param sql              待拼接SQL
     * @param ignoreProperties 忽略属性
     */
    public static void appendQueryColumns(Object src, StringBuilder sql, String... ignoreProperties) {

        List<String> ignoreList1 = Arrays.asList(ignoreProperties);
        List<String> ignoreList2 = Arrays.asList("class", "pageable", "page", "rows", "sidx", "sord");

        //1.获取Bean
        BeanWrapper srcBean = new BeanWrapperImpl(src);
        //2.获取Bean的属性描述
        PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
        //3.获取Bean的非空属性
        for (PropertyDescriptor p : pds) {
            String propertyName = p.getName();
            Object propertyValue = srcBean.getPropertyValue(propertyName);
            if (!StringUtils.isEmpty(propertyValue)) {
                //映射关系：对象属性(驼峰)->数据库字段(下划线)
                if (!ignoreList1.contains(propertyName) && !ignoreList2.contains(propertyName)) {
                    String column = new PropertyNamingStrategy.SnakeCaseStrategy().translate(propertyName).toLowerCase();
                    sql.append(" and " + column + " = '" + propertyValue + "'");
                }
            }
        }
    }

    /**
     * 自动拼接原生SQL的“and”查询条件,支持大佬的贼方法：@Like 简繁体中英文 @Between 时间范围
     *
     * @param entity           实体对象
     * @param sql              待拼接SQL
     * @param ignoreProperties 忽略属性
     */
    public static void appendQueryColumnsSupportLike(Object entity, StringBuilder sql, String... ignoreProperties) {

        try {

            List<String> ignoreList1 = Arrays.asList(ignoreProperties);
            List<String> ignoreList2 = Arrays.asList("class", "pageable", "page", "rows", "sidx", "sord");

            //拼接查询条件
            List<Predicate> predicates = new ArrayList<>();
            for (Field field : entity.getClass().getDeclaredFields()) {
                //获取授权
                field.setAccessible(true);
                String fieldName = field.getName();
                Object fieldValue = field.get(entity);
                if (!field.isAnnotationPresent(Transient.class)) {
                    String column = new PropertyNamingStrategy.SnakeCaseStrategy().translate(fieldName).toLowerCase();
                    if (!StringUtils.isEmpty(fieldValue)) {
                        //映射关系：对象属性(驼峰)->数据库字段(下划线)
                        if (!ignoreList1.contains(fieldName) && !ignoreList2.contains(fieldName)) {
                            //开启模糊查询
                            if (field.isAnnotationPresent(Like.class)) {
                                //开启模糊查询（严格匹配查询、转大写简体查询、转大写繁体查询）
                                String columnLike = ("upper(" + column + ")") + (" like '%" + StringUtils.trimWhitespace(String.valueOf(fieldValue)) + "%'").toUpperCase();
                                sql.append(" and (")
                                        .append(columnLike).append(" or ")
                                        .append(FastLocale.getSimplifiedChinese(columnLike)).append(" or ")
                                        .append(FastLocale.getTraditionalChinese(columnLike))
                                        .append(")");
                            }
                            //开启等值查询
                            else {
                                sql.append(" and " + column + " = '" + fieldValue + "'");
                            }
                        }
                    } else {
                        //开启区间查询
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
                            if ("java.util.Date".equals(field.getType().getName())) {
                                appendTimeColumns(column, (Date) minVal, (Date) maxVal, sql);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("拼接模糊查询SQL报错了！", e);
        }

    }

    /**
     * 拼接时间范围SQL
     *
     * @param columnName   时间字段名
     * @param startTimeMin 时间最小值（Date）
     * @param startTimeMax 时间最小值（Date）
     * @param sql          待拼接的SQL
     */
    public static void appendTimeColumns(String columnName, Date startTimeMin, Date startTimeMax, StringBuilder sql) {
        if (startTimeMin != null) {
            sql.append(" and ").append(columnName).append(" >=to_date('temp_name','yyyy-MM-dd hh24:mi:ss') ".replaceAll("temp_name", FastDateFormat.get().formatDate(startTimeMin, FastDateFormat.CHINA_PATTERN)));
        }

        if (startTimeMax != null) {
            sql.append(" and ").append(columnName).append(" <=to_date('temp_name','yyyy-MM-dd hh24:mi:ss') ".replaceAll("temp_name", FastDateFormat.get().formatDate(startTimeMax, FastDateFormat.CHINA_PATTERN)));
        }
    }

    /**
     * 拼接排序SQL
     *
     * @param entity 实体类
     * @param sql    待拼接的SQL
     */
    public static void orderByColumn(PageCondition entity, StringBuilder sql) {
        String sidx = entity.getSidx();
        String sord = entity.getSord();

        if (!StringUtils.isEmpty(sidx)) {
            //1.获取Bean
            BeanWrapper srcBean = new BeanWrapperImpl(entity);
            //2.获取Bean的属性描述
            PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
            //3.获取符合的排序字段名
            for (PropertyDescriptor p : pds) {
                String propertyName = p.getName();
                if (sidx.equals(propertyName)) {
                    sql.append("order by ").append(getUnderScoreColumn(sidx)).append("desc".equalsIgnoreCase(sord) ? " desc" : " asc");
                }
            }
        }
    }

    /**
     * 拼接排序SQL
     *
     * @param entity 实体类
     * @param sql    待拼接的SQL
     */
    public static void orderByColumnAndId(PageCondition entity, StringBuilder sql) {
        String sidx = entity.getSidx();
        String sord = entity.getSord();

        if (!StringUtils.isEmpty(sidx)) {
            //1.获取Bean
            BeanWrapper srcBean = new BeanWrapperImpl(entity);
            //2.获取Bean的属性描述
            PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
            //3.获取符合的排序字段名
            for (PropertyDescriptor p : pds) {
                String propertyName = p.getName();
                if (sidx.equals(propertyName)) {
                    //分页出现重复数据，原因：单个排序字段条件不确定，需要多order by id
                    sql.append("order by ").append(FastSQL.getUnderScoreColumn(sidx)).append(", id").append("desc".equalsIgnoreCase(sord) ? " desc" : " asc");
                }
            }
        }
    }

    /**
     * 驼峰属性转下划线
     *
     * @param propertyName 实体类属性名
     */
    public static String getUnderScoreColumn(String propertyName) {
        return new PropertyNamingStrategy.SnakeCaseStrategy().translate(propertyName).toLowerCase();
    }
}
