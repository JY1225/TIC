package com.sweii.dao;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.sweii.framework.helper.NumbericHelper;
import com.sweii.util.PageUtil;
/**
 * BaseDao�ӿ���
 * @author duncan
 * @createTime 2009-11-24
 */
public class BaseDao<T, PK extends Serializable> extends HibernateDaoSupport implements IBaseDao<T, PK> {
    protected Class<T> entityClass;// DAO�������Entity����.
    /**
     * ��spring�ṩ���캯��ע��
     */
    public BaseDao(Class<T> type) {
	this.entityClass = type;
    }
    @SuppressWarnings("unchecked")
    public BaseDao() {
	Type genType = getClass().getGenericSuperclass();
	if (genType instanceof ParameterizedType) {
	    this.entityClass = (Class<T>) ((ParameterizedType) genType).getActualTypeArguments()[0];
	    //System.out.println(this.entityClass.getName());
	}
    }
    /**
     * ��յ�ǰ�߳�Session�Ļ��������г־û����󣬴Ӷ���ʱ�ͷ���ռ�õ��ڴ�ռ�
     * @author duncan
     * @createTime 2009-11-24
     */
    public void clear() {
	this.getHibernateTemplate().clear();
    }
    public Session openSession() {
	return getHibernateTemplate().getSessionFactory().openSession();
    }
    /**
     * �����ύ��ǰ�߳�Session�Ĳ���,�ύ�󲻿�rollback,�����Ҫrollback������
     * ����commit֮ǰ��дlog������Ҫʱ�ɸ���log�������ݻָ���
     * @author duncan
     * @createTime 2009-11-24
     */
    public void commit() {
	SessionFactoryUtils.getSession(this.getHibernateTemplate().getSessionFactory(), true).beginTransaction().commit();
    }
    /**
     *��ǰ�߳�Session�Ĳ������������ݿ�ͬ������,��δ�ύ,��rollback
     *Ŀ��:�ɵ���Щ��������Session�����еĳ־û��������,ʹ���ڴ����,�������ϵͳ�����½�,
     *����flush()�󣬿ɵ���evict()���־û������Session������ɾ�����Ӷ���ʱ�ͷ���ռ�õ��ڴ�ռ䡣
     *���ش������ݽ��и��²���ʱ,��ʹ�ô˷����Ż�ϵͳ
     * @author duncan
     * @lastEdit 2007-05-26
     */
    public void flush() {
	this.getHibernateTemplate().flush();
    }
    /**
     * ����ִ�в�ѯ�ӳټ��ض���,֧�ּ��ϲ���
     * @param object  �������ض���
     * @author duncan
     * @createTime 2009-11-24
     */
    public void initialize(T entityObject) {
	this.getHibernateTemplate().initialize(entityObject);
    }
    /**
     * �ӵ�ǰ�߳�Session�������������ָ���ĳ־û�����
     * @param object �־û������ӳټ��ض���
     * @author duncan
     * @createTime 2009-11-24
     */
    public void evict(T entityObject) {
	this.getHibernateTemplate().evict(entityObject);
    }
    /**
     * �ӵ�ǰ�߳�Session�������������ָ���ĳ־û�����
     * @param entities �־û������ӳټ��ض���
     * @author duncan
     * @createTime 2009-11-24
     */
    public void evict(Collection<T> entities) {
	for (T object : entities) {
	    this.getHibernateTemplate().evict(object);
	}
    }
    /**
     * �ж�Session�������Ƿ���ڲ���ָ���ĳ־û�����
     *��;���������ж��Ƿ��Ѽ��أ����Ƿ�ʹ�ù�initialize()���ع��ö���
     * @param object �־û�����
     * @author duncan
     * @createTime 2009-11-24
     * @return boolean true��ʾ����,false��ʾ������
     */
    public boolean contain(T entityObject) {
	return this.getHibernateTemplate().contains(entityObject);
    }
    /**
     * ͨ��ID���س־û�����ִ�й��̣�
     * ��ID��ΪKey��ѯ��ǰ�߳�Session����(һ�����棩�Ƿ�����,�����session������ֱ�ӷ��ظó־û�����,
     * �����ѯ���������е�"�໺��"�Ƿ����У�������Ӷ��������з��ظó־û����󣬲���������һ��select * from table where id=?
     * ��sql�����ظó־û�����������ݿ�û�ж�ӦID�����ݼ�¼���򷵻�null��������ڼ�¼����������ļ���Ϣ���س־û����󣬲����ö������session
     * ���棬���������"�໺��"�������ö������"�໺��"�У���iterator()����ʹ�ú�"��ѯ����"ʹ�á�
     * @param id ��ID
     * @author duncan
     * @createTime 2009-11-24
     * @return T ���س־û�����,����Ϊnullֵ
     */
    @SuppressWarnings("unchecked")
    public T getById(PK id) {
	return (T) this.getHibernateTemplate().get(this.entityClass, id);
    }
    /**
     * ͨ��ID���س־û�����ִ�й�����getByPk()���ƣ�Ψһ��ͬ�ľ��ǵ����ݿ�û�ж�ӦID�ļ�¼ʱ��getByPk()����nullֵ����
     * loadByPk()�����׳��쳣��
     * @param id ��ID
     * @author duncan
     * @createTime 2009-11-24
     * @return object ���س־û�����,������Ϊnullֵ
     */
    @SuppressWarnings("unchecked")
    public T load(PK id) {
	return (T) this.getHibernateTemplate().load(this.entityClass, id);
    }
    /**
     * ��ȡʵ�����͵�ȫ�������б�
     * @author duncan
     * @createTime 2009-11-24
     * @version 1.0
     * @return List<T>  
     */
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
	return (List<T>) (this.getHibernateTemplate().loadAll(this.entityClass));
    }
    /**
     * �����ݿ�־û����󣨼������ݿ����һ����¼�� 
     * ����������ڱ�session�г־û���,�����κ��¡�<br>
     * �����һ��seesionӵ����ͬ�ĳ־û���ʶ,�׳��쳣��<br>
     * ���û�г־û���ʶ����,����save()��<br>
     * ����־û���ʶ������һ���µ�ʵ��������,����save()��<br>
     * ����Ǹ����汾��Ϣ��(version��timestamp)�Ұ汾���Ա���Ϊ�µ�ʵ���������save()��<br>
     * �������update()���¹����йܶ���
     * @param object �������
     * @author duncan
     * @createTime 2009-11-24
     * 
     */
    public void save(T entityObject) {
	this.getHibernateTemplate().save(entityObject);
    }
    public void saveEntity(Object entity) {
	this.getHibernateTemplate().save(entity);
    }
    /**
     * ��������ʵ��,ִ��saveOrUpdate����
     * @param coll  ����
     * @author duncan
     * @createTime 2009-11-24
     */
    public void saveAll(Collection<T> coll) {
	this.getHibernateTemplate().saveOrUpdateAll(coll);
    }
    /**
     * �����ݿ�־û��������¼�¼,��������:
     * ���ж�entityObject����ID�Ƿ�Ϊ�գ�Ϊ����ִ����save()������ͬ�Ĳ������������ݿ����һ����¼,�����µ�ID�������ID��Ϊ�գ�
     * ����һ��select * from table where id=?��SQL�����ظü�¼�����������ָ��ID�ļ�¼����ִ����save()������ͬ�Ĳ������������ݿ����һ����¼,�����µ�ID����
     * �����¼���ڣ�����ش����ݿ��м��س־û����������object��ֵ�Ա�,��ֵ��ͬ���������ɸ���SQL���,�����и��²�����
     * @param object �������
     * @author duncan
     * @createTime 2009-11-24
     * 
     */
    public void saveOrUpdate(T entityObject) {
	this.getHibernateTemplate().saveOrUpdate(entityObject);
    }
    /**
     * �����������ݼ�¼,hql�����?��ʽ���ܲ���
     * @param hql hibernate�����������
     * @param values ����ֵ
     * @author duncan
     * @createTime 2009-11-24
     * 
     */
    public void update(String hql, Object... values) {
	this.getHibernateTemplate().bulkUpdate(hql, values);
    }
    /**
     * ����ʵ��
     * @param entityObject
     * @author lizhongren
     * 2009-12-17 ����04:17:55
     */
    public void update(T entityObject) {
	this.getHibernateTemplate().update(entityObject);
    }
    /**
     * ����ʵ��
     * @param entityObject
     * @author lizhongren
     * 2009-12-17 ����04:17:55
     */
    public void updateEntity(Object entityObject) {
	this.getHibernateTemplate().update(entityObject);
    }
    /**
     * ͨ��IDɾ������
     * @param PK id��
     * @author duncan
     * @createTime 2009-11-24
     * 
     */
    public void deleteById(PK id) {
	T vo = this.getById(id);
	if (vo != null) {
	    this.delete(vo);
	}
    }
    /**
     * ɾ������
     * @param T �־ö���
     * @author duncan
     * @createTime 2009-11-24
     * 
     */
    public void delete(T entityObject) {
	this.getHibernateTemplate().delete(entityObject);
    }
    /**
     * ɾ�����ʵ��
     * @param entities
     * @author lizhongren
     * 2009-12-20 ����11:10:42
     */
    public void deleteAll(Collection<T> entities) {
	this.getHibernateTemplate().deleteAll(entities);
    }
    /**
     * ����ɾ�����ݼ�¼,hql�����?��ʽ���ܲ���
     * @param hql hibernate����ɾ�����,��delete Entity,��ɾ�����м�¼
     * @param values ����ֵ
     * @author duncan
     * @createTime 2009-11-24
     * 
     */
    public void delete(String hql, Object... values) {
	this.getHibernateTemplate().bulkUpdate(hql, values);
    }
    /**
     * ����hql��ѯ����ѯ���ݿⲢ���ز�ѯ����������ĵ����־û����󣨼��Ƿ��ز�ѯ����ĵ�һ����¼��,ֱ��ʹ��HibernateTemplate��find����.
     * �����Ĳ���SQL��䣺mysqlΪlimit 0,1 
     * @param hql ָ����ѯ���
     * @author duncan
     * @createTime 2009-11-24
     * @return  T ֻ���ز�ѯ�����һ����¼,�����ڼ�¼����null
     */
    @SuppressWarnings("unchecked")
    public T findEntiry(String hql, Object... values) {
	List<T> results = this.getHibernateTemplate().find(hql, values);
	if (results.size() > 0) {
	    return results.get(0);
	} else {
	    return null;
	}
    }
    /**
     * ����hql��ѯ����ѯ���ݿⲢ���ز�ѯ����������ĵ����־û����󣨼��Ƿ��ز�ѯ����ĵ�һ����¼��,ֱ��ʹ��HibernateTemplate��find����.
     * �����Ĳ���SQL��䣺mysqlΪlimit 0,1 
     * @param hql ָ����ѯ���
     * @author duncan
     * @createTime 2009-11-24
     * @return  T ֻ���ز�ѯ�����һ����¼,�����ڼ�¼����null
     */
    @SuppressWarnings("unchecked")
    public Object findObjectEntiry(String hql, Object... values) {
	List results = this.getHibernateTemplate().find(hql, values);
	if (results.size() > 0) {
	    return results.get(0);
	} else {
	    return null;
	}
    }
    /**
     * ����hql��ѯ����ѯ���ݿⲢ���ز�ѯ����������ĳ־û����󼯺�,ֱ��ʹ��HibernateTemplate��find����.
     * @param hql ָ����ѯ���
     * @param params ����ֵ
     * @author duncan
     * @createTime 2009-11-24
     * @return ���ز�ѯ��������ĳ־û����󼯺�
     */
    @SuppressWarnings("unchecked")
    public List<T> find(String hql, Object... values) {
	return this.getHibernateTemplate().find(hql, values);
    }
    /**
     * ����hql��ѯ����ѯ���ݿⲢ���ز�ѯ����������ĳ־û����󼯺�,ֱ��ʹ��HibernateTemplate��find����.
     * @param hql ָ����ѯ���
     * @param params ����ֵ
     * @author duncan
     * @createTime 2009-11-24
     * @return ���ز�ѯ��������ĳ־û����󼯺�
     */
    @SuppressWarnings("unchecked")
    public List findEntityList(String hql, Object... values) {
	return this.getHibernateTemplate().find(hql, values);
    }
    /**
     * ����hql��ѯ����ѯ���ݿⲢ���ز�ѯ����������ĳ־û����󼯺�,������ѯ������Ϸ���"��ѯ����",���´β�ѯʱʹ�á�
     * ˵����ʹ�ô˷�����Ҫע�⣬���ھ������µ����ݲ�Ҫʹ�ô˷�������Ϊ�������µ����ݣ��������ݾ�û��ʲô��˼�ˣ�����Ӱ��ϵͳ���ܡ�
     * @param hql ָ����ѯ���
     * @return ���ز�ѯ��������ĳ־û����󼯺�
     * @author duncan
     * @createTime 2009-11-24
     * @return ���ز�ѯ��������ĳ־û����󼯺�
     */
    @SuppressWarnings("unchecked")
    public List<T> findByCache(String hql, Object... values) {
	this.getHibernateTemplate().setCacheQueries(true);
	return this.getHibernateTemplate().find(hql, values);
    }
    /**
     * ִ�б��ز�ѯ���SQLQuery����<br>
     * ���Ե���addEntity(*.class).list();��ö�Ӧʵ��list����<br>
     * addEntity.add(*.class).addJoin(*.class).list();���һ�Զ�������<br>
     * �����÷���google
     * 
     * @param sql
     * @return
     */
    public SQLQuery nativeSqlQuery(String sql) {
	return this.getSession(false).createSQLQuery(sql);
    }
    /**
     * ��ҳ��ѯ,ִ�й���:�Ȳ�ѯ����,�ٲ�ѯ��ҳ����
     * @param  countHql ����������������hql���(���Ǵ�count(*)��hql)
     * @param  hql   ��ѯ��ҳ����hql
     * @param  pageNo  �ڼ�ҳ
     * @param  pageSize  ÿҳ��ʾ��
     * @param  values  hql����ֵ,countHql��hql������Ҫһ��
     * @author duncan
     * @createTime 2009-11-24
     * @return Page<T>  ���ط�ҳ��ѯ��Ľ��
     */
    @SuppressWarnings("unchecked")
    public PageUtil<T> pagedQuery(String countHql, String hql, int pageNo, int pageSize, Object... values) {
	return this.pagedQuery(countHql, hql, null, pageNo, pageSize, values);
    }
    /**
     * ��ѯ��ҳ����
     * @param  hql   ��ѯ��ҳ����hql
     * @param  pageNo  �ڼ�ҳ
     * @param  pageSize  ÿҳ��ʾ��
     * @param  values  hql����ֵ,countHql��hql������Ҫһ��
     * @author duncan
     * @createTime 2009-11-24
     * @return Page<T>  ���ط�ҳ��ѯ��Ľ��
     */
    @SuppressWarnings("unchecked")
    public PageUtil<T> pagedQuery(String hql, int pageNo, int pageSize, Object... values) {
	return this.pagedQuery(null, hql, null, pageNo, pageSize, values);
    }
    /**
     * ��ѯ��ҳ����
     * @param  hql   ��ѯ��ҳ����hql
     * @param  pageNo  �ڼ�ҳ
     * @param  pageSize  ÿҳ��ʾ��
     * @param  values  hql����ֵ,countHql��hql������Ҫһ��
     * @author duncan
     * @createTime 2009-11-24
     * @return Page<T>  ���ط�ҳ��ѯ��Ľ��
     */
    @SuppressWarnings("unchecked")
    public PageUtil<T> pagedQuery(String hql, java.lang.Class clazz, int pageNo, int pageSize, Object... values) {
	return this.pagedQuery(null, hql, clazz, pageNo, pageSize, values);
    }
    /**
     * ��ҳ��ѯ,�ѽ��������ָ��Bean��.
     * @param countHql ����������������hql���(���Ǵ�count(*)��hql)
     * @param hql  ��ѯ���hql
     * @param pageNo  �ڼ�ҳ
     * @param pageSize ҳ������
     * @param values  hql��ѯ����ֵ
     * @param clazz ��Ҫת������ ʹ��aliasToBeanת������
     * @return Page<Class>
     */
    @SuppressWarnings("unchecked")
    public PageUtil pagedQuery(String countHql, String hql, java.lang.Class clazz, int pageNo, int pageSize, Object... values) {
	// Count��ѯ
	long totalCount = 0;
	if (countHql != null) {
	    List<T> countlist = this.getHibernateTemplate().find(countHql, values);
	    totalCount = (Long) countlist.get(0);
	} else {
	    String sql = getOriginalSql(hql);
	    totalCount = this.simpleJdbcDao.getSimpleJdbcTemplate().queryForInt(sql, values);
	}
	if (totalCount < 1) return new PageUtil<T>();
	// ��ǰҳ�Ŀ�ʼ��������
	long startIndex = PageUtil.getStartOfPage(pageNo, pageSize);
	Query query = this.createQuery(hql, values);
	query.setFirstResult((int) startIndex).setMaxResults(pageSize);
	if (clazz != null) {
	    query.setResultTransformer(Transformers.aliasToBean(clazz));
	}
	List list = query.list();
	return new PageUtil(startIndex, totalCount, pageSize, list);
    }
    /**
     * sql ��ҳ��ѯ
     * @param sql
     * @param target
     * @param pageNo
     * @param pageSize
     * @param values
     * @return
     * @author lizhongren
     * 2009-12-23 ����02:05:09
     */
    @SuppressWarnings("unchecked")
    public PageUtil pagedSQLQuery(String sql, Class target, int pageNo, int pageSize, Object... values) {
	return this.pagedSQLQuery(sql, target, null, pageNo, pageSize, values);
    }
    @SuppressWarnings("unchecked")
    public List queryLimit(String hql, int start, int limit, Object... values) {
	Query query = this.createQuery(hql, values);
	query.setFirstResult((int) start).setMaxResults(limit);
	return query.list();
    }
    /**
     * ��Ҫ���sql���������ͺ�Hibernate�ķ�װ
     * ��test����,bigInteger ����
     * @param sql
     * @param target
     * @param types Ҫӳ����ֶκ�����
     * @param pageNo
     * @param pageSize
     * @param values
     * @return
     * @author duncan
     * @return Page
     * 2009-12-11����12:36:13
     */
    @SuppressWarnings("unchecked")
    public PageUtil pagedSQLQuery(String sql, Class target, HashMap<String, org.hibernate.type.NullableType> types, int pageNo, int pageSize, Object... values) {
	String countsql = sql;
	//�ݲ����� order by wherr ��
	/*int sqlOrderBy = sql.toLowerCase().indexOf("order by");
	 int sqlWhere=sql.toLowerCase().lastIndexOf("where");
	 
	 if (sqlOrderBy > 0 && sqlOrderBy>sqlWhere) {
	 countsql = sql.substring(0, sqlOrderBy);
	 }*/
	// Count��ѯ
	int totalCount = this.simpleJdbcDao.getSimpleJdbcTemplate().queryForInt("select count(*) from (" + countsql + ") tmp_count_t", values);
	if (totalCount < 1) return new PageUtil();
	// ��ǰҳ�Ŀ�ʼ��������
	long startIndex = PageUtil.getStartOfPage(pageNo, pageSize);
	SQLQuery q = this.nativeSqlQuery(sql);
	for (int i = 0; i < values.length; i++) {
	    if (values[i] instanceof Integer) {
		q.setInteger(i, ((Integer) values[i]).intValue());
	    } else if (values[i] instanceof String) {
		q.setString(i, (String) values[i]);
	    } else if (values[i] instanceof Date) {
		q.setDate(i, (Date) values[i]);
	    } else if (values[i] instanceof Timestamp) {
		q.setTimestamp(i, (Timestamp) values[i]);
	    } else if (values[i] instanceof Double) {
		q.setDouble(i, ((Double) values[i]).doubleValue());
	    } else if (values[i] instanceof Float) {
		q.setFloat(i, ((Float) values[i]).floatValue());
	    } else if (values[i] instanceof Long) {
		q.setLong(i, ((Long) values[i]).longValue());
	    } else {
		throw new HibernateException("can not support the type:" + values[i].getClass().getName());
	    }
	}
	if (types != null) {
	    for (Iterator<String> it = types.keySet().iterator(); it.hasNext();) {
		String filed = it.next();
		if (types.get(filed) == null) {
		    q.addScalar(filed);
		} else {
		    q.addScalar(filed, types.get(filed));
		}
	    }
	}
	List list = q.setFirstResult((int) startIndex).setMaxResults(pageSize).setResultTransformer(Transformers.aliasToBean(target)).list();
	return new PageUtil(startIndex, totalCount, pageSize, list);
    }
    /**
     * ��ѯ��¼����
     * @param sql
     * @param values
     * @return
     * @author lizhongren
     * 2009-12-23 ����02:41:59
     */
    @SuppressWarnings("unchecked")
    public int getCountBySQL(String sql, Object... values) {
	SQLQuery q = this.nativeSqlQuery(sql);
	for (int i = 0; i < values.length; i++) {
	    if (values[i] instanceof Integer) {
		q.setInteger(i, ((Integer) values[i]).intValue());
	    } else if (values[i] instanceof String) {
		q.setString(i, (String) values[i]);
	    } else if (values[i] instanceof Date) {
		q.setDate(i, (Date) values[i]);
	    } else if (values[i] instanceof Timestamp) {
		q.setTimestamp(i, (Timestamp) values[i]);
	    } else if (values[i] instanceof Double) {
		q.setDouble(i, ((Double) values[i]).doubleValue());
	    } else if (values[i] instanceof Float) {
		q.setFloat(i, ((Float) values[i]).floatValue());
	    } else if (values[i] instanceof Long) {
		q.setLong(i, ((Long) values[i]).longValue());
	    } else {
		throw new HibernateException("can not support the type:" + values[i].getClass().getName());
	    }
	}
	List list = q.list();
	if (list != null && list.size() > 0) {
	    return NumbericHelper.getIntValue(list.get(0), 0);
	}
	return 0;
    }
    /**
     * ���hql����ѯ����
     * @param hql   ָ����ѯ���
     * @param  values  hql����ֵ
     * @return ����hql��ѯ���������
     * @author duncan
     * @createTime 2009-11-24
     */
    @SuppressWarnings("unchecked")
    public int getCountByHQL(String hql, Object... values) {
	hql = hql.replace("fetch", ""); //�滻�� left join fetch 
	int sqlFrom = hql.toLowerCase().indexOf("from");
	int sqlOrderBy = hql.toLowerCase().indexOf("order by");
	StringBuffer countStr = new StringBuffer("select count(*) ");
	if (sqlOrderBy > 0) {
	    countStr.append(hql.substring(sqlFrom, sqlOrderBy));
	} else {
	    countStr.append(hql.substring(sqlFrom));
	}
	Query query = SessionFactoryUtils.getSession(this.getHibernateTemplate().getSessionFactory(), false).createQuery(countStr.toString());
	for (int i = 0; i < values.length; i++) {
	    query.setParameter(i, values[i]);
	}
	List result = query.list();
	if (result != null && result.size() > 0) {
	    return ((Long) result.get(0)).intValue();
	} else {
	    return 0;
	}
    }
    /**
     * Hibernate��HQL���ת�������ݿ��sql���
     * @param originalHql
     * @return
     * @throws Exception
     * @author duncan
     * @return String
     * 2009-11-4����06:00:19
     */
    protected String getOriginalSql(String originalHql) {
	try {
	    int sqlOrderBy = originalHql.toLowerCase().indexOf("order by");
	    if (sqlOrderBy > 0) {
		originalHql = originalHql.substring(0, sqlOrderBy);
	    }
	    /*int start=originalHql.toLowerCase().indexOf("select");
	     int end=originalHql.toLowerCase().indexOf("from");
	     StringBuffer buf=new StringBuffer(originalHql);
	     if(start!=-1){
	     buf.delete(start, end).toString();
	     }*/
	    //originalHql="select count(*) "+buf.toString();
	    org.hibernate.hql.ast.QueryTranslatorImpl queryTranslator = new org.hibernate.hql.ast.QueryTranslatorImpl(originalHql, originalHql, java.util.Collections.EMPTY_MAP, (org.hibernate.engine.SessionFactoryImplementor) super.getSessionFactory());
	    queryTranslator.compile(java.util.Collections.EMPTY_MAP, false);
	    return "select count(*) from (" + queryTranslator.getSQLString() + ") tmp_count_t";
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
    /**
     * ����select �ֶ� hql ����װ�������target������, ע���ֶκ�һ��Ҫ��as ����,ͬʱ������target������һ��Ҫ����
     * @param hql  hql��ѯ���
     * @param target  ָ����setĿ��Bean������
     * @param values  hql��ѯ���Ĳ���
     * @author duncan
     * @createTime 2009-11-24
     * @return List<Class>  target
     */
    @SuppressWarnings("unchecked")
    public List find(String hql, Class target, Object... values) {
	return this.createQuery(hql, values).setResultTransformer(Transformers.aliasToBean(target)).list();
    }
    /**
     * ����Query����.<br>
     * ������Ҫfirst,max,fetchsize,cache,cacheRegion��������õĺ���,�����ڷ���Query����������.
     * 
     * @param hql
     * @param values
     * @return
     */
    protected Query createQuery(String hql, Object... values) {
	// �����false��ʾ������session��֤,��ǰ������springͬһ������Ĺ�����
	Query query = this.getSession(false).createQuery(hql);
	if (values != null) {
	    for (int i = 0; i < values.length; i++) {
		query.setParameter(i, values[i]);
	    }
	}
	return query;
    }
    /**
     * ���ݲ�ѯ��䷵�ز�ѯ�ĵ�һ�����
     * @param hql  ��ѯ��䣨����HQL��ʽ��
     * @param values
     * @return
     * @author lizhongren
     * 2009-12-17 ����04:26:33
     */
    @SuppressWarnings("unchecked")
    public T findFirstByQuery(final String hql, Object... values) {
	List<T> list = getHibernateTemplate().find(hql, values);
	return (list == null || list.size() <= 0) ? null : list.get(0);
    }
    /**
     * �������еļ�¼
     * @return
     * @author lizhongren
     * 2009-12-18 ����12:35:57
     */
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
	return getHibernateTemplate().find(" select entity from " + entityClass.getName() + " entity ");
    }
    @SuppressWarnings("unchecked")
    public List<T> findAll(String sql, Class bean) {
	return this.getSessionFactory().getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(bean)).list();
    }
    /**
     * ��ѯ��ǰʵ���Ƿ����
     * @param id
     * @return
     * @author lizhongren
     * 2010-2-11 ����10:27:57
     */
    public boolean isExistEntity(Integer id) {
	int count = this.getCountByHQL("select entity from " + entityClass.getName() + " entity where entity.id=?", id);
	if (count == 0) return false;
	return true;
    }
    /**
     * ��ȡHibernateTemplate
     * @return
     * @author lizhongren
     * 2009-12-20 ����03:03:59
     */
    public HibernateTemplate getMyHibernateTemplate() {
	return this.getHibernateTemplate();
    }
    /**
     * JDBCģ��
     * @return
     * @author lizhongren
     * 2009-12-22 ����01:39:29
     */
    public SimpleJdbcDao getSimpleJdbcDaoTemp() {
	return this.simpleJdbcDao;
    }
    private SimpleJdbcDao simpleJdbcDao;
    public SimpleJdbcDao getSimpleJdbcDao() {
	return simpleJdbcDao;
    }
    public void setSimpleJdbcDao(SimpleJdbcDao simpleJdbcDao) {
	this.simpleJdbcDao = simpleJdbcDao;
    }
    public Object merge(T entityObject) {
	return getHibernateTemplate().merge(entityObject);
    }
}
