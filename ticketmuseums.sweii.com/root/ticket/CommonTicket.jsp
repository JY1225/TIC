<%@page contentType="text/html;charset=GBK" language="java"%>
<%@include file="/frame/Init.jsp"%>
<!DOCTYPE html> 
<% 
List<Ticket> tickets = (List<Ticket>) request.getAttribute("tickets");
AdminStatBean bean = (AdminStatBean) request.getSession().getAttribute("adminStat");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>ɢ��</title>
<link href="/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/frame/js/Main.js"></script>
<script type="text/javascript" src="/js/Hash.js"></script>
<script type="text/javascript" src="/frame/js/Cookies.js"></script>
<script type="text/javascript" src="/js/CommonWaterTicket.js"></script>
<script language="javascript" src="/lodop/LodopFuncs.js"></script>
<script> parent.saleType=1;</script>
<script> saleType=1;</script>
<script>
function selectTicket(tr,index){
	/*
    var size=parseInt(tr.size);
    for(var i=0;i<size;i++){
        $('rows'+i).style.backgroundColor="#FFFFFF";
        $('rows'+i).style.fontWeight='';
        $('check'+i).checked=false;
    }
    $('rows'+index).style.backgroundColor="#61A4E4";
    $('rows'+index).style.fontWeight='bold';
    $('check'+index).checked=true;
    */
}
</script>
</head>
<body style="background-color: #DFE6F8">

<object id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
	<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0 pluginspage="install_lodop.exe"></embed>
</object> 
<div class="box01" align="center"><span class="left">&nbsp;</span> <span class="tit" style="font-size: 20px"><b><% if(request.getParameter("category").equals("2")){out.print("ɢ����Ʊ");}else{out.print("������Ʊ");} %>����</b></span></div>
        <table width="100%" style="table-layout: fixed;">
            <tr>
                <td width="100%" valign="top" >
                <div class="box03" >
           
                <table  border="0" cellpadding="0" cellspacing="0"  style="table-layout: fixed;">
    <tr class="trborder tools"  height="40px"    style="background-color: #E3EAF4">

        <td class="trborder" style="border-top:1px solid #21629c;border-left:1px solid #21629c; " width="2%">&nbsp;</td>
        <% if(request.getParameter("category")!=null&&request.getParameter("category").equals("1")){ %>
        <td class="trborder" style="border-top:1px solid #21629c; " width="16%" align="center" style="font-size:20px"><b>Ʊ������</b></td>
        <%}else{ %>
        <td class="trborder" style="border-top:1px solid #21629c; " width="18%" align="center" style="font-size:20px"><b>Ʊ������</b></td>
        <%} %>
        <td class="trborder" style="border-top:1px solid #21629c; " width="6%" align="center" style="font-size:20px"><b>Ʊ��</b></td>
        <td class="trborder" style="border-top:1px solid #21629c; " width="6%" align="center" style="font-size:20px"><b>Ѻ��</b></td>
        <td class="trborder" style="border-top:1px solid #21629c; " width="6%" align="center" style="font-size:20px"><b>����</b></td>
        <td class="trborder" style="border-top:1px solid #21629c; " width="6%" align="center" style="font-size:20px"><b>���</b></td>
    </tr>
    <% for(int i=0;i<tickets.size();i++){
	Ticket ticket=tickets.get(i);
    %>
    <tr  height="40px" id="rows<%=i%>" ticketId="<%=ticket.getId() %>" size="<%=tickets.size() %>" onClick="selectTicket(this,<%=i %>);" style="cursor:hand;background-color: #FFFFFF">
        <td  class="trborder" style="border-left:1px solid #21629c; "><input onFocus='this.blur();' id="check<%=i %>" type='checkbox'  ></td>
        <td class="trborder" align="center" style="font-size:20px" id="ticketName<%=i %>"><%=ticket.getName() %></td>
        <td class="trborder" align="center" style="font-size:20px" id="price<%=i %>"><%=ticket.getPrice() %></td>
        <td class="trborder" align="center" style="font-size:20px" id="prePrice<%=i %>"><%=ticket.getPrePrice() %></td>
        <td class="trborder" align="center" style="font-size:20px"><input  id="size<%=i %>" type="text" value="0" onBlur="addTicket(this,<%=i %>)" style="width:50px;font-size:20px;text-align:center"></input></td>
        <td class="trborder" align="center" style="font-size:20px" id="amount<%=i %>">0</td>
    </tr>
    <%} %>
    
