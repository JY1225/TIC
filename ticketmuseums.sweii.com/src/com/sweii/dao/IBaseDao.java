package com.sweii.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.sweii.util.PageUtil;

/**
 * BaseDao�ӿ���
 * 
 * @author duncan
 * @createTime 2009-11-24
 */
public interface IBaseDao<T, PK extends Serializable> {
    
	public Session openSession();
	/**
	 * ��յ�ǰ�߳�Session�Ļ��������г־û����󣬴Ӷ���ʱ�ͷ���ռ�õ��ڴ�ռ�
	 * 
	 * @author duncan
	 * @createTime 2009-11-24
	 */
	public void clear();

	/**
	 * �����ύ��ǰ�߳�Session�Ĳ���,�ύ�󲻿�rollback,�����Ҫrollback������
	 * ����commit֮ǰ��дlog������Ҫʱ�ɸ���log�������ݻָ���
	 * 
	 * @author duncan
	 * @createTime 2009-11-24
	 */
	public void commit();

	/**
	 *��ǰ�߳�Session�Ĳ������������ݿ�ͬ������,��δ�ύ,��rollback
	 * Ŀ��:�ɵ���Щ��������Session�����еĳ־û��������,ʹ���ڴ����,�������ϵͳ�����½�,
	 * ����flush()�󣬿ɵ���evict()���־û������Session������ɾ�����Ӷ���ʱ�ͷ���ռ�õ��ڴ�ռ䡣
	 * ���ش������ݽ��и��²���ʱ,��ʹ�ô˷����Ż�ϵͳ
	 * 
	 * @author duncan
	 * @lastEdit 2007-05-26
	 */
	public void flush();

	/**
	 * ����ִ�в�ѯ�ӳټ��ض���,֧�ּ��ϲ���
	 * 
	 * @param object
	 *            �������ض���
	 * @author duncan
	 * @createTime 2009-11-24
	 */
	public void initialize(T entityObject);

	/**
	 * �ӵ�ǰ�߳�Session�������������ָ���ĳ־û�����
	 * 
	 * @param object
	 *            �־û������ӳټ��ض���
	 * @author duncan
	 * @createTime 2009-11-24
	 */
	public void evict(T entityObject);

	/**
	 * �ӵ�ǰ�߳�Session�������������ָ���ĳ־û�����
	 * 
	 * @param entities
	 *            �־û������ӳټ��ض���
	 * @author duncan
	 * @createTime 2009-11-24
	 */
	public void evict(Collection<T> entities);

	/**
	 * �ж�Session�������Ƿ���ڲ���ָ���ĳ־û����� ��;���������ж��Ƿ��Ѽ��أ����Ƿ�ʹ�ù�initialize()���ع��ö���
	 * 
	 * @param object
	 *            �־û�����
	 * @author duncan
	 * @createTime 2009-11-24
	 * @return boolean true��ʾ����,false��ʾ������
	 */
	public boolean contain(T entityObject);

	/**
	 * ͨ��ID���س־û�����ִ�й��̣�
	 * ��ID��ΪKey��ѯ��ǰ�߳�Session����(һ�����棩�Ƿ�����,�����session������ֱ�ӷ��ظó־û�����,
	 * �����ѯ���������е�"�໺��"�Ƿ����У�������Ӷ��������з��ظó־û����󣬲���������һ��select * from table where
	 * id=? ��sql�����ظó־û�����������ݿ�û�ж�ӦID�����ݼ�¼���򷵻�null��������ڼ�¼����������ļ���Ϣ���س־û�����
	 * �����ö������session ���棬���������"�໺��"�������ö������"�໺��"�У���iterator()����ʹ�ú�"��ѯ����"ʹ�á�
	 * 
	 * @param id
	 *            ��ID
	 * @author duncan
	 * @createTime 2009-11-24
	 * @return T ���س־û�����,����Ϊnullֵ
	 */
	public T getById(PK id);

	/**
	 * ͨ��ID���س־û�����ִ�й�����getByPk()���ƣ�Ψһ��ͬ�ľ��ǵ����ݿ�û�ж�ӦID�ļ�¼ʱ��getByPk()����nullֵ����
	 * loadByPk()�����׳��쳣��
	 * 
	 * @param id
	 *            ��ID
	 * @author duncan
	 * @createTime 2009-11-24
	 * @return object ���س־û�����,������Ϊnullֵ
	 */
	public T load(PK id);

