package com.sweii.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;



/**
 * DAO��JDBC����
 * @author duncan
 * 2009-2009-10-10-����09:02:24
 */
public class SimpleJdbcDao extends SimpleJdbcDaoSupport {

	/**
	* �ṩ�Ա�ĸ��ĺ�ɾ������
	* 
	* @param sql
	*            Ҫִ�е�sql���
	* @param args
	*            ���
	* @return Ӱ�������
	*/
	public int update(String sql, Object... args) {
	   return this.getSimpleJdbcTemplate().update(sql, args);
	}

	/**
	* �������¶�����¼
	* 
	* @param sql
	*            ����sql��ɵ�����(����������)
	* @see �������ļ�: <br>
	*      getJdbcTemplate().batchUpdate(String[]
	*      sql,BatchPreparedStatementSetter pss)
	* @return Ӱ����������
	*/
	public int[] batchUpdate(String[] sql) {
	   return this.getJdbcTemplate().batchUpdate(sql);
	}

	/**
	* ��ȡ����
	* 
	* @param countSql
	*            ����������sql���
	* @return
	*/
	public long countRows(String countSql) {
	   return this.getJdbcTemplate().queryForLong(countSql);
	}

	/**
	* ��ȡ���ص�Connection����
	* 
	* @return
	*/
	public Connection getNativeConn() {

	   // �ӵ�ǰ�̰߳󶨵��������ӻ�ȡ����
	   Connection conn = DataSourceUtils.getConnection(this.getJdbcTemplate()
	     .getDataSource());
	   try {
	    conn = this.getJdbcTemplate().getNativeJdbcExtractor()
	      .getNativeConnection(conn);
	   } catch (SQLException e) {
	    e.printStackTrace();
	    return null;
	   }

	   return conn;
	}

	/**
	* ��öϿ����ݿ����ӵ��м�,�������������ڴ�,�ܵ�maxSize������
	* 
	* @param sql
	*            Ҫִ�е�sql����?ռλ��
	* @param params
	*            ���ռλ��������
	* @param types
	*            ����������(java.sql.Types�еĳ���) ����:new
	*            int[]{Types.VARCHAR,Types.DATE}
	* @return Ӱ�������<br>
	*         <b>ע:</b> params��typesͬʱΪ��,sqlΪ����?ռλ��;����typesΪ��ʱ,��springȥ�²�����
	*/
	public SqlRowSet queryForRowSet(String sql, Object[] params, int[] types) {

	   if (params != null && types != null) {
	    return this.getJdbcTemplate().queryForRowSet(sql, params, types);
	   } else if (params != null && types == null) {
	    return this.getJdbcTemplate().queryForRowSet(sql, params);
	   } else {
	    return this.getJdbcTemplate().queryForRowSet(sql);
	   }
	}
	
	/**
	* �ṩ�Ա�ĸ��ĺ�ɾ������
	* 
	* @param hql
	*            ʹ��������������sql���
	* @param sps
	*            ����:<br>
	*            new BeanPropertySqlParamterSource(javaBean������Ҫ������������Ӧ);<br>
	*            new MapSqlParameterSource().add("��������",������Ӧ��ֵ).add()...������ʽ����
	* @return KeyHolder���������߶���;�������������,KeyHolder��������������ֵ<br>
	*         ��3�������ɵ���:<br>
	*         getKey()һ����������<br>
	*         getKeys()��������Map�ṹ<br>
	*         getKeyList()��������ɶ��Map��ɵ�List
	*/
	public KeyHolder updateNamedParamer(String hql, SqlParameterSource sps) {
	   KeyHolder keyHolder = new GeneratedKeyHolder();
	   this.getSimpleJdbcTemplate().getNamedParameterJdbcOperations().update(
	     hql, sps, keyHolder);
	   return keyHolder;
	}

	/**
	* ִ��sql���,�紴�����
	* 
	* @param sql
	*/
	public void executeSql(String sql) {
	   this.getJdbcTemplate().execute(sql);
	}

	/**
	* @return ���spring��JdbcTemplateʹ�ø��๦��
	*/
	public JdbcTemplate getSpringJdbcTemplate() {
	   return this.getJdbcTemplate();
	}

	/**
	* ����jdk5.0�﷨��JdbcTemplate�ļ򻯰汾
	* 
	* @return ���spring��SimpleJdbcTemplateʹ�ø��๦��
	*/
	public SimpleJdbcTemplate getSpringSimplaJdbcTemplate() {
	   return this.getSimpleJdbcTemplate();
	}


}

