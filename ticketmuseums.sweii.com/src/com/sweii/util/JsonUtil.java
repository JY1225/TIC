package com.sweii.util;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.sweii.framework.annotation.Sweii;
/**
 * ��java����ת��Ϊjson
 * @author duncan
 * @createTime 2007-10-23
 * @version 1.0
 */
public class JsonUtil {
    public static String format = "yyyy-MM-dd";
    /**
     * ��java����ת��Ϊjson
     * @param obj  ��Ҫת����Json��java����
     * @author duncan
     * @lastEdit 2007-10-23
     * @return Json�ַ���
     */
    public static String changeToJson(Object obj) {
	String json = changeToJson(obj, "");
	json = json.replaceAll("\n", "###");
	json = json.replaceAll("\r", "@@@");
	return json;
    }
    /**
     * ��java����ת��Ϊjson
     * @param obj  ��Ҫת����Json��java����
     * @param replaceType  
     * @author duncan
     * @lastEdit 2007-10-23
     * @return Json�ַ���
     */
    public static String changeToJson(Object obj, String replaceType) {
	String jsonstr = null;
	if (obj instanceof Collection) {//��ǰ����Ϊ���϶���
	    jsonstr = changeCollection((Collection) obj, replaceType);
	} else if (obj instanceof String || obj instanceof Byte || obj instanceof Character || obj instanceof Double || obj instanceof Float) {
	    if (replaceType.equals("html")) {
		jsonstr = "'" + replace(obj.toString()) + "'";
	    } else if (replaceType.equals("js")) {
		jsonstr = "'" + replaceToJavascript(obj.toString()) + "'";
	    } else {
		if (obj.toString().endsWith(".0")) {
		    jsonstr = "'" + obj.toString().substring(0, obj.toString().length() - 2) + "'";
		} else {
		    jsonstr = "'" + obj.toString() + "'";
		}
	    }
	} else if (obj instanceof Integer || obj instanceof Boolean || obj instanceof Long) {//�������Ͷ���ֱ�ӷ���
	    jsonstr = obj.toString();
	} else if (obj instanceof Date) {//���ڶ���ָ����ʽ����
	    SimpleDateFormat dateformat = new SimpleDateFormat(format);
	    jsonstr = "'" + dateformat.format(obj) + "'";
	} else if (obj instanceof String[]) {//�ַ�������,ֱ�ӹ��췵��
	    String[] strArray = (String[]) obj;
	    StringBuffer str = new StringBuffer("");
	    for (int i = 0; i < strArray.length; i++) {
		if (!str.toString().equals("")) {
		    str.append(",");
		}
		str.append("'" + strArray[i] + "'");
	    }
	    jsonstr = "[" + str.toString() + "]";
	} else if (obj == null) {//�ն����򷵻ؿմ�
	    jsonstr = "''";
	} else if (obj.toString().indexOf("java.") < 0 && obj.toString().indexOf("org.") < 0) {//javaBean����
	    jsonstr = changeBean(obj, replaceType);
	}
	return jsonstr;
    }
    /**
     * �����϶���ת��Ϊjson
     * @param col  ��Ҫת����Json�ļ��϶���
     * @param replaceType  
     * @author duncan
     * @lastEdit 2007-10-23
     * @return Json�ַ���
     */
    private static String changeCollection(Collection col, String replaceType) {
	StringBuffer collectionstr = new StringBuffer("[");
	Object[] objs = col.toArray();
	for (int i = 0; i < objs.length; i++) {
	    collectionstr.append(changeToJson(objs[i], replaceType) + ",");
	}
	if (col.size() > 0) {
	    collectionstr.setCharAt(collectionstr.length() - 1, ']');
	} else {
	    collectionstr.append("]");
	}
	return collectionstr.toString();
    }
    /**
     * ��javaBean����ת��Ϊjson
     * @param col  ��Ҫת����Json��javaBean����
     * @param replaceType  
     * @author duncan
     * @lastEdit 2007-10-23
     * @return Json�ַ���
     */
    private static String changeBean(Object obj, String replaceType) {
	StringBuffer beanstr = new StringBuffer("");
	Class c = obj.getClass();
	beanstr.append("{");
	Field fields[] = c.getDeclaredFields();
	StringBuffer fieldName = new StringBuffer("");
	for (int i = 0; i < fields.length; i++) {
	    fieldName.delete(0, fieldName.length());
	    fieldName.append(fields[i].getName());
	    fieldName.setCharAt(0, (char) (fieldName.charAt(0) - 32));//����һ���ַ�ת��Ϊ��д
	    try {
		Method method = c.getDeclaredMethod("get" + fieldName.toString(), new Class[0]);
		Object objs = method.invoke(obj, new Class[0]);
		beanstr.append(fields[i].getName() + ":");
		if(method.isAnnotationPresent(Sweii.class)){
		    String f=method.getAnnotation(Sweii.class).format();
		    int dot=method.getAnnotation(Sweii.class).dot();
		    if(f!=null&&f.length()>0){
			format=f;
			beanstr.append(changeToJson(objs));
		    }else if(objs.getClass().getSimpleName().equals("Float")||objs.getClass().getSimpleName().equals("Double")){
			String d="0.##";
			if(dot==3){
			    d="0.###";
			}else if(dot==4){
			    d="0.####";
			}
			beanstr.append("'"+new DecimalFormat(d).format(objs)+"'");
		    }else{
			beanstr.append(changeToJson(objs));
		    }
		}else{
		     beanstr.append(changeToJson(objs));
		}
		if (i < fields.length - 1) {
		    beanstr.append(",");
		}
	    } catch (Exception e) {
	    }
	}
	beanstr.append("}");
	return beanstr.toString();
    }
    public static String replaceToJavascript(String str) {
	if (str != null) {
	    str = str.replaceAll("'", "\'");
	    str = str.replaceAll("\"", "\\\"");
	}
	return str;
    }
    public static String replace(String str) {
	if (str != null) {
	    str = str.replaceAll("&", "&amp;");
	    str = str.replaceAll("<", "&lt;");
	    str = str.replaceAll(">", "&gt;");
	    str = str.replaceAll("\r\n", "<br>");
	    str = str.replaceAll("\n\r", "<br>");
	    str = str.replaceAll("\n", "<br>");
	    str = str.replaceAll("\r", "<br>");
	    str = str.replaceAll("  ", " &nbsp;");
	    str = str.replaceAll("'", "&acute;");
	    str = str.replaceAll("\"", "&quot;");
	    return str;
	} else {
	    return "";
	}
    }
    public static void main(String[] args) {
	List data = new ArrayList();
	List a = new ArrayList();
	a.add("1");
	a.add("deng");
	a.add("http://ssqc.om");
	List b = new ArrayList();
	b.add("deng");
	List c = new ArrayList();
	c.add("http://ssqc.om");
	data.add(a);
	data.add(b);
	data.add(c);
	System.out.println(JsonUtil.changeToJson(data));
    }
}
