package com.sweii.service;
import java.util.List;

import com.sweii.util.PageUtil;
import com.sweii.vo.Code;
import com.sweii.vo.Setting;
/**
 * ͨ�ýӿ�
 * @author duncan
 * @createTime 2009-11-24
 * @version 1.0
 */
public interface CommonService {
    /**
     * ����ҳ��ѯ����ʵ��
     * @param entity
     * @param orderField
     * @param order
     * @return
     * @author kfz
     * @createTime 2011-4-1
     */
    public List queryEntityList(Object entity, String orderField, String order);
    /**
     * ��ҳ��ѯ
     * @author duncan
     * @createTime 2011-1-28
     * @version 1.0
     */
    public PageUtil queryEntityPage(Object entity, String orderField, String order, int pageNo, int pageSize) throws Throwable;
    /**
     * �½�����ʵ��
     * @author duncan
     * @createTime 2011-1-28
     * @version 1.0
     */
    public void addEntity(Object entity) throws Throwable;
    /**
     * �½�����ʵ�壬һ�Զ��ϵ
     * @author duncan
     * @createTime 2011-2-14
     * @version 1.0
     */
    public void moreAddEntity(Object entity, List list) throws Throwable;
    /**
     * �޸�ʵ����Ϣ
     * @param Object entity ʵ����
     * @param fields ��Ҫ���µ�������
     * @author duncan
     * @createTime 2011-1-28
     * @version 1.0
     */
    public void editEntity(Object entity, List<String> fields) throws Throwable;
    /**
     * �޸�ʵ��
     * @author duncan
     * @createTime 2011-2-16
     * @version 1.0
     */
    public void moreEditEntity(Object entity, List list, List<String> fields, String joinFields) throws Throwable;
    /**
     * ɾ��
     * @param String entity ʵ������
     * @param String joinEntity ����ɾ����ʵ������
     * @param ids ɾ����¼
     * @author duncan
     * @createTime 2011-1-28
     * @version 1.0
     */
    public void deleteEntity(String entity, String joinEntity, List<Integer> ids) throws Throwable;
    /**
     * @param String entity ʵ������
     * @param String joinEntity ������ʵ������
     * @param ids ��¼
     * @author duncan
     * @createTime 2011-1-28
     * @version 1.0
     */
    public void confirmEntity(String entity, List<Integer> ids) throws Throwable;
    /**
     * �޸�������
     * @author duncan
     * @createTime 2011-2-22
     * @version 1.0
     */
    public void changeEntity(String entity, List<String> fields, List<String> values, List<Integer> ids) throws Throwable;
    /**
     * ͨ��ID��ѯʵ��
     * @author duncan
     * @createTime 2011-2-16
     * @version 1.0
     */
    public Object queryEntityById(String entity, Integer id);
    /**
     * ͨ��ID��ѯ����ʵ���б�
     * @author duncan
     * @createTime 2011-2-16
     * @version 1.0
     */
    public List queryJoinEntityList(String entity, String joinEntity, Integer id);
    /**
     * ���¸����ڴ������ֵ�
     * @author duncan
     * @createTime 2009-11-24
     * @version 1.0
     * @return void
     */
    public void reloadCode();
    /**
     * ͨ�������ֵ丸�����CodeType��ѯ���ֵ��б�
     * @param String codeType �����ֵ丸�����
     * @author duncan
     * @createTime 2009-11-25
     * @version 1.0
     * @return List<Code> �������б�,���ڶ�̬��λ�˵�ѡ������
     */
    public List<Code> queryCodeList(String codeType);
    /**
     * ͨ�������ֵ丸�����CodeType��ѯ���ֵ��б�
     * @param String codeType �����ֵ丸�����
     * @author duncan
     * @createTime 2009-11-25
     * @version 1.0
     * @return List<Code> �������б�,���ڶ�̬��λ�˵�ѡ������
     */
    public List<Code> queryCodeList(String codeType, Integer type);
    /**
     * ͨ�����ͺ�ֵ,��ѯ�����ֵ�����
     * @author duncan
     * @createTime 2010-1-1
     * @version 1.0
     * @return String
     */
    public String queryCodeName(String codeType, String value);
    /**
     * ��ѯ�����б�
     * @author duncan
     * @createTime 2010-1-1
     * @version 1.0
     * @return PageUtil<Code>
     */
    public PageUtil<Code> queryCodePage(Integer parentId, String searchKey, int pageNo, int pageSize);
    /**
     *��Ӵ���
     * @author duncan
     * @createTime 2010-1-1
     * @version 1.0
     * @return void
     */
    public void doAddCode(Code vo);
    /**
     * �޸Ĵ���
     * @author duncan
     * @createTime 2010-1-1
     * @version 1.0
     * @return void
     */
    public void doEditCode(Code vo);
    /**
     * ɾ������,����Ǹ���,��ɾ����������
     * @author duncan
     * @createTime 2010-1-1
     * @version 1.0
     * @return void
     */
    public void doDeleteCode(Integer[] ids);
    /**
     * ɾ��
     * @author duncan
     * @createTime 2010-6-2
     * @version 1.0
     * @return void
     */
    public void deleteHoliday(Integer id);
    /**
     * 
     * @author duncan
     * @createTime 2010-8-22
     * @version 1.0
     */
    public void editEntity(Integer id, String entity, String field, String value, String javaType, String type, String format)throws Throwable;
    /**
     * 
     * @author duncan
     * @createTime 2011-2-16
     * @version 1.0
     */
    public String queryBranchName(Integer branchId);
    /**
     * ��ѯĳ���Ͷ�ӦĳЩֵ���б�,values �ö��ŷָ�
     * @param codeType
     * @param values
     * @return
     * @author kfz
     * @createTime 2010-12-28
     */
    public List<Code> queryCodeListByValue(String codeType, String values);
    /**
     * ȷ��ʵ�壬����status״̬Ϊ1
     * @param obj
     * @param fs
     * @author kfz
     * @createTime 2011-2-18
     */
    public void confirmEntity(Object obj, List<Integer> ids);
    public Setting getSetting();
    /**
     * �������ݿ�
     * @author duncan
     * @createTime 2011-11-8
     * @version 1.0
     */
    public boolean backup(Integer type) throws Exception ;
}
