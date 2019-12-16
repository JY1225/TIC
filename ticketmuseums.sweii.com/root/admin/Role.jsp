<%@page contentType="text/html;charset=GBK" language="java"%>
<%@include file="/frame/Init.jsp"%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title> </title>
<link href="/frame/css/frame.css" rel="stylesheet" type="text/css" />
<script src="/frame/js/Main.js"></script>
<script src="/frame/js/Tabpage.js"></script>
<script>
var treeItem = null;

Page.onLoad(function(){
	var tree = $("tree1");
	var arr = tree.getElementsByTagName("p");
	for(var i=0;i<arr.length;i++){
		var p = arr[i];
		if(i==1){
			p.className = "cur";
			Tree.CurrentItem = p;
			p.onclick.apply(p);
			break;
		}
	}
});

Page.onClick(function(){
	var div = $("_DivContextMenu");
	if(div){
			$E.hide(div);
	}
});

function showMenu(event,ele){
	var cid = ele.getAttribute("cid");
	if(!cid){
		return ;
	}
	var menu = new Menu();
	menu.setEvent(event);
	menu.setParam(cid);
	menu.addItem("�½���ɫ",add,"Icons/icon018a2.gif");
	menu.addItem("�޸Ľ�ɫ",showEditDialog,"Icons/icon018a2.gif");
	menu.addItem("ɾ����ɫ",del,"Icons/icon018a2.gif");
	menu.show();
}
function add(param){
	var diag = new Dialog("Diag1");
	diag.Width = 380;
	diag.Height = 150;
	diag.Title = "�½���ɫ";
	diag.URL = "admin/prepareAddRole.do";
	diag.onLoad = function(){
		$DW.$("name").focus();
	};
	diag.OKEvent = addSave;
	diag.show();
}

function addSave(){
	var dc = Form.getData($DW.$F("form2"));
	if($DW.Verify.hasError()){
	  return;
     }
	Server.sendRequest("admin/addRole.do",dc,function(response){
		var message="�½���ɫ�ɹ�";
		if(response!='1'){
			message="�½���ɫʧ��"
		}
		Dialog.alert(message,function(){
			if(response=='1'){
				$D.close();
				window.location.reload();
			}
		});
	});
}

function del(id,name){
	if(!id){
		Dialog.alert("����ѡ��һ����ɫ��");
		return ;
	}
	Dialog.confirm("ȷ��ɾ��<b style='color:#F00'> "+name+"</b> ��ɫ��",function(){
		var dc = new DataCollection();
		dc.add("id",id);
		Server.sendRequest("admin/deleteRole.do",dc,function(response){
			var message="ɾ����ɫ�ɹ�";
			if(response!='1'){
				message="ɾ����ɫʧ��"
			}
			Dialog.alert(message,function(){
				if(response=='1'){
					window.parent.parent.location.reload();
				}
			});
		});
	});
}

function showEditDialog(param){
	if(!param){
		Dialog.alert("����ѡ��һ����ɫ��");
		return ;
	}
	var diag = new Dialog("Diag1");
	diag.Width = 350;
	diag.Height = 80;
	diag.Title = "�޸Ľ�ɫ";
	diag.URL = "admin/prepareEditRole.do?id="+param;
	diag.onLoad = function(){
		$DW.$("name").focus();
	};
	diag.OKEvent = editSave;
	diag.show();
}

function editSave(){
	var dc = Form.getData($DW.$F("form2"));
	if($DW.Verify.hasError()){
	  	return;
     }
	Server.sendRequest("admin/editRole.do",dc,function(response){
		var message="�޸Ľ�ɫ�ɹ�";
		if(response!='1'){
			message="�޸Ľ�ɫʧ��"
		}
		Dialog.alert(message,function(){
			if(response=='1'){
				$D.close();
				window.parent.location.reload();
			}
		});
	});
}
function attribute(param){
	var RoleCode = param;
	var diag = new Dialog("Diag1");
	diag.Width = 260;
	diag.Height = 130;
	diag.Title = "����";
	diag.URL = "Platform/RoleAttribute.jsp?RoleCode="+param;
	diag.ShowButtonRow = false;
	diag.show();
}

function onRoleTabClick(){
	var cid = "";
	if(treeItem){
		cid = treeItem.getAttribute("cid");
	}
	var currentTab = Tab.getCurrentTab().contentWindow;
	if(currentTab.$("id"))currentTab.$S("id",cid);
	if(Tab.getCurrentTab()==Tab.getChildTab("Basic")){
		Tab.getCurrentTab().src = "/admin/viewRole.do?id="+cid;
	}else if(currentTab.init){
		currentTab.init();
	}
}

function onTreeClick(ele){
	var cid = ele.getAttribute("cid");
	if(!cid){
		return ;
	}
	treeItem = ele;
	var currentTab = Tab.getCurrentTab().contentWindow;
	if(currentTab.$("id"))currentTab.$S("id",cid);
	if(Tab.getCurrentTab()==Tab.getChildTab("Basic")){
			Tab.getCurrentTab().src = "/admin/viewRole.do?id="+cid;
	}else if(currentTab.init){
		currentTab.init();
	}
}


//../admin/queryRoleFunction.do
</script>
</head>
<body >
  <table width="100%" border="0" cellspacing="6" cellpadding="0" style="border-collapse: separate; border-spacing: 6px;">
    <tr valign="top">
    <td width="180">
<table width="180" border="0" cellspacing="0" cellpadding="6" class="blockTable">
        <tr>
          <td style="padding:6px;" class="blockTd">
		  <sw:tree id="tree1" style="height:430px" level="2"  rootText="��ɫ�б�"
		  branchIcon="Icons/icon025a1.gif" leafIcon="Icons/icon025a1.gif">
		  	<p cid='${id}' cname='${name}' onClick="onTreeClick(this);" oncontextmenu="showMenu(event,this);">&nbsp;${name}</p>
		  </sw:tree>
		  </td>
        </tr>
      </table>	
	</td>
    <td>
	<sw:tab>
	<sw:childtab id="Basic" src="../admin/viewRole.do" afterClick="onRoleTabClick()">
	<img src='../Icons/icon018a1.gif' /><b>������Ϣ</b>
	</sw:childtab>
    <sw:childtab id="RoleFunction" src="../admin/queryRoleFunction.do"  selected="true" afterClick="onRoleTabClick()"><img src="../Icons/icon018a1.gif" /><b>�˵�Ȩ��</b></sw:childtab>
	</sw:tab>
	</td>
    </tr>
  </table>
</body>
</html>

