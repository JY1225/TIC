package com.sweii.bean;
/**
 *ͨ�ò�ѯ����
 * 
 * @author kfz
 *@date 2010-12-23
 * 
 **/
public class BaseCondition {
    private int pageNo;// �ڼ�ҳ
    private int pageSize;// ÿҳ��
    private int id;
    public int getPageNo() {
	return pageNo;
    }
    public void setPageNo(int pageNo) {
	this.pageNo = pageNo;
    }
    public int getPageSize() {
	return pageSize;
    }
    public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
    }
    public int getId() {
	return id;
    }
    public void setId(int id) {
	this.id = id;
    }
}
