package com.sweii.bean;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TimeBean extends ReportBean{
	public static Map<Integer,String> CATEGORY_MAP=new HashMap<Integer,String>();
	public static Map<Integer,String> TYPE_MAP=new HashMap<Integer,String>();
	static{
		CATEGORY_MAP.put(1, "½�ع�԰");
		CATEGORY_MAP.put(2, "ˮ��԰");
		TYPE_MAP.put(1, "ɢ��");
		TYPE_MAP.put(2, "����");
		TYPE_MAP.put(3, "��Ա");
		TYPE_MAP.put(4, "ɢ����Ʊ");
		TYPE_MAP.put(5, "��Ա��Ʊ");
		TYPE_MAP.put(6, "����Ԥ��");
	}
   
    public Map<Integer,StatBean> map=new HashMap<Integer,StatBean>();
    public List<StatBean> list=new LinkedList<StatBean>();
}
