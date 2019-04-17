package com.kwdz.blog.svc.common.service;


import com.kwdz.blog.api.common.page.PageInfo;
import com.kwdz.blog.api.common.result.ResultModel;
import com.kwdz.blog.api.common.util.FastCopy;
import com.kwdz.blog.svc.common.dao.CommonDao;
import com.kwdz.blog.svc.common.dao.FastPage;
import org.hibernate.jpa.internal.QueryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 通用Service实现类
 *
 * @param <V> 实体Vo
 * @param <E> 实体
 */
public class CommonServiceImpl<V, E> implements CommonService<V, E> {

    private Class<V> entityVoClass;//实体类模型

    private Class<E> entityClass;//实体类

    @Autowired
    private CommonDao<E> commonDao;//注入实体类仓库

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public CommonServiceImpl() {
        Type[] types = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
        this.entityVoClass = (Class<V>) types[0];
        this.entityClass = (Class<E>) types[1];
    }

    /**
     * 获取分页结果PageInfo
     */
    @Override
    public ResultModel<PageInfo<V>> page(V entity) {
        //1.获取分页对象(Page<Entity>)
        Page<E> page = commonDao.findAll(FastPage.getSpecification(entity, entityClass), FastPage.getPageable(entity));
        //2.获取分页对象(PageInfo<EntityVo>)
        PageInfo<V> pageInfo = FastPage.getPageInfo(page, entityVoClass);
        //3.获取组织(Organization)
        return ResultModel.of(pageInfo);
    }

    /**
     * 获取自定义分页结果PageInfo
     */
    public ResultModel<PageInfo<V>> readPage(V entity, Query query) {
        //设置countQuerySQL语句
        Query countQuery = em.createNativeQuery("select count(1) from ( " + ((QueryImpl) query).getHibernateQuery().getQueryString() + " )");
        //设置countQuerySQL参数
        query.getParameters().forEach(parameter -> countQuery.setParameter(parameter.getName(), query.getParameterValue(parameter.getName())));
        //获取分页结果PageInfo
        return readPage(entity, query, countQuery);
    }

    /**
     * 获取自定义分页结果PageInfo
     */
    public ResultModel<PageInfo<V>> readPage(V entity, Query query, Query countQuery) {
        //1.获取分页对象(Page<Entity>/Page<Object[]>)
        Page page = FastPage.readPage(query, FastPage.getPageable(entity), countQuery);
        //2.获取分页对象(PageInfo<EntityVo>)
        PageInfo<V> pageInfo = FastPage.getPageInfo(page, entityVoClass);
        //3.获取组织(Organization)
        return ResultModel.of(pageInfo);
    }


    /**
     * 获取分页集合List
     */
    @Override
    public ResultModel<List<V>> pageList(V entity) {
        List<E> entityList = commonDao.findAll(FastPage.getSpecification(entity, entityClass));
        List<V> entityVoList = FastCopy.copyList(entityList, entityVoClass);
        return ResultModel.of(entityVoList);
    }

    /**
     * 获取普通List
     */
    @Override
    public ResultModel<List<V>> list(V entity) {
        List<E> entityList = commonDao.findAll(Example.of(FastCopy.copy(entity, entityClass)));
        List<V> entityVoList = FastCopy.copyList(entityList, entityVoClass);
        return ResultModel.of(entityVoList);
    }

    /**
     * 获取单个实体
     */
    @Override
    public ResultModel<V> get(String id) {
        E entity = commonDao.findOne(id);
        if (entity == null) {
            throw new RuntimeException("实体ID不存在：" + id + "(" + entityClass + ")");
        }
        V entityVo = FastCopy.copy(entity, entityVoClass);
        return ResultModel.of(entityVo);
    }

    /**
     * 获取单个实体且非空
     */
    @Override
    public ResultModel<V> getShow(String id) {
        E entity = commonDao.findOne(id);
        if (entity == null) {
            try {
                return ResultModel.of(entityVoClass.newInstance());
            } catch (Exception e) {
                return ResultModel.of(null);
            }
        }
        V entityVo = FastCopy.copy(entity, entityVoClass);
        return ResultModel.of(entityVo);
    }

    /**
     * 新增/保存单个实体
     */
    @Override
    public ResultModel<V> save(V entity) {
        try {
            E e = commonDao.save(FastCopy.copy(entity, entityClass));
            return ResultModel.of(FastCopy.copy(e, entityVoClass));
        } catch (Throwable e) {
            //打印SQL最底层异常
            while (e.getCause() != null) {
                e = e.getCause();
            }
            throw new RuntimeException(e);
        }
    }

    /**
     * 新增/保存批量实体
     */
    @Override
    public ResultModel<List<V>> saveList(List<V> entityList) {
        List<V> list = new ArrayList<>();
        if (entityList != null) {
            entityList.forEach((entity) -> list.add(FastCopy.copy(commonDao.save(FastCopy.copy(entity, entityClass)), entityVoClass)));
        }
        return ResultModel.of(list);
    }

    /**
     * 批量删除
     */
    @Override
    public ResultModel<List<String>> deleteBatch(List<String> ids) {
        if (ids != null && ids.size() > 0) {
            for (String id : ids) {
                commonDao.delete(id);
            }
            return ResultModel.of(ids);
        }
        return ResultModel.of(new ArrayList<>());
    }

}
