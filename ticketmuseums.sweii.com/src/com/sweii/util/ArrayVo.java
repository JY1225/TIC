package com.sweii.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author duncan
 * @createTime 2011-2-16
 * @version 1.0
 */
public class ArrayVo {
    private List fields=new ArrayList();//���޸���
    private List joinFields=new ArrayList();//���޸���
    private List values=new ArrayList();//����Ӧֵ
    private List<List> joinValues=new ArrayList();//���б�ֵ
    public static void main(String[] args) {
	ArrayVo vo=new ArrayVo();
	vo.getFields().add("id");
	vo.getFields().add("name");
	vo.getValues().add(1);
	vo.getValues().add("name");
	System.out.println(JsonUtil.changeToJson(vo));
    }
    public List getFields() {
        return fields;
    }
    public void setFields(List fields) {
        this.fields = fields;
    }
    public List getJoinFields() {
        return joinFields;
    }
    public void setJoinFields(List joinFields) {
        this.joinFields = joinFields;
    }
    public List<List> getJoinValues() {
        return joinValues;
    }
    public void setJoinValues(List<List> joinValues) {
        this.joinValues = joinValues;
    }
    public List getValues() {
        return values;
    }
    public void setValues(List values) {
        this.values = values;
    }
}
