<%@page contentType="text/html;charset=GBK" language="java"%>
<%@include file="/frame/Init.jsp"%>
<sw:init id="dg1">
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<title>�������</title>
 <link href="/style/style.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="/frame/js/Main.js"></script>
    <sw:dialog method="add" width="480" height="100"  title="�½���ɫ" url="admin/RoleDialog.jsp" action="common/addRole.do"/> 
   <sw:dialog method="edit" width="480" height="100"  title="�޸Ľ�ɫ" url="admin/RoleDialog.jsp" action="common/editRole.do" fields="name,description"/> 
<sw:confirm method="del" action="common/deleteRole.do" confirm="��ȷ��Ҫɾ��ѡ��Ľ�ɫ��"  success="ɾ����Ϣ�ɹ���" failure="ɾ����Ϣʧ�ܣ�"   multi="true"/>

<script>
function view(id,name){
    var diag = new Dialog("viewSubList"+id);
    diag.Width = 720;
    diag.Height =482;
    diag.Title = name+"-Ȩ�޷���";
    diag.URL = "admin/viewRoleFunction.do?id="+id;
    diag.CancelEvent = function(){
        diag.close();
    }
    diag.ShowOKButton=false;
    diag.CanceButtonText="�� ��";
    diag.show();//��ʾ��
}
function viewUser(id,name){
    var diag = new Dialog("viewList"+id);
    diag.Width = 700;
    diag.Height =396;
    diag.Title = name+"-Ȩ�޷���";
    diag.URL = "common/selectAdminRolePage.do?jsp=admin/AdminRole.jsp&multiSelect=true&selectSize=15&orderField=id&order=asc&adminRole.role.id="+id;
    diag.CancelEvent = function(){
        diag.close();
    }
    diag.ShowOKButton=false;
    diag.CanceButtonText="�� ��";
    diag.show();//��ʾ��
}
</script>
	</head>
	<body style="background-color: #DFE6F8;">
    <div class="box01"  align="center"><span align="left">&nbsp;</span> <span class="tit" style="font-size: 20px">��ɫȨ�޹���</span></div>
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
                        <td width="5%" align="center" ztype="RowNo"><b>���</b></td>
                        <td width="5%" align="center" ztype="selector" field="id">&nbsp;</td>
                        <td width="20%">&nbsp;<b>��ɫ����</b></td>
                        <td width="20%">&nbsp;<b>��ɫ����</b></td>
                        <td width="20%">&nbsp;<b>����ʱ��</b></td>
                        <td width="30%">&nbsp;<b>����</b></td>
                    </tr>
                    <tr>
                        <td align="center">&nbsp;</td>
                        <td align="center">&nbsp;</td>
                        <td>&nbsp;${name}</td>
                        <td>&nbsp;${description}</td>
                        <td>&nbsp;${createTime}</td>
                        <td><span  style="cursor: hand"  onclick="view(${id},'${name}')"><img src="/Icons/icon404a7.gif"  style="vertical-align:bottom"  height="16"></img>����Ȩ��</span>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    </tr>
			<tr ztype="pagebar" align="left" height="30px" style="background-color: #D3E1F1;">
                <td colspan="7" align="left" height="30px">${PageBar}</td>
            </tr>
        </table>
    </sw:datagrid>
	</body>
	</html>
</sw:init>
