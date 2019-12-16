<%@page contentType="text/html;charset=GBK" language="java"%>
<%@include file="/frame/Init.jsp"%>
<%@page import="com.erican.auth.vo.*"%>
<%
Module module=(Module)request.getAttribute("module");
%>
<sw:init id="dg1">

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>���ܵ�</title>
<link href="/style/style.css" rel="stylesheet" type="text/css" />
<script src="/frame/js/Main.js"></script>
<script>
var moduleId='<%=module.getId()%>';
function add(){
	var diag = new Dialog("DiagFun");
	diag.Width = 600;
	diag.Height =292;
	diag.Title = "�½�"+this.document.title;
	diag.URL = "admin/prepareAddFunction.do?moduleId="+moduleId;
	diag.onLoad = function(){
		$DW.$("function.name").focus();
	};
	diag.OKEvent = addSave;
	diag.show();
}

function addSave(){
	var dc = $DW.Form.getData("form2");
	if($DW.Verify.hasError()){
	  return;
     }
	Server.sendRequest("admin/addFunction.do",dc,function(response){
		Dialog.alert(response.message,function(){
			if(response.status==1){
				$D.close();
				DataGrid.loadData('dg1');
			}
		});	
	},'json');
}
function del(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("����ѡ��Ҫɾ���Ĺ��ܵ㣡");
		return;
	}
	Dialog.confirm("��ȷ��Ҫɾ����Щ���ܵ���</br><b style='color:#F00'>"+arr.join(',</br>')+"</b>",function(){
		var dc = new DataCollection();
		dc.add("funIds",arr.join());
		Server.sendRequest("admin/deleteFunction.do",dc,function(response){
			Dialog.alert(response.message,function(){
				if(response.status==1){
					DataGrid.loadData('dg1');
				}
			});
		},'json');
	});
}
function edit(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("����ѡ��Ҫ�༭�Ĺ��ܵ㣡");
		return;
	}
	var diag = new Dialog("DiagFun");
	diag.Width = 600;
	diag.Height =292;
	diag.Title = "�༭ģ�鹦�ܵ�";
	diag.URL = "admin/prepareEditFunction.do?id="+arr[0];
	diag.onLoad = function(){
		$DW.$("function.name").focus();
	};
	diag.OKEvent = editSave;
	diag.show();
}

function editSave(){
	var dc = $DW.Form.getData("form2");
	if($DW.Verify.hasError()){
	  return;
    }
	Server.sendRequest("admin/editFunction.do",dc,function(response){
		Dialog.alert(response.message,function(){
			if(response.status==1){
				$D.close();
				DataGrid.loadData("dg1");
			}
		})
	},'json');
}
</script>
</head>
<body>
<table class="tools" style="width:100%">
        <tr>
            <td>
<sw:button onClick="add()">�½�</sw:button>		  
							  <sw:button  onClick="edit()">�޸�</sw:button>
							  <sw:button   onClick="del()">ɾ��</sw:button>	</td>
        </tr>
    </table>
		
    <sw:datagrid>       
         <table class="table" cellpadding="0" cellspacing="0" style="table-layout: fixed">
            <tr ztype="head" class="tr">
				   	        <td width="6%" ztype="RowNo">���</td>
					        <td width="4%" ztype="selector" field="id">&nbsp;</td>
				            <td width="15%" >��������</td>
							<td width="20%" >���ܴ���</td>
							<td width="45%" >Ȩ������</td>
					    </tr>
					    <tr>
				          <td >&nbsp;</td>
					      <td>&nbsp;</td>
						  <td>${name}</td>
						  <td>${code}</td>
						  <td>${permission}</td>
					    </tr>
						<tr ztype="pagebar" align="left" height="30px" style="background-color: #D3E1F1;">
                <td colspan="17" align="left" height="30px">${PageBar}</td>
            </tr>
					</table>
				</sw:datagrid>
</body>
</html>
</sw:init>
