<%@page contentType="text/html;charset=GBK" language="java"%>
<%@include file="/frame/Init.jsp"%>
<sw:init id="dg1">
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>�豸����</title>
 <link href="/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/frame/js/Main.js"></script>
<script type="text/javascript" src="/frame/datepicker/WdatePicker.js"></script>
   <sw:dialog method="add" width="380" height="160"  title="�½��豸" url="base/EquipmentDialog.jsp" action="common/addEquipment.do" /> 
    <sw:dialog method="edit" width="380" height="160"  title="�޸��豸" url="base/EquipmentDialog.jsp" action="common/editEquipment.do" fields="name,ip,port,createTime"/> 
    <sw:confirm method="del" action="common/deleteEquipment.do" confirm="��ȷ��Ҫɾ��ѡ����豸��"  success="ɾ���豸�ɹ���" failure="ɾ���豸ʧ�ܣ�"   multi="true"/>   
  <sw:search fields="equipment.name,equipment.ip" method="search" />   
</head>
<body  style="background-color: #DFE6F8;">
<div class="box01" align="center"><span class="left">&nbsp;</span> <span class="tit" style="font-size:20px"><b>�豸���� </b></span></div>
<table class="tools" style="width:100%">
    <tr>
       <td>
				<sw:button src="/Icons/icon002a2.gif"  limit="true" onClick="add()">�½�</sw:button>
				<sw:button src="/Icons/icon018a4.gif"  limit="true" onClick="edit()">�޸�</sw:button>
				<sw:button src="/Icons/icon018a3.gif" limit="true"  onClick="del()">ɾ��</sw:button>
				����:<input id="equipment.name" type="text" size="10"></input>
				IP:<input id="equipment.ip" type="text" size="10"></input>
				<sw:button onClick="search()">��ѯ</sw:button>
		</td>
	</tr>
</table>
<sw:datagrid>       
           <table class="table" cellpadding="0" cellspacing="0" style="table-layout: fixed">
              <tr ztype="head" class="tr">
                <td width="34px" ztype="RowNo">&nbsp;<b>���</td>
                <td width="34px" ztype="selector" field="id">&nbsp;</td>
                <td width="100px">&nbsp;<b>����</b></td>
                <td width="100px">&nbsp;<b>IP��ַ</b></td>
                <td width="100px">&nbsp;<b>�˿�</b></td>
                <td width="100px">&nbsp;<b>����ʱ��</b></td>
                <td width="100px">&nbsp;<b>����״̬</b></td>
            </tr>
            <tr>
                <td align="center">&nbsp;</td>
                <td >&nbsp;</td>
                <td>&nbsp;${name}</td>
                <td>&nbsp;${ip}</td>
                 <td>&nbsp;${port}</td>
                 <td>&nbsp;${createTime}</td>
                 <td>&nbsp;${statusStr}</td>
            </tr>
            <tr ztype="pagebar" align="left" height="30px" style="background-color: #D3E1F1;">
                <td colspan="17" align="left" height="30px">${PageBar}</td>
            </tr>
        </table>
    </sw:datagrid>
</body>
</html>
</sw:init>