	/**
	 * ��ȡʵ�����͵�ȫ�������б�
	 * 
	 * @author duncan
	 * @createTime 2009-11-24
	 * @version 1.0
	 * @return List<T>
	 */
	public List<T> getAll();

	/**
	 * �����ݿ�־û����󣨼������ݿ����һ����¼�� ����������ڱ�session�г־û���,�����κ��¡�<br>
	 * �����һ��seesionӵ����ͬ�ĳ־û���ʶ,�׳��쳣��<br>
	 * ���û�г־û���ʶ����,����save()��<br>
	 * ����־û���ʶ������һ���µ�ʵ��������,����save()��<br>
	 * ����Ǹ����汾��Ϣ��(version��timestamp)�Ұ汾���Ա���Ϊ�µ�ʵ���������save()��<br>
	 * �������update()���¹����йܶ���
	 * 
	 * @param object
	 *            �������
	 * @author duncan
	 * @createTime 2009-11-24
	 * 
	 */
	public void save(T entityObject);
	/**
	 * 
	 * @author duncan
	 * @createTime 2011-1-28
	 * @version 1.0
	 */
	public void saveEntity(Object entity);

	/**
	 * ��������ʵ��,ִ��saveOrUpdate����
	 * 
	 * @param coll
	 *            ����
	 * @author duncan
	 * @createTime 2009-11-24
	 */
	public void saveAll(Collection<T> coll);

	/**
	 * �����ݿ�־û��������¼�¼,��������:
	 * ���ж�entityObject����ID�Ƿ�Ϊ�գ�Ϊ����ִ����save()������ͬ�Ĳ������������ݿ����һ����¼,�����µ�ID�������ID��Ϊ�գ�
	 * ����һ��select * from table where
	 * id=?��SQL�����ظü�¼�����������ָ��ID�ļ�¼����ִ����save()������ͬ�Ĳ������������ݿ����һ����¼,�����µ�ID����
	 * �����¼���ڣ�����ش����ݿ��м��س־û����������object��ֵ�Ա�,��ֵ��ͬ���������ɸ���SQL���,�����и��²�����
	 * 
	 * @param object
	 *            �������
	 * @author duncan
	 * @createTime 2009-11-24
	 * 
	 */
	public void saveOrUpdate(T entityObject);

	/**
	 * �����������ݼ�¼,hql�����?��ʽ���ܲ���
	 * 
	 * @param hql
	 *            hibernate�����������
	 * @param values
	 *            ����ֵ
	 * @author duncan
	 * @createTime 2009-11-24
	 * 
	 */
	public void update(String hql, Object... values);

	/**
	 * ����ʵ��
	 * 
	 * @param entityObject
	 * @author lizhongren 2009-12-17 ����04:17:55
	 */
	public void update(T entityObject);
	/**
	 * ����ʵ��
	 * 
	 * @param entityObject
	 * @author lizhongren 2009-12-17 ����04:17:55
	 */
	public void updateEntity(Object entityObject);

	/**
	 * ͨ��IDɾ������
	 * 
	 * @param PK
	 *            id��
	 * @author duncan
	 * @createTime 2009-11-24
	 * 
	 */
	public void deleteById(PK id);

	/**
	 * ɾ������
	 * 
	 * @param T
	 *            �־ö���
	 * @author duncan
	 * @createTime 2009-11-24
	 * 
	 */
	public void delete(T entityObject);

	/**
	 * ����ɾ�����ݼ�¼,hql�����?��ʽ���ܲ���
	 * 
	 * @param hql
	 *            hibernate����ɾ�����,��delete Entity,��ɾ�����м�¼
	 * @param values
	 *            ����ֵ
	 * @author duncan
	 * @createTime 2009-11-24
	 * 
	 */
	public void delete(String hql, Object... values);

	/**
	 * ɾ�����ʵ��
	 * 
	 * @param entities
	 * @author lizhongren 2009-12-20 ����11:10:42
	 */
	public void deleteAll(Collection<T> entities);

	/**
	 * ����hql��ѯ����ѯ���ݿⲢ���ز�ѯ����������ĵ����־û����󣨼��Ƿ��ز�ѯ����ĵ�һ����¼��,
	 * ֱ��ʹ��HibernateTemplate��find����. �����Ĳ���SQL��䣺mysqlΪlimit 0,1
	 * 
	 * @param hql
	 *            ָ����ѯ���
	 * @author duncan
	 * @createTime 2009-11-24
	 * @return T ֻ���ز�ѯ�����һ����¼,�����ڼ�¼����null
	 */
	public T findEntiry(String hql, Object... values);
	/**
	 * ����hql��ѯ����ѯ���ݿⲢ���ز�ѯ����������ĵ����־û����󣨼��Ƿ��ز�ѯ����ĵ�һ����¼��,
	 * ֱ��ʹ��HibernateTemplate��find����. �����Ĳ���SQL��䣺mysqlΪlimit 0,1
	 * 
	 * @param hql
	 *            ָ����ѯ���
	 * @author duncan
	 * @createTime 2009-11-24
	 * @return T ֻ���ز�ѯ�����һ����¼,�����ڼ�¼����null
	 */
	public Object findObjectEntiry(String hql, Object... values);

	/**
	 * ����hql��ѯ����ѯ���ݿⲢ���ز�ѯ����������ĳ־û����󼯺�,ֱ��ʹ��HibernateTemplate��find����.
	 * 
	 * @param hql
	 *            ָ����ѯ���
	 * @param params
	 *            ����ֵ
	 * @author duncan
	 * @createTime 2009-11-24
	 * @return ���ز�ѯ��������ĳ־û����󼯺�
	 */
	public List<T> find(String hql, Object... values);
	/**
	 * ����hql��ѯ����ѯ���ݿⲢ���ز�ѯ����������ĳ־û����󼯺�,ֱ��ʹ��HibernateTemplate��find����.
	 * 
	 * @param hql
	 *            ָ����ѯ���
	 * @param params
	 *            ����ֵ
	 * @author duncan
	 * @createTime 2009-11-24
	 * @return ���ز�ѯ��������ĳ־û����󼯺�
	 */
	public List findEntityList(String hql, Object... values);

	/**
	 * ����hql��ѯ����ѯ���ݿⲢ���ز�ѯ����������ĳ־û����󼯺�,������ѯ������Ϸ���"��ѯ����",���´β�ѯʱʹ�á�
	 * ˵����ʹ�ô˷�����Ҫע�⣬���ھ������µ����ݲ�Ҫʹ�ô˷�������Ϊ�������µ����ݣ��������ݾ�û��ʲô��˼�ˣ�����Ӱ��ϵͳ���ܡ�
	 * 
	 * @param hql
	 *            ָ����ѯ���
	 * @return ���ز�ѯ��������ĳ־û����󼯺�
	 * @author duncan
	 * @createTime 2009-11-24
	 * @return ���ز�ѯ��������ĳ־û����󼯺�
	 */
	public List<T> findByCache(String hql, Object... values);

	/**
	 * ��ҳ��ѯ,ִ�й���:�Ȳ�ѯ����,�ٲ�ѯ��ҳ����
	 * 
	 * @param countHql
	 *            ����������������hql���(���Ǵ�count(*)��hql)
	 * @param hql
	 *            ��ѯ��ҳ����hql
	 * @param pageNo
	 *            �ڼ�ҳ
	 * @param pageSize
	 *            ÿҳ��ʾ��
	 * @param values
	 *            hql����ֵ,countHql��hql������Ҫһ��
	 * @author duncan
	 * @createTime 2009-11-24
	 * @return Page<T> ���ط�ҳ��ѯ��Ľ��
	 */
	public PageUtil<T> pagedQuery(String countHql, String hql, int pageNo,
			int pageSize, Object... values);

	/**
	 * ��ѯ��ҳ����
	 * 
	 * @param hql
	 *            ��ѯ��ҳ����hql
	 * @param pageNo
	 *            �ڼ�ҳ
	 * @param pageSize
	 *            ÿҳ��ʾ��
	 * @param values
	 *            hql����ֵ,countHql��hql������Ҫһ��
	 * @author duncan
	 * @createTime 2009-11-24
	 * @return Page<T> ���ط�ҳ��ѯ��Ľ��
	 */
	public PageUtil<T> pagedQuery(String hql, int pageNo, int pageSize,
			Object... values);

	/**
	 * ��ҳ��ѯ,�ѽ��������ָ��Bean��.
	 * 
	 * @param countHql
	 *            ����������������hql���(���Ǵ�count(*)��hql)
	 * @param hql
	 *            ��ѯ���hql
	 * @param pageNo
	 *            �ڼ�ҳ
	 * @param pageSize
	 *            ҳ������
	 * @param values
	 *            hql��ѯ����ֵ
	 * @param clazz
	 *            ��Ҫת������ ʹ��aliasToBeanת������
	 * @return Page<Class>
	 */
	@SuppressWarnings("unchecked")
	public PageUtil pagedQuery(String countHql, String hql,
			java.lang.Class clazz, int pageNo, int pageSize, Object... values);

	/**
	 * ��ҳ��ѯ,�ѽ��������ָ��Bean��.
	 * 
	 * @param countHql
	 *            ����������������hql���(���Ǵ�count(*)��hql)
	 * @param hql
	 *            ��ѯ���hql
	 * @param pageNo
	 *            �ڼ�ҳ
	 * @param pageSize
	 *            ҳ������
	 * @param values
	 *            hql��ѯ����ֵ
	 * @param clazz
	 *            ��Ҫת������ ʹ��aliasToBeanת������
	 * @return Page<Class>
	 */
	@SuppressWarnings("unchecked")
	public PageUtil pagedQuery(String hql, java.lang.Class clazz, int pageNo,
			int pageSize, Object... values);

	/**
	 * ���hql����ѯ����
	 * 
	 * @param hql
	 *            ָ����ѯ���
	 * @param values
	 *            hql����ֵ
	 * @return ����hql��ѯ���������
	 * @author duncan
	 * @createTime 2009-11-24
	 */
	public int getCountByHQL(String hql, Object... values);

	/**
	 * ����select �ֶ� hql ����װ�������target������, ע���ֶκ�һ��Ҫ��as ����,ͬʱ������target������һ��Ҫ����
	 * 
	 * @param hql
	 *            hql��ѯ���
	 * @param target
	 *            ָ����setĿ��Bean������
	 * @param values
	 *            hql��ѯ���Ĳ���
	 * @author duncan
	 * @createTime 2009-11-24
	 * @return List<Class> target
	 */
	@SuppressWarnings("unchecked")
	public List find(String hql, Class target, Object... values);

	/**
	 * ���ݲ�ѯ��䷵�ز�ѯ�ĵ�һ�����
	 * 
	 * @param hql
	 *            ��ѯ��䣨����HQL��ʽ��
	 * @param values
	 * @return
	 * @author lizhongren 2009-12-17 ����04:26:33
	 */
	public T findFirstByQuery(final String hql, Object... values);

	/**
	 * �������еļ�¼
	 * 
	 * @return
	 * @author lizhongren 2009-12-18 ����12:35:57
	 */
	public List<T> findAll();

	/**
	 * ��ȡHibernateTemplate
	 * 
	 * @return
	 * @author lizhongren 2009-12-20 ����03:03:59
	 */
	public HibernateTemplate getMyHibernateTemplate();

	/**
	 * JDBCģ��
	 * 
	 * @return
	 * @author lizhongren 2009-12-22 ����01:39:29
	 */
	public SimpleJdbcDao getSimpleJdbcDaoTemp();

	/**
	 * sql ��ҳ��ѯ
	 * 
	 * @param sql
	 * @param target
	 * @param pageNo
	 * @param pageSize
	 * @param values
	 * @return
	 * @author lizhongren 2009-12-23 ����02:05:09
	 */
	@SuppressWarnings("unchecked")
	public PageUtil pagedSQLQuery(String sql, Class target, int pageNo,
			int pageSize, Object... values);

	/**
	 * ��Ҫ���sql���������ͺ�Hibernate�ķ�װ ��test����,bigInteger ����
	 * 
	 * @param sql
	 * @param target
	 * @param types
	 *            Ҫӳ����ֶκ�����
	 * @param pageNo
	 * @param pageSize
	 * @param values
	 * @return
	 * @author duncan
	 * @return Page 2009-12-11����12:36:13
	 */
	@SuppressWarnings("unchecked")
	public PageUtil pagedSQLQuery(String sql, Class target,
			HashMap<String, org.hibernate.type.NullableType> types, int pageNo,
			int pageSize, Object... values);

	/**
	 * ��ѯָ����¼
	 * 
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @param values
	 * @return
	 * @author lizhongren 2010-1-8 ����12:06:54
	 */
	@SuppressWarnings("unchecked")
	public List queryLimit(String hql, int start, int limit, Object... values);

	/**
	 * ��ѯ��ǰʵ���Ƿ����
	 * 
	 * @param id
	 * @return
	 * @author lizhongren 2010-2-11 ����10:27:57
	 */
	public boolean isExistEntity(Integer id);

	public Object merge(T entityObject);
}
