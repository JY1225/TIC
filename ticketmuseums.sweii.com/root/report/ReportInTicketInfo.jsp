<%@page contentType="text/html;charset=GBK" language="java"%>
<%@include file="/frame/Init.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sweii.framework.helper.*"%>
<%@ page import="com.sweii.bean.*"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>����01</title>
<link href="/style/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="/frame/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/ztree/jquery.ztree.core-3.4.js"></script>
<script type="text/javascript" src="/ztree/jquery.ztree.excheck-3.4.js"></script>
<script type="text/javascript" src="/js/Report.js"></script>
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
ul.ztree {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:365px;height:300px;overflow-y:scroll;overflow-x:auto;}
</style>
<script type="text/javascript">
function query(){
	$("form").submit();
}
</script>
</head>
<%
Integer type=(Integer)request.getAttribute("type");
%>
<body style="background-color: #DFE6F8;">
<jsp:include page="/frame/Printer.jsp" />
<div class="box01" align="center"><span class="left">&nbsp;</span> <span class="tit" style="font-size: 20px"><b>
	���ͳ�Ʊ�
</b></span></div>

<center>
<div style="height:<%=(Integer.valueOf(height)-30) %>px;overflow-y:scroll;overflow-x:hidden;overflow:scroll;">
<form action="/report/reportInTicketInfo.do" method="post" id="form">
<s:hidden name="type"/>
<jsp:include page="SearchPage.jsp"></jsp:include>
</form>
    <table width="99%" border="0" cellspacing="0"  >
        <tr>
            <td width="15%" style="font-weight: bold;" align="left"></td>
            <td align="center" width="50%" style="font-weight: bold; font-size: 25px">
           		���ͳ�Ʊ�
            </td>
            <td width="20%" style="font-weight: bold;font-size:12px" align="right"><a href="javascript:void(0);" onclick="printHA4('print');" class="printBtn"><img src="/Icons/icon013a1.gif" />��ӡ</a><a href="javascript:void(0);" onclick="saveAsFile('print','<s:if test="type==1">����Ʊͳ�Ʊ�</s:if><s:elseif test="type==2">����Ʊͳ�Ʊ�</s:elseif><s:elseif test="type==3">����Ʊͳ�Ʊ�</s:elseif> .xls');" class="printBtn"><img src="/Icons/icon018a7.gif" />����</a></td>
        </tr>       
        <tr>
            <td colspan="4" align="center">
            <span id="print">
            <table width="98%" border="0" cellspacing="0" class="table-border" style="height: 25px; text-align: left">
                <tr height="25px" style="font-weight: bold;font-size:12px;background-color: white">
               		<td align="center">�������</td>                
                    <td align="center">Ʊ��</td>
                    <td align="center">�������</td>
                </tr>
                <%
               		StatBean statBean=(StatBean)request.getAttribute("statBean");
                	
                	Map<Integer,String> tsMap=(Map<Integer,String>)request.getAttribute("tsMap");
                
                	List<CategoryBean> cbList=statBean.list;
               		for(CategoryBean cb:cbList){
               			int i=0;
               			boolean wrsend=false;
               			for(TypeBean tb:cb.list){%>
               			 <tr height="25px" style="font-size:12px;background-color: white">
               				<%
               				if(!wrsend){
               					wrsend=true;
               				%>
               				<td align="center"   rowspan="<%=(cb.list.size())%>"><%=tb.getTime() %></td>
               				
               				<%}
               					if (tb.getId()==1){
               				%>		
               					<td align="center">��ԱƱ</td>
               				<% }else if(tb.getId()==2){%>
		                   		<td align="center"> ɢ��Ʊ</td>
               				<% 
               					}else if(tb.getId()==3){
               				%>
               				 <td align="center"> ����Ʊ</td>
               				<%} %>
			                <td align="center"><%=NumbericHelper.getIntValue(tb.getSaleCount(),0)%></td>
		                </tr>
               			<%}%>
                		<tr height="25px" style="font-size:12px;background-color: white">
                		<td align="center" colspan="2" style='color:blue;font-weight:bold'>С��:</td> 
		                <td align="center" style='color:blue;font-weight:bold'><%=NumbericHelper.getIntValue(cb.getSaleCount(),0)%></td>
	                    </tr>
                	<%}
                %>
                <tr height="25px" style="font-size:12px;background-color: white">
               		<td align="center" colspan="2" style='color:red;font-weight:bold'>�ܼ�:</td> 
	                <td align="center" style='color:red;font-weight:bold'><%=NumbericHelper.getIntValue(statBean.getSaleCount(),0)%></td>
                </tr>
            </table>
            </span>
            </td>
        </tr>
    </table>
    <br></br>
    <br></br>
    <br></br>
    <br></br>
    <br></br>
    </div>
    </center>
    
</body>
</html>