<%@page contentType="text/html;charset=GBK" language="java"%>
<%@include file="/frame/Init.jsp"%>
<sw:init id="dg1">
<!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>ģ�����</title>
    <link href="/style/style.css" rel="stylesheet" type="text/css" />
<script src="/frame/js/Main.js"></script>
<sw:dialog method="add" width="400" height="110"  title="�½�ģ�����" url="admin/ModuleCategoryAddDialog.jsp" action="common/addModuleCategory.do"/>	
<sw:dialog method="edit" width="400" height="110"  title="�޸�ģ�����" url="admin/ModuleCategoryAddDialog.jsp" action="common/editModuleCategory.do" fields="id,name,description"/>	
<sw:confirm method="del" action="common/deleteModuleCategory.do" confirm="��ȷ��Ҫɾ��ѡ���ģ�������" success="ɾ��ģ�����ɹ���" failure="ɾ��ģ�����ʧ�ܣ�"    multi="true"/>
</head>
<body style="background-color: #DFE6F8;">
    <div class="box01"  align="center"><span align="left">&nbsp;</span> <span class="tit" style="font-size: 20px">ģ�����</span></div>
<table class="tools" style="width:100%">
    <tr>
        <td>
        <sw:button limit="true" onClick="add();">����</sw:button>
        <sw:button limit="true" onClick="edit();">�޸�</sw:button>
        <sw:button limit="true" onClick="del();">ɾ��</sw:button>
        </td>
    </tr>
</table>		 
    <sw:datagrid>
        <table class="table" cellpadding="0" cellspacing="0" style="table-layout: fixed">
            <tr ztype="head" class="tr">
								<td width="4%" align="center" ztype="RowNo"><b>&nbsp;���</b></td>
								<td width="3%" align="center" ztype="selector" field="id">&nbsp;</td>
								<td width="25%"><b>&nbsp;��������</b></td>
								<td width="68%"><b>&nbsp;��������</b></td>
							</tr>
							<tr>
								<td align="center">&nbsp;</td>
				                <td align="center">&nbsp;</td>
								<td>&nbsp;${name}</td>
								<td>&nbsp;${description}</td>
							</tr>
            <tr ztype="pagebar" align="left" height="30px" style="background-color: #D3E1F1;">
                <td colspan="6" align="left" height="30px">${PageBar}</td>
            </tr>
        </table>
    </sw:datagrid>
</body>
</html>
</sw:init>