</table>
</div>
</td>
</tr>
</table>
<table width="1000px" style="font-size:16px">
<tr><td align="left"><fieldset style="height:265px">
    <legend ><label><b>ͳ����Ϣ</b></label></legend>
    <table width="100%"><tr><td align="right"  width="45%">ɢ�ͽ���ۼ�:</td><td><input id="commonSize" type="text" value="<%=bean.getCommonSize() %>" style="width:140px;font-weight:bold;color:red" readonly></input>(Ԫ)</td></tr>
    <tr><td align="right">��Ʊ����ۼ�:</td><td><input type="text" style="width:140px;font-weight:bold;color:red" value="<%=bean.getTeamSize() %>" readonly></input>(Ԫ)</td></tr>
    <tr><td align="right">�����Ա����ۼ�:</td><td><input type="text" style="width:140px;font-weight:bold;color:red" value="<%=bean.getUserSize() %>" readonly></input>(Ԫ)</td></tr>
    <tr><td align="right">��Ա���ѽ���ۼ�:</td><td><input type="text" style="width:140px;font-weight:bold;color:red" value="<%=bean.getExtendSize() %>" readonly></input>(Ԫ)</td></tr>
    <tr><td align="right">��Ա��������ۼ�:</td><td><input type="text" style="width:140px;font-weight:bold;color:red" value="<%=bean.getRepairSize() %>" readonly></input>(Ԫ)</td></tr>
    <tr><td align="right" style="color:red">��Ʊ����ۼ�:</td><td><input type="text" style="width:140px;font-weight:bold;color:red" value="<% if(bean.getReturnPrice()>0){out.print("-"+bean.getReturnPrice());}else{out.print("0");} %>" readonly></input>(Ԫ)</td></tr>
    <tr><td align="right">�����ܼ�:</td><td><input id="totalSize" type="text" style="width:140px;font-weight:bold;color:red" value="<%=bean.getTotal() %>" readonly></input>(Ԫ)</td></tr>
    <tr><td align="right">IC��Ѻ���ۼ�:</td><td><input id="preSize" type="text" style="width:140px;font-weight:bold;color:red" value="<%=bean.getPreSize() %>" readonly></input>(Ԫ)</td></tr>
    <tr><td align="right" style="color:red">��IC��Ѻ���ۼ�:</td><td><input type="text" style="width:140px;font-weight:bold;color:red" value="<%if(bean.getReturnPrePrice()>0){out.print("-"+bean.getReturnPrePrice());}else{out.print("0");}  %>" readonly></input>(Ԫ)</td></tr>
    </table>
    </fieldset>
                
                </td>
                <td>&nbsp;&nbsp;&nbsp;</td>                <td><table  width="100%"   style="font-size:16px"><tr valign="top" height="30%" ><td  width="100%" >
        
        <fieldset  style="height:220px">
    <legend ><label><b>�տ���Ϣ</b></label></legend>
      <table width="100%">
      <tr><td align="right"  width="40%">IC��Ѻ��:</td><td><input id="prePriceTotal" type="text" style="width:140px;font-weight:bold;color:red" readonly></input>(Ԫ)</td></tr>
    <tr><td align="right">Ʊ�۽��:</td><td><input id="priceTotal"  type="text" style="width:140px;font-weight:bold;color:red" value="" readonly></input>(Ԫ)</td></tr>
    <tr><td align="right">Ӧ�ս��:</td><td><input id="shouldTotal"  type="text" style="width:140px;font-weight:bold;color:red" value="" readonly></input>(Ԫ)</td></tr>
    <tr><td align="right">�տ���:</td><td><input id="total" onBlur="checkTotal();"  onpropertychange="checkTotal();" type="text" style="width:140px;font-weight:bold;color:red"></input>(Ԫ)</td></tr>
    <tr><td align="right">Ӧ�ҽ��:</td><td><input id="returnTotal" type="text" style="width:140px;font-weight:bold;color:red" value="" readonly></input>(Ԫ)</td></tr>
    <tr><td align="right">����Ʊ��:</td><td><input id="sizeTotal"  type="text" style="width:140px;font-weight:bold;color:red" value="" readonly></input>(��)</td></tr>
    </table>
    </fieldset>
        </td></tr>
        
        <tr><td >
        <span  id="submitTicket" class="da-button gray large"  onclick='sumbitTicket()'  style='font-weight:bold;font-size:25px' disabled="true">
                                        	��Ʊ
                                        </span>&nbsp;
                                        <span id="clearButton"   onclick='clearTicket();' class="da-button gray large" style='font-weight:bold;font-size:25px' disabled="true">
                                        	���
                                        </span>
                                        <span style='font-size:12px;'><input id="printFlag" onclick="setPrintFlag();" type="checkbox"></input>���ӡ</span>
        </td>
        </tr>
        
        </table></td>
        
        <td>
        <fieldset style="height:280px">
    <legend ><label><b>��Ʊ��Ϣ</b></label></legend>
      <textarea id="ticketInfo" rows="14" cols="34" readonly style="font-size:16px;font-weight:bold"></textarea>
    </fieldset>
        </td>
        </tr>
</table>
<!-- 
<script>
 if(parent.comPort.length>0){
     Dialog.alert("�޷��򿪴���"+parent.comPort);
 }
</script>
 -->
</body>
</html>