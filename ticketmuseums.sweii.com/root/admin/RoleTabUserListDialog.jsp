<%@page contentType="text/html;charset=GBK" language="java"%>
<%@include file="/frame/Init.jsp"%>
<sw:init id="dg1">

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title> </title>
	<link href="/frame/css/ext-all.css" rel="stylesheet" type="text/css" />
	<script src="/frame/js/Main.js"></script>
<script type="text/javascript">
function doSearch(){
	var name = "";
	if ($V("SearchUserName") != "�������û�������ʵ����") {
		name = $V("SearchUserName").trim();
	}
	DataGrid.setParam("dg1",Constant.PageIndex,0);
	DataGrid.setParam("dg1","searchContent",name);
	DataGrid.loadData("dg1");
}

document.onkeydown = function(event){
	event = getEvent(event);
	if(event.keyCode==13){
		var ele = event.srcElement || event.target;
		if(ele.id == 'SearchUserName'||ele.id == 'Submibutton'){
			doSearch();
		}
	}
}

function delKeyWord() {
	if ($V("SearchUserName") == "�������û�������ʵ����") {
		$S("SearchUserName","");
	}
}
</script>
</head>
<body>

	<div class="x-panel-header x-unselectable"style="-moz-user-select: none;">
	<table align="right" >
		<tr>
		
		<td> <input name="SearchUserName" type="text" id="SearchUserName" value="�������û�������ʵ����" onFocus="delKeyWord();" style="width:150px"></td>
      	 <td> <input type="button" name="Submibutton" id="Submibutton" value="��ѯ" onClick="doSearch()"></td>
			<td>&nbsp;</td>
		</tr>
	</table>
	</div>		
		<sw:datagrid id="dg1" size="10" 
					fields="id,username,name,email,mobile,phone,statusName" ajax="admin/prepareAddRoleAdmin.do">
		     <table width="100%" cellpadding="2" cellspacing="0" class="dataTable">
				<tr ztype="head" class="x-grid3-hd-row">
		   	        <td width="7%" ztype="RowNo">���</td>
			        <td width="7%" ztype="selector" field="id">&nbsp;</td>
		            <td width="23%" ><b>�û���</b></td>
					<td width="25%" ><b>��ʵ����</b></td>
					<td width="16%" ><b>�û�״̬</b></td>
					<td width="22%" ><b>�����ʼ�</b></td>
			    </tr>
			    <tr >
		          <td >&nbsp;</td>
			      <td>&nbsp;</td>
				  <td>${username}</td>
				  <td>${name}</td>
				  <td>${statusName}</td>
				  <td>${email}</td>
			    </tr>
				<tr ztype="pagebar">
				  <td colspan="6" align="center">${PageBar}</td>
				</tr>
			</table>
		</sw:datagrid>
</body>
</html>
</sw:init>
