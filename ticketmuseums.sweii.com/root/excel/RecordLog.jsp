<%@page contentType="text/html;charset=GBK" language="java"%>
<%@include file="/frame/Init.jsp"%>
<sw:init id="dg1">
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>��Ա����</title>
 <link href="/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/frame/js/Main.js"></script>
<script type="text/javascript" src="/frame/datepicker/WdatePicker.js"></script>
   <sw:dialog method="add" width="500" height="320"  title="����ӻ�Ա" url="excel/RecordLogDialog.jsp" action="common/addRecordLog.do"/> 
    <sw:dialog method="edit" width="500" height="320"  title="�޸Ļ�Ա" url="excel/RecordLogDialog.jsp" action="common/editRecordLog.do" fields="id,name,number,phone,remark"/> 
    <sw:confirm method="del" action="common/deleteRecordLog.do" confirm="��ȷ��Ҫɾ��ѡ��Ļ�Ա��"  success="ɾ����Ա�ɹ���" failure="ɾ����Աʧ�ܣ�"   multi="true"/>   
  <sw:search fields="RecordLog.userId" method="search" />   
</head>
<body  style="background-color: #DFE6F8;">
<div class="box01" align="center"><span class="left">&nbsp;</span> <span class="tit" style="font-size:20px"><b>������������</b></span></div>
<table class="tools" style="width:100%">
        <tr>
            <td>
��Ա����:<input id="user.name" type="text"></input>
����:<input id="user.number" type="text" ></input>
<sw:button onClick="search()">��ѯ</sw:button>
</td>
        </tr>
    </table>
    
<sw:datagrid multiSelect="false">       
           <table class="table" cellpadding="0" cellspacing="0" style="table-layout: fixed">
              <tr ztype="head" class="tr">
                <td width="34px" ztype="RowNo">&nbsp;<b>���</td>
                <td width="34px" ztype="selector" field="id">&nbsp;</td>
                <td width="100px">&nbsp;<b>��Ա����</b></td>
                <td width="100px">&nbsp;<b>�ͻ�����</b></td>
                <td width="100px">&nbsp;<b>����</b></td>
                <td width="100px">&nbsp;<b>ˢ��ʱ��</b></td>
                <td width="100px">&nbsp;<b>����ʱ��</b></td>
            </tr>
            <tr>
                <td align="center">&nbsp;</td>
                <td >&nbsp;</td>
                <td>&nbsp;${user.name}</td>
                <td>&nbsp;${type}</td>
                 <td>&nbsp;${number}</td>
                 <td>&nbsp;${createTime}</td>
                 <td>&nbsp;${passTime}</td>

            </tr>
            <tr ztype="pagebar" align="left" height="30px" style="background-color: #D3E1F1;">
                <td colspan="17" align="left" height="30px">${PageBar}</td>
            </tr>
        </table>
    </sw:datagrid>
</body>
</html>
</sw:init>
