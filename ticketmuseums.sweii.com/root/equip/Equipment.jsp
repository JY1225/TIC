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
<sw:dialog method="add" width="400" height="140"  title="�����豸" url="equip/EquipmentDialog.jsp" action="common/addEquipment.do"/>	
<sw:dialog method="edit" width="400" height="140"  title="�޸��豸" url="equip/EquipmentDialog.jsp" action="common/editEquipment.do" fields="id,name,ip,port,type"/>
<sw:confirm method="del" action="common/changeEquipment.do" confirm="��ȷ��Ҫɾ��ѡ����豸��" field="flag" value="0"  success="ɾ���豸�ɹ���" failure="ɾ���豸ʧ�ܣ�"   multi="true"/>
 
</head>
<body  style="background-color: #DFE6F8">
<div class="box01" align="center"><span class="left">&nbsp;</span> <span class="tit" style="font-size:20px"><b>�豸��Ϣ����</b></span></div>
<table class="tools" style="width:100%">
    <tr>
        <td>
        <sw:button limit="true" onClick="add();">����</sw:button>
        <sw:button limit="true" onClick="edit();">�޸�</sw:button>
        <sw:button limit="true" onClick="del();">ɾ��</sw:button>
        </td>
    </tr>
</table>
<sw:datagrid editFields="equipment,id,name,item.id,ip,port" multiSelect="false">       
        <table class="table" cellpadding="0" cellspacing="0">
            <tr ztype="head" class="tr"> 
                <td width="5%" align="center" ztype="RowNo"><b>���</b></td>
                <td width="2%" ztype="selector" field="id">&nbsp;</td>
                <td width="15%">&nbsp;<b>�豸����</b></td>
                <td width="15%">&nbsp;<b>IP��ַ</b></td>
                <td width="10%">&nbsp;<b>�˿�</b></td>
                <td width="15%">&nbsp;<b>����</b></td>
                <td width="20%">&nbsp;<b>����ʱ��</b></td>
                <td width="15%">&nbsp;<b>״̬</b></td>
            </tr>
            <tr >
                <td align="center">&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;${name}</td>   
                <td>&nbsp;${ip}</td>      
                <td>&nbsp;${port}</td>      
                <td>&nbsp;${type}</td>      
                <td>&nbsp;${createTime}</td>  
                <td>&nbsp;${statusStr}</td>                 
            </tr>
            <tr ztype="pagebar" align="left" height="30px" style="background-color: #D3E1F1;">
                <td colspan="15" align="left" height="30px">${PageBar}</td>
            </tr>
        </table>
    </sw:datagrid>
</body>
</html>
</sw:init>
