<%@page contentType="text/html;charset=GBK" language="java"%>
<%@include file="/frame/Init.jsp"%>
<sw:init id="dg1">
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>Ʊ�ֹ���</title>
 <link href="/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/frame/js/Main.js"></script>
<script type="text/javascript" src="/frame/datepicker/WdatePicker.js"></script>
   <sw:dialog method="add" width="450" height="250"  title="�½�Ʊ��" url="base/TicketDialog.jsp" action="common/addTicket.do"/> 
    <sw:dialog method="edit" width="450" height="250"  title="�޸�Ʊ��" url="base/TicketDialog.jsp" action="common/editTicket.do" fields="id,name,price,size"/> 
    <sw:confirm method="del" action="common/deleteTicket.do" confirm="��ȷ��Ҫɾ��ѡ���Ʊ����"  success="ɾ��Ʊ�ֳɹ���" failure="ɾ��Ʊ��ʧ�ܣ�"   multi="true"/>   
  <sw:search fields="ticket.name,ticket.type" method="search" />   

   <script>
        /*
         ������Ӧ�����ƣ���ѡ�����
         */
        $(function() {
            $(".provider").bind('click',function() {
                $("#dialog").dialog("open");
            });
            $( "#dialog" ).dialog({autoOpen:false});
        });
        $(function(){
            $(".ant").bind('click',function(){
                $(".provider").val($(this).text()) ;
            })
        });

        /*
         ������Ʒ���ƣ���ѡ�����
         */
        $(function() {
            $(".goods").bind('click',function() {
                $("#dialog2").dialog("open");
            });
            $( "#dialog2" ).dialog({autoOpen:false});
        });
        $(function(){
            $(".ant2").bind('click',function(){
                $(".goods").val($(this).text()) ;
            })
        });
    </script>
</head>
<body  style="background-color: #DFE6F8;">
<div class="box01" align="center"><span class="left">&nbsp;</span> <span class="tit" style="font-size:20px"><b>Ʊ�ֹ��� </b></span></div>

<table class="tools" style="width:100%">
        <tr>
            <td>
<sw:button src="/Icons/icon002a2.gif"  limit="true" onClick="add()">�½�</sw:button>
<sw:button src="/Icons/icon018a4.gif"  limit="true" onClick="edit()">�޸�</sw:button>
<sw:button src="/Icons/icon018a3.gif" limit="true"  onClick="del()">ɾ��</sw:button>
����:<input id="ticket.name" type="text" size="10"></input>
Ʊ����:<input id="ticket.type" type="text" size="10"></input>
<sw:button onClick="search()">��ѯ</sw:button>
</td>
        </tr>
    </table>
    
<sw:datagrid>       
           <table class="table" cellpadding="0" cellspacing="0" style="table-layout: fixed">
              <tr ztype="head" class="tr">
                <td width="34px" ztype="RowNo">&nbsp;<b>���</td>
                <td width="34px" ztype="selector" field="id">&nbsp;</td>
                <td width="100px">&nbsp;<b>����</b></td>
                <td width="60px">&nbsp;<b>Ʊ��</b></td>
                <td width="60px">&nbsp;<b>����</b></td>
            </tr>
            <tr>
                <td align="center">&nbsp;</td>
                <td >&nbsp;</td>
                <td>&nbsp;${name}</td>
                 <td>&nbsp;${price}</td>
                 <td>&nbsp;${size}</td>
            </tr>
            <tr ztype="pagebar" align="left" height="30px" style="background-color: #D3E1F1;">
                <td colspan="17" align="left" height="30px">${PageBar}</td>
            </tr>
        </table>
    </sw:datagrid>
</body>
</html>
</sw:init>
