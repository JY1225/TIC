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
      <td   ><input id="equipment.id" type="hidden"></input></td>
      <td></td>
    </tr>
    <tr height="25">
      <td align="right" style="width:30%">�豸���ƣ�</td>
      <td><input type="text"  class="input1" id="equipment.name" verify="�豸����|NotNull" /></td>
    </tr>
 
    <tr height="25">
      <td align="right" >IP��ַ:</td>
      <td><input type="text"  class="input1" id="equipment.ip" style="width:120px"  verify="IP��ַ|NotNull" /></td>
    </tr>
    <tr height="25">
      <td align="right" >�˿�:</td>
      <td><input type="text"  class="input1" id="equipment.port" style="width:60px"  verify="�˿�|NotNull&&gInt" /></td>
    </tr>
     <tr height="10">
      <td   ><input id="equipment.createTime" type="hidden"></input></td>
      <td></td>
    </tr>
</table>
</form>
</body>
</html>
