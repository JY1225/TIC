package com.sweii.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.support.KeyHolder;
/**
 * 
 * $Header:
 * $Revision:
 * @Author:�ڽ�ƽ
 * @version:1.0
 * <BR/>����ʱ�䣺2006-9-4 20:22:57<BR/>
 * 
 * ����˵����<BR/>
 * <BR/>
 * ʵ�� @see org.springframework.jdbc.support.KeyHolder ,�ܹ����SQL������������<BR/>
 */
public final class JdbcKeyHolder implements KeyHolder{
	List keyList = new ArrayList();
	public Number getKey() throws InvalidDataAccessApiUsageException {
		return (Number)(((Map)keyList.get(0)).get("GENERATED_KEY"));
	}

	public Map getKeys() throws InvalidDataAccessApiUsageException {
		return (Map)keyList.get(0);
	}

	public List getKeyList() {
		return keyList;
	}
}