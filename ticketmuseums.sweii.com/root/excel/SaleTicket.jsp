<%@page contentType="text/html;charset=GBK" language="java"%>
<%@include file="/frame/Init.jsp"%>
<sw:init id="dg1">
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>ɢ����Ʊ����</title>
 <link href="/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/frame/js/Main.js"></script>
<script type="text/javascript" src="/frame/datepicker/WdatePicker.js"></script>
   <sw:search fields="saleTicket.number" method="search" />   
</head>
<body  style="background-color: #DFE6F8;">
<div class="box01" align="center"><span class="left">&nbsp;</span> <span class="tit" style="font-size:20px"><b>ɢ����Ʊ���� </b></span></div>
<table class="tools" style="width:100%">
        <tr>
            <td>
<input id="saleTicket.number"  tip="����" style="width:120px" type="text"></input>
<sw:button onClick="search()">��ѯ</sw:button>
</td>
        </tr>
    </table>
    
<sw:datagrid multiSelect="false">       
           <table class="table" cellpadding="0" cellspacing="0" style="table-layout: fixed">
              <tr ztype="head" class="tr">
                <td width="34px" ztype="RowNo">&nbsp;<b>���</td>
                <td width="34px" ztype="selector" field="id">&nbsp;</td>
                <td width="100px">&nbsp;<b>����</b></td>
                <td width="100px">&nbsp;<b>Ʊ������</b></td>
                <td width="100px">&nbsp;<b>Ʊ��</b></td>
                 <td width="100px">&nbsp;<b>��Ʊ����</b></td>
                <td width="100px">&nbsp;<b>��Ч��������</b></td>
                <td width="100px">&nbsp;<b>ʹ��ʱ��</b></td>
                <td width="100px">&nbsp;<b>��ƱԱ</b></td>
                <td width="100px">&nbsp;<b>��Ʊʱ��</b></td>
            </tr>
            <tr>
                <td align="center">&nbsp;</td>
                <td >&nbsp;</td>
                <td>&nbsp;${number}</td>
                <td>&nbsp;${ticket.name}</td>
                <td>&nbsp;${price}</td>
                 <td>&nbsp;${saleDate}</td>
                 <td>&nbsp;${endTime}</td>
                 <td>&nbsp;${useTime}</td>
                 <td>&nbsp;${admin.name}</td>
                 <td>&nbsp;${createTime}</td>
            </tr>
            <tr ztype="pagebar" align="left" height="30px" style="background-color: #D3E1F1;">
                <td colspan="17" align="left" height="30px">${PageBar}</td>
            </tr>
        </table>
    </sw:datagrid>
</body>
</html>
</sw:init>
