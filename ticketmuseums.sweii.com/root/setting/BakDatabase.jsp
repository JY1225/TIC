<%@page contentType="text/html;charset=GBK" language="java"%>
<%@include file="/frame/Init.jsp"%>
<sw:init id="dg1">
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>�û�</title>
 <link href="/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/frame/js/Main.js"></script>
    <sw:search fields="bakDatabase.fileName" method="search" />
    </head>
    <body>

    <div class="box01" align="center"><span class="left">&nbsp;</span> <span class="tit" style="font-size:20px"><b>���ݼ�¼</b></span></div>
<table class="tools" style="width:100%">
    <tr>
       <td><input type="text" id="bakDatabase.fileName" tip="�ļ���" style="width: 100px" /><sw:button src="/Icons/icon403a3.gif" onClick="search()">����</sw:button></td> 
    </tr>
</table>
<sw:datagrid multiSelect="false" height="117"  >       
        <table class="table" cellpadding="0" cellspacing="0" style="table-layout: fixed">
            <tr ztype="head" class="tr">
                <td width="35px" ztype="RowNo">���</td>
                <td width="30px" ztype="selector" field="id">&nbsp;</td>
                <td width="100">�����ļ���</td>
                <td width="60">�����ļ���С</td> 
                <td width="60">����Ŀ¼</td>
                <td width="60">����ʱ��</td>
                <td width="80">��������</td>
            </tr>
            <tr >
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>${fileName}</td>
                <td>${fileSize}</td>
                <td>${path}</td>
                <td>${bakTime}</td>
                <td>${type}</td>    
            </tr>
            <tr ztype="pagebar" align="left" height="30px" style="background-color: #D3E1F1;">
                <td colspan="7" align="left" height="30px">${PageBar}</td>
            </tr>
        </table>
    </sw:datagrid>

    </body>
    </html>
</sw:init>
