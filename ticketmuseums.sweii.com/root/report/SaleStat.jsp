<%@page contentType="text/html;charset=GBK" language="java"%>
<%@include file="/frame/Init.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>��Ʊͳ�Ʊ�</title>
<link href="/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/frame/js/Main.js"></script>
<script type="text/javascript" src="/frame/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="/dtree/dtree.js"></script>
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
</style>
</head>
<body style="background-color: #DFE6F8;">
<jsp:include page="/frame/Printer.jsp" />
<div class="box01" align="center"><span class="left">&nbsp;</span> <span class="tit" style="font-size: 20px"><b>��Ʊͳ�Ʊ�</b></span></div>

<center>
<div style="height:600px;overflow-y:scroll;overflow-x:hidden;overflow:scroll;">
<table width="800px" style="font-size:12px">
    <tr>
        <td width="100%">
        <fieldset><legend><label>&nbsp;&nbsp;<b style="font-size: 14px">��ѯ����</b></label></legend>
        <table>
            <tr>
                <td style="font-size: 12px">��ʼʱ��:</td>
                <td><input id="startTime" type="text" style="width: 150px" value="2012-11-06 00:00:00" ztype="date" format="yyyy-MM-dd HH:mm:ss" /></td>
                <td style="font-size: 12px">����ʱ��:</td>
                <td><input id="endTime" type="text" style="width: 150px" value="2012-11-06 23:59:59" ztype="date" format="yyyy-MM-dd HH:mm:ss" /></td>
            </tr>
            <tr>
                <td style="font-size: 12px" colspan="2">
                <fieldset style="width:200px"><legend><label>&nbsp;&nbsp;<b style="font-size: 12px">ѡ����ƱԱ</b></label></legend>
                     <div style="height:150px;width:200px;overFlow-y: scroll; overFlow-x: hidden">
                     
                      
                     <script type="text/javascript">
                        <!--
                        d = new dTree('seller');
                        d.check=true;
                        d.add(0,-1,'ȫ����ƱԱ');
                        d.add(1,0,'����(���)','');
                        d.add(2,0,'����(0001)','');
                        d.add(3,0,'����(0002)','');
                        d.add(4,0,'С��(0003)','');
                        d.add(5,0,'����(0004)','');
                        d.add(6,0,'����(0005)','');
                        d.add(7,0,'����','');
                        d.add(8,0,'С��','');
                        d.add(9,0,'����','');
                        d.add(10,0,'����','');
                        d.add(11,0,'����','');
                        d.add(12,0,'С��','');
                        document.write(d);
                        var ids="1,2,3,4,5,6,7,8";//
                        //-->
                </script>
    </div>
    <script>         //��ѯǰ���÷�����ѡ��ID�б�
                     function getSellerCheckedIds(){
                         var idss=ids.split(",");
                         var checkIds="";
                         for(var i=0;i<idss.length;i++){
                             if(document.getElementById('cseller'+idss[i]).checked){
                                 checkIds+=idss[i]+",";
                             }
                         }
                         alert(checkIds);
                     }
                     //��ʼ��ѡ����ƱԱ,
                     function initCheckSeller(){
                         
                     }
                     </script>
                     </fieldset>
                </td>
                <td style="font-size: 12px" colspan="2">
                  <fieldset style="width:260px"><legend><label>&nbsp;&nbsp;<b style="font-size: 12px">ѡ��Ʊ��</b></label></legend>
                     <div style="height:150px;width:260px;overFlow-y: scroll; overFlow-x: hidden">
                     <script type="text/javascript">
                        <!--
                        var ticket = new dTree('ticket');
                        ticket.check=true;
                        ticket.add(0,-1,'½�ع�԰');
                        ticket.add(-11,0,'ɢ��','');
                        ticket.add(-12,0,'����','');
                        ticket.add(-13,0,'��Ա','');
                        ticket.add(-14,0,'��Ʊ','');
                        ticket.add(5,1,'½-���ֿ�','');
                        ticket.add(6,1,'½-�λÿ�','');
                        ticket.add(7,2,'Сѧ��-�λÿ�','');
                        ticket.add(8,2,'��ѧ��-�λÿ�','');
                        ticket.add(9,2,'��ѧ��-�λÿ�','');
                        ticket.add(10,2,'ֱ����λ-�λÿ�','');
                        ticket.add(11,2,'������-�λÿ�','');
                        ticket.add(12,3,'½�ع�԰-����vip','');
                        ticket.add(12,3,'½�ع�԰-����vip','')
                        ticket.add(12,3,'½�ع�԰-��ͥvip','')
                        ticket.add(12,4,'½-�λ�-��Ʊ','')
                        ticket.add(30,-1,'ˮ��԰');
                        ticket.add(31,30,'ɢ��','');
                        ticket.add(32,30,'����','');
                        ticket.add(33,30,'��Ա','');
                        ticket.add(44,30,'��Ʊ','');
                        document.write(ticket);
                        var ticketIds="1,2,3,4,5,6,7,8";
                        //-->
                </script>
    </div>
    <script>
                     //��ѯǰ���÷�����ѡ��ID�б�
                     function getTicketCheckedIds(){
                         var idss=ticketIds.split(",");
                         var checkIds="";
                         for(var i=0;i<idss.length;i++){
                             if(document.getElementById('cticket'+idss[i]).checked){
                                 checkIds+=idss[i]+",";
                             }
                         }
                         alert(checkIds);
                     }
                     </script>
                     </fieldset>                  
                </td>
            </tr>
            <tr><td>��ƱԱ���:</td><td><input id="adminName" type="text" style="width: 150px" /></td><td><input type="button" value="��ѯ"></input></td></tr>
        </table>
        </fieldset>
        </td>
    </tr>
</table>
    <table width="1000px" border="0" cellspacing="0"  >
        <tr>
            <td width="15%" style="font-weight: bold;" align="left"></td>
            <td align="center" width="50%" style="font-weight: bold; font-size: 25px">��Ʊͳ�Ʊ�</td>
            <td width="20%" style="font-weight: bold;font-size:14px" align="right"><a href="javascript:void(0);" onclick="printHA4('print');" class="printBtn"><img src="/Icons/icon013a1.gif" />��ӡ</a></td>
        </tr>       
        <tr>
            <td colspan="4" align="center">
            <span id="print">
            <table width="98%" border="0" cellspacing="0" class="table-border" style="height: 25px; text-align: left">
                <tr height="25px" style="font-weight: bold;font-size:12px;background-color: white">
                    <td align="center">��԰����</td>
                    <td align="center">�ͻ�����</td>                   
                    <td align="center">Ʊ��</td>
                     <td align="center">��Ʊ����</td>
                    <td align="center">��Ʊ���</td>
                    <td align="center">��Ʊ����</td>
                    <td align="center">��Ʊ���</td>
                    <td align="center">�˿�����</td>
                    <td align="center">��Ѻ����</td>
                    <td align="center">��������</td>
                    <td align="center">���ѽ��</td>
                    <td align="center">��������</td>
                    <td align="center">�������</td>
                    <td align="center">Ѻ����</td>
                    <td align="center">ʵ�ս��</td>
                </tr>
                <%for(int i=0;i<12;i++){ %>
                 <tr height="25px" style="font-size:12px;background-color: white">
                    <% if(i==0) {%>
                       <td align="center"   rowspan="12">½��԰</td>
                       <td align="center"  rowspan="2">ɢ��</td>  
                       
                    <%} %>
                    <% if(i==2){ %>
                     <td align="center"  rowspan="5">����</td>  
                    <%} %>
                    <% if(i==7){ %>
                     <td align="center"  rowspan="3">��Ա</td>  
                    <%} %>
                    <% if(i==10){ %>
                     <td align="center"  rowspan="1">��Ʊ</td>  
                    <%} %>
                    <% if(i==11){ %>
                     <td align="center" colspan="2" style='color:blue;font-weight:bold'>С��:</td>  
                    
                    <%}else{ %>
                     <td align="center" >Ʊ��<%=i %></td>
                    <%} %>
                     <td align="center">0</td>
                    <td align="center">0</td>
                    <td align="center">0</td>
                    <td align="center">0</td>
                    <td align="center">0</td>
                    <td align="center">0</td>
                    <td align="center">0</td>
                    <td align="center">0</td>
                    <td align="center">0</td>
                    <td align="center">0</td>
                    <td align="center">0</td>
                    <td align="center">0</td>
                </tr>
                <%} %>
            </table>
            </span>
            </td>
        </tr>
    </table>
    </div>
    </center>
    
</body>
</html>