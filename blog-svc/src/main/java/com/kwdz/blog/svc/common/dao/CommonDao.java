package com.kwdz.blog.svc.common.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 通用数据库操作
 */
@NoRepositoryBean
public interface CommonDao<E> extends JpaRepository<E, String>, JpaSpecificationExecutor<E> {

}
