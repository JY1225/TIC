<%@page contentType="text/html;charset=GBK" language="java"%>
<%@include file="/frame/Init.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sweii.vo.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.sweii.framework.helper.*"%>
<%@ page import="com.sweii.bean.*"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title></title>
<link href="/style/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="/frame/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/ztree/jquery.ztree.core-3.4.js"></script>
<script type="text/javascript" src="/ztree/jquery.ztree.excheck-3.4.js"></script>
<style type="text/css">
.table-border { /*border-top:1px solid #000;  */
    border-bottom: 1px solid #000; /*�²��߿�*/
    border-left: 1px solid #000; /*�󲿱߿�*/
    border-right: 0px solid #000; /* */
}

.table-border td {
    border-top: 1px solid #000; /*�ϲ��߿�*/
    border-right: 1px solid #000; /*�Ҳ��߿�*/
}
ul.ztree {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:465px;height:300px;overflow-y:scroll;overflow-x:auto;}
</style>
<script type="text/javascript">
	var setting = {
		check: {
		enable: true,
		chkboxType: {"Y":"ps", "N":"ps"}
		},
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeClick: beforeClick,
			onCheck: onCheck
		}
	};
	function beforeClick(treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		zTree.checkNode(treeNode, !treeNode.checked, null, true);
		return false;
	}

	function onCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(treeId),
		nodes = zTree.getCheckedNodes(true),
		v = "",
		ids="";
		for (var i=0, l=nodes.length; i<l; i++) {
			if(nodes[i].id<=0)continue;
			v += nodes[i].name + ",";
			ids += nodes[i].id + ",";
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		if (ids.length > 0 ) ids = ids.substring(0, ids.length-1);
		var htmlObj = $("#tripStr");
		htmlObj.attr("value", v);
		var htmlObj = $("#tripStrIds");
		htmlObj.attr("value", ids);
	}

	function showMenu() {
		var htmlObj = $("#tripStr");
		var htmlOffset = $("#tripStr").offset();
		$("#adminContent").fadeOut("fast");
		$("#adminContent").css({left:htmlOffset.left + "px", top:htmlOffset.top + htmlObj.outerHeight() + "px"}).slideDown("fast");
		$("body").bind("mousedown", onBodyDown);
	}
	function hideMenu() {
		$("#adminContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "tripStr"
			|| event.target.id == "adminContent" 
				|| $(event.target).parents("#adminContent").length>0)) {
			hideMenu();
		}
	}

	$(document).ready(function(){
		$.fn.zTree.init($("#adminTree"), setting, saleAdminNodes);
		
	});

	function query(){
		$("form").submit();
	}
	function SaveAsFile(){
		LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));  
		LODOP.PRINT_INIT("");
		LODOP.ADD_PRINT_TABLE(100,20,500,80,document.documentElement.innerHTML);
//		LODOP.SET_SAVE_MODE("QUICK_SAVE",true);//�������ɣ��ޱ����ʽ,�������ϴ�ʱ�����õ���
		LODOP.SAVE_TO_FILE("���ļ���.xls");
	};
	<s:property value="#request.saleAdminTree" escape="false"/>
</script>
<%  SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); %>
</head>
<body style="background-color: #DFE6F8;">
<jsp:include page="/frame/Printer.jsp" />
<div class="box01" align="center"><span class="left">&nbsp;</span> <span class="tit" style="font-size: 20px"><b>����Ԥ��ͳ�Ʊ�</b></span></div>
<center>
<div style="height:600px;overflow-y:scroll;overflow-x:hidden;overflow:scroll;">
<form action="/report/reportAdminPreTicket.do" method="post" id="form">
<s:hidden  name="tripStrIds" id="tripStrIds"/>
<table  width="950px" style="font-size:12px">
    <tr>
        <td width="100%">
        <fieldset><legend><label>&nbsp;&nbsp;<b style="font-size: 14px" style='font-size:12px'>��ѯ����</b></label></legend>
        <table>
            <tr height="50px">
            <td style='font-size:12px'>
            		<span style='display:none'>&nbsp;&nbsp;��ƱԱ��ţ�&nbsp;<input id="saleAdmin" name="saleAdmin" value="<s:property value="saleAdmin"/>" type="text" style="width: 150px" /></span>
                	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ʼʱ�䣺<input id="startTime" name="startTime" type="text" style="width:150px"  class="Wdate" value="<s:property value="startTime"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /><img onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'startTime'})" src="/images/Calendar.gif" align='absmiddle' style='cursor:pointer'  vspace='1' >
                	
                	&nbsp;&nbsp;����ʱ�䣺<input id="endTime" name="endTime" type="text" style="width:150px" class="Wdate" value="<s:property value="endTime"/>"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /><img onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'endTime'})" src="/images/Calendar.gif" align='absmiddle' style='cursor:pointer'  vspace='1' >
                	
             </td>
            </tr>
         	<tr style='display:none'>
         		<td width="50%" valign="middle" style='font-size:12px'>&nbsp;&nbsp;ѡ����ƱԱ��<s:textarea id="tripStr" name="tripStr" readonly="true" onclick="showMenu();"  cols="90" rows="3" />
         		</td>
         	</tr>
         	<tr>
         	<td colspan="3" align="center"><sw:button onClick="query()">��ѯ</sw:button></td>
         	</tr>
        </table>
        </fieldset>
        </td>
    </tr>
</table>
</form>
    <table width="950px" border="0" cellspacing="0"  >
        <tr>
            <td width="15%" style="font-weight: bold;" align="left"></td>
            <td align="center" width="50%" style="font-weight: bold; font-size: 25px">����Ԥ��ͳ�Ʊ�</td>
            <td width="20%" style="font-weight: bold;font-size:12px" align="right"><a href="javascript:void(0);" onclick="printHA4('print');" class="printBtn"><img src="/Icons/icon013a1.gif" />��ӡ</a><a href="javascript:void(0);" onclick="saveAsFile('print','����Ԥ��ͳ�Ʊ�.xls');" class="printBtn"><img src="/Icons/icon018a7.gif" />����</a></td>
        </tr>       
        <tr>
            <td colspan="4" align="center">
            <span id='print'>
            <table width="98%" border="0" cellspacing="0" class="table-border" style="height: 25px; text-align: left"  style="table-layout: fixed;">
                <tr height="25px" style="font-weight: bold;font-size:12px;background-color: white">
                	<td align="center" style="width:80px">�������</td> 
                	<td align="center" style="width:150px">��˾����</td>   
                	<td align="center" style="width:150px">Ʊ������</td> 
                    <td align="center" style="width:120px">�տ�ʱ��</td>
                     <td align="center" style="width:80px">�տ���</td>                	
                	<td align="center" style="width:80px">Ʊ��</td>  
                	<td align="center" style="width:80px">����</td>          
                    <td align="center" style="width:80px">�ܽ��</td>
                   
                </tr>
                <%
                	List<PreTicket> tickets=(List<PreTicket>)request.getAttribute("tickets");
                int size=0;
                int total=0;
                for(PreTicket ticket:tickets){
                	size+=ticket.getTicketSize();
                	total+=ticket.getTicketAmount();
               	%>
               			
               			 <tr height="25px" style="font-size:12px;background-color: white">
               				<td align="center"><%=ticket.getOrderNumber() %></td>
               				<td align="center"><%=ticket.getCompany() %></td>
               				<td align="center"><%=ticket.getTicket().getName() %></td>
               				
               				<td align="center"><%=timeFormat.format(ticket.getOperateTime()) %> </td>
               				 <td align="center"><%=ticket.getOperator() %></td>
               				 <td align="center"><%=ticket.getPrice() %></td>
               				 <td align="center"><%=ticket.getTicketSize() %></td>		                    
               				<td align="center"><%=ticket.getTicketAmount() %></td>
		                </tr>
                <%} %>
                <tr height="25px" style="font-size:12px;background-color: white">
               				<td align="center" colspan="6" style="color:red;font-weight:bold;">�ϼ�:</td>
		                    <td align="center" style='color:red;font-weight:bold'><%=size %></td>
               				<td align="center" style='color:red;font-weight:bold'><%=total %></td>
		                </tr>
            </table>
            </span>
            </td>
        </tr>
    </table>
    </div>
    <div id="adminContent" class="menuContent" style="display:none;margin-top:0px; position: absolute;">
		<ul id="adminTree" class="ztree" style="margin-top:0; width:510px; height: 300px;"></ul>
	</div></center>
</body>
</html>