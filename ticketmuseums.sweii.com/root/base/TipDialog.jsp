<%@page contentType="text/html;charset=GBK" language="java"%>

<%@include file="/frame/Init.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<sw:cssjs/>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
</head>
<body  style="background-color: #DFE6F8">
<form id="form2">
<table width="100%" cellpadding="2" cellspacing="0">
    <tr height="10">
      <td   ><input id="tip.id" type="hidden"></input><input id="tip.type" <%-- value="<%=request.getParameter("type") %>" --%> type="hidden"></input></td>
      <td></td>
    </tr>
    <tr height="25">
      <td align="right" style="width:30%">ģ�����ƣ�</td>
      <td><input type="text"  class="input1" id="tip.name" verify="ģ������|NotNull" tyle="width:120px"/></td>
    </tr>
    <tr height="25">
      <td align="right" >�������ƣ�</td>
      <td><input type="text"  class="input1" id="tip.funName" style="width:125px"  verify="��������|NotNull" /></td>
    </tr>
    <tr height="25">
      <td align="right" >���룺</td>
      <td><input type="text"  class="input1" id="tip.code"  tyle="width:120px" verify="����|NotNull" readOnly/></td>
    </tr>
    <tr height="25">
      <td align="right" >��ʾ��Ϣ��</td>
      <td><input type="text"  class="input1" id="tip.message" style="width:260px"  verify="NotNull" /></td>
    </tr>
    <tr><td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>��̬����:</b> [����],[��Ա����],[Ʊ������],[��Ʊ����],[����],[����]</td></tr>
</table>
</form>
</body>
</html>
