package com.sweii.util;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * ��ҳ��
 * @author duncan
 * 2009-2009-10-10-����09:03:21
 * @param <T>
 */
public class PageUtil<T> implements Serializable {
	private static final long serialVersionUID = -5624189033006412710L;
	public static long DEFAULT_PAGE_SIZE = 18;
	private long pageSize = DEFAULT_PAGE_SIZE; // ÿҳ�ļ�¼��
	private long start; // ��ǰҳ��һ��������List�е�λ��,��0��ʼ
	private List<T> data = Collections.emptyList(); // ��ǰҳ�д�ŵļ�¼
	private long totalCount = 0; // �ܼ�¼��
	private int size;
	private long length;
	private double weight;

	public int getSize() {
	    return size;
	}

	public void setSize(int size) {
	    this.size = size;
	}

	public long getLength() {
	    return length;
	}

	public void setLength(long length) {
	    this.length = length;
	}

	public double getWeight() {
	    return weight;
	}

	public void setWeight(double weight) {
	    this.weight = weight;
	}

	/**
	* ���췽����ֻ�����ҳ.
	*/
	public PageUtil() {
	   this(0l, 0l, DEFAULT_PAGE_SIZE, Collections.<T> emptyList());
	}

	/**
	* Ĭ�Ϲ��췽��.
	* 
	* @param start
	*            ��ҳ���������ݿ��е���ʼλ��
	* @param totalSize
	*            ���ݿ����ܼ�¼����
	* @param pageSize
	*            ��ҳ����
	* @param data
	*            ��ҳ����������
	*/
	public PageUtil(long start, long totalSize, long pageSize, List<T> data) {
	   this.pageSize = pageSize;
	   this.start = start;
	   this.totalCount = totalSize;
	   this.data = data;
	}

	public long getStart() {
		return start;
	}

	/**
	* ȡ�ܼ�¼��.
	*/
	public long getTotalCount() {
	   return this.totalCount;
	}

	/**
	* ȡ��ҳ��.
	*/
	public long getTotalPageCount() {
	   if (totalCount % pageSize == 0)
	    return totalCount / pageSize;
	   else
	    return totalCount / pageSize + 1;
	}

	/**
	* ȡÿҳ��������.
	*/
	public Long getPageSize() {
	   return pageSize;
	}

	/**
	* ȡ��ǰҳ�еļ�¼.
	*/
	public List<T> getResult() {
	   return data;
	}
	/**
	* ȡ��ҳ��ǰҳ��,ҳ���1��ʼ.
	*/
	public long getCurrentPageNo() {
	   return start / pageSize + 1;
	}

	/**
	* ��ҳ�Ƿ�����һҳ.
	*/
	public boolean hasNextPage() {
	   return this.getCurrentPageNo() < this.getTotalPageCount();
	}

	/**
	* ��ҳ�Ƿ�����һҳ.
	*/
	public boolean hasPreviousPage() {
	   return this.getCurrentPageNo() > 1;
	}

	/**
	* ��ȡ��һҳ��һ�����������ݼ���λ�ã�ÿҳ����ʹ��Ĭ��ֵ.
	* 
	*/
	protected static long getStartOfPage(long pageNo) {
	   return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
	}

	/**
	* ��ȡ��һҳ��һ�����������ݼ���λ��.
	* 
	* @param pageNo
	*            ��1��ʼ��ҳ��
	* @param pageSize
	*            ÿҳ��¼����
	* @return ��ҳ��һ������
	*/
	public static long getStartOfPage(long pageNo, long pageSize) {
	   return (pageNo - 1) * pageSize;
	}

	public void setPageSize(long pageSize) {
	    this.pageSize = pageSize;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
	}


