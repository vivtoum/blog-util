package com.kwdz.blog.svc.common.dao;

import com.kwdz.blog.api.common.util.FastCopy;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

/**
 * 通用Repository实现类
 */
public class SimpleJpaRepository2<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> {

    private final JpaEntityInformation<T, ?> entityInformation;
    private final EntityManager em;

    @SuppressWarnings("all")
    public SimpleJpaRepository2(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        em = entityManager;
    }

    /**
     * 调整Save方法，支持新增和部分更新
     */

    @Transactional
    @Override
    public <S extends T> S save(S entity) {
        //获取ID
        ID entityId = (ID) entityInformation.getId(entity);
        Optional<T> optionalT;
        if (StringUtils.isEmpty(entityId)) {
            String uuid = UUID.randomUUID().toString();
            //防止UUID重复
            if (findOne((ID) uuid) == null) {
                uuid = UUID.randomUUID().toString();
            }
            //若ID为空 则设置为UUID
            new BeanWrapperImpl(entity).setPropertyValue(entityInformation.getIdAttribute().getName(), uuid);
            //标记为新增数据
            optionalT = Optional.empty();
        } else {
            //若ID非空 则查询最新数据
            optionalT = Optional.ofNullable(findOne(entityId));
        }
        //获取空属性并处理成null
        String[] nullProperties = FastCopy.getNullProperties(entity);
        //若根据ID查询结果为空
        if (!optionalT.isPresent()) {
            em.persist(entity);//新增
            return entity;
        } else {
            //1.获取最新对象
            T target = optionalT.get();
            //2.将非空属性覆盖到最新对象
            BeanUtils.copyProperties(entity, target, nullProperties);
            //3.更新非空属性
            em.merge(target);
            return entity;
        }

    }
}
