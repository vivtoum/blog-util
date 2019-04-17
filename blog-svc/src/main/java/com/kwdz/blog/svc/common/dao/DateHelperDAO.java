package com.kwdz.blog.svc.common.dao;

import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;

public interface DateHelperDAO {

	/**
	 *功能：--查看数据库当前日期、时间
	 * @return
	 */
	 @Query(value = "select sysdate from dual",nativeQuery = true)
	 Timestamp getCurrentDatabaseTime() ;

	/**
	 *
	 * @return
	 */
	@Query(value = "select dbtimezone from dual",nativeQuery = true)
	 String getDatabaseTimezone();

}
