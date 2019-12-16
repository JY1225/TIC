package com.sweii.vo;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * �����ֵ�
 * 
 * @author duncan
 * @createTime 2009-11-24
 * @version 1.0
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class Code {
    private Integer id;// ID��
    private Integer parentId;// ����ID
    private String codeType;// ����
    private String codeName;// ����
    private String codeValue;// ֵ
    private Integer showType;// �Ƿ���ʾ,1��ʾ��ʾ
    private Integer type;
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getShowType() {
	return showType;
    }
    public void setShowType(Integer showType) {
	this.showType = showType;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
	return id;
    }
    public void setId(Integer id) {
	this.id = id;
    }
    public String getCodeName() {
	return codeName;
    }
    public void setCodeName(String codeName) {
	this.codeName = codeName;
    }
    public String getCodeType() {
	return codeType;
    }
    public void setCodeType(String codeType) {
	this.codeType = codeType;
    }
    public String getCodeValue() {
	return codeValue;
    }
    public void setCodeValue(String codeValue) {
	this.codeValue = codeValue;
    }
    public Integer getParentId() {
	return parentId;
    }
    public void setParentId(Integer parentId) {
	this.parentId = parentId;
    }
}
