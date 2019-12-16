package com.sweii.util;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;

/**
 * 
 * @author �ڽ�ƽ
 * @version 1.0
 * ����˵����<BR/>
 * <BR/>
 * ����springframework <a href="http://www.springframework.org" target="_blank">http://www.springframework.org</a> ����ж���JDBC 3.0�ķ�װ��
 * �ٴν��з�װ����Ŀ�����ܹ����õ�ʹ�ò�ͬ������Դ���������ݿ���л���<BR/>
 * 
 */
public final class JdbcHelper {
	
	/**
	 * ����ָ��������Դ����һ���µ� JdbcTemplate ,�ο� springframework 1.2x�е�JDBC�½�
	 * @param ds �������������Դ
	 * @return JdbcTemplate ����һ��JdbcTemplate��ʵ��
	 */
	public static final JdbcTemplate createJdbcTemplate(final DataSource ds){
		return new JdbcTemplate(ds);
	}
	
	/**
	 * ����ָ��������Դ��Native SQL ���е���������������û���ҵ��κζ���Ļ���������null.
	 * @param ds	������Ҫʹ�õ�����Դ
	 * @param sql	����Ҫʹ�õ�Native SQL
	 * @param params	SQL �ж�Ӧ������ֵ
	 * @param mapper	�������ݺ�JAVAʵ�����֮���ӳ���ϵ��
	 * @return	Object ����ʵ��������û���ҵ����ͷ���null
	 */
	public static final Object load(final DataSource ds,final String sql,final Object[] params,RowMapper mapper){
		List results = queryForList(ds,sql,params,mapper);
		if(results!=null && !results.isEmpty())
			return results.get(0);
		else
			return null;
	}
	

	/**
	 * ����ָ����Native SQL ����ʵ�����Ĳ�ѯ�������һ���б�
	 * @param ds	������Ҫʹ�õ�����Դ
	 * @param sql	����Ҫʹ�õ�Native SQL
	 * @param params	SQL �ж�Ӧ������ֵ
	 * @param mapper	�������ݺ�JAVAʵ�����֮���ӳ���ϵ��
	 * @return	List ���û���ҵ��κζ�����ô����һ�� new ArrayList();
	 */
	public static final List queryForList(final DataSource ds,final String sql,final Object[] params,RowMapper mapper){
		JdbcTemplate jt = createJdbcTemplate(ds);
		if(mapper!=null)
			return jt.query(sql, params,mapper);
		else
			return jt.queryForList(sql,params);
	}
	/**
	 * ����ָ����Native SQL ����ʵ�����Ĳ�ѯ�������һ���б�
	 * @param ds	������Ҫʹ�õ�����Դ
	 * @param sql	����Ҫʹ�õ�Native SQL
	 * @param params	SQL �ж�Ӧ������ֵ
	 * @param mapper	�������ݺ�JAVAʵ�����֮���ӳ���ϵ��
	 * @return	List ���û���ҵ��κζ�����ô����һ�� new ArrayList();
	 */
	public static final List queryForList(final DataSource ds,final String sql, Object[] values, RowMapper rowMapper,int pageNo,int pageSize) {
		String paged_sql = sql;
		int start=(pageNo-1)*pageSize;
		int end  =pageNo*pageSize;
		start = start<=0?0:start;
		end   = pageSize;
		Integer firstResult = new Integer(start);
		Integer maxResults = new Integer(end);
		Object[] params = null;
			paged_sql =sql +" limit ?,?";
			if (values == null) {
				params = new Object[]{firstResult, maxResults};
			} else {
				int index = 0;
				params = new Object[values.length + 2];
				for (index = 0; index < values.length; index++) {
					params[index] = values[index];
				}
				params[index + 0] = firstResult;
				params[index + 1] = maxResults;
			}
		List result = JdbcHelper.queryForList(ds,paged_sql, params, rowMapper);
		if (result == null)
			result = new ArrayList();
		return result;
	}
	/**
	 * ����ָ����Native SQL ����ʵ�����Ĳ�ѯ�������һ���б�
	 * @param ds	������Ҫʹ�õ�����Դ
	 * @param sql	����Ҫʹ�õ�Native SQL
	 * @param params	SQL �ж�Ӧ������ֵ
	 * @param mapper	�������ݺ�JAVAʵ�����֮���ӳ���ϵ��
	 * @return	List ���û���ҵ��κζ�����ô����һ�� new ArrayList();
	 */
	public static final int queryForInt(final DataSource ds,final String sql,final Object[] params){
		JdbcTemplate jt = createJdbcTemplate(ds);
		return jt.queryForInt(sql,params);
	}
	/**
	 * ����ָ��������Դ��Native SQL,�������ݿ�ĵ������ݵ� INSERT ����
	 * @param ds	������Ҫʹ�õ�����Դ
	 * @param sql	����Ҫʹ�õ�Native SQL
	 * @param params	SQL �ж�Ӧ������ֵ
	 * @return Integer ����������ݳɹ��������ظ����ݵ�����ֵ(Integer ����),���򽫷���null.
	 */
	public static final Integer save(final DataSource ds,final String sql,final Object[] params){
		JdbcTemplate jt = createJdbcTemplate(ds);
		PreparedStatementCreator psc = new JdbcPreparedStatementCreator(sql,params);
		KeyHolder k = new JdbcKeyHolder();
		jt.update(psc, k);
		if(k.getKey()!=null)
			return new Integer(k.getKey().intValue());
		else
			return null;
	}
	
	/**
	 * ����ָ��������Դ��Native SQL,�������ݿ����ݵ� UPDATE �� EXECUTE ����
	 * @param ds	������Ҫʹ�õ�����Դ
	 * @param sql	����Ҫʹ�õ�Native SQL
	 * @param params	SQL �ж�Ӧ������ֵ
	 * @return int ���ظ�Native SQL�����Ӱ������ݵ�����
	 */
	public static final int update(final DataSource ds,final String sql,final Object[] params){
		JdbcTemplate jt = createJdbcTemplate(ds);
		return jt.update(sql, params);
	}
	/**
	 * �õ�ͳ�Ƶ�Native SQL�ַ���
	 * @param sql
	 * @return
	 */
	private static final String getCountSql(String sql){
		String s = sql.toLowerCase();
		int index = s.indexOf(" from ");
		if(-1 == index)
			throw new RuntimeException("Not a select sql string !");
		index = s.indexOf(" order ");
		String temp = sql;
		if(-1 != index){
			temp = s.substring(0, index);
		}
		s = "select count(*) as total from ( " + temp + " )temp_table_v";
		return s;
	}

}
