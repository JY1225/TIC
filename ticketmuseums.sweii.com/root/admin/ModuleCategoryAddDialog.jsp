<%@page contentType="text/html;charset=GBK" language="java"%>

<%@include file="/frame/Init.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<link href="/frame/css/frame.css" rel="stylesheet" type="text/css" />
<script src="/frame/js/Main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
</head>
<body class="dialogBody">
<form id="form2">
<table width="100%" cellpadding="2" cellspacing="0">
    <tr>
      <td  height="10" ><input id="moduleCategory.id" type="hidden"></input><input id="moduleCategory.orderIndex" value="1" type="hidden"></input></td>
      <td></td>
    </tr>
    <tr>
      <td align="right" >�������ƣ�</td>
      <td><input name="moduleCategory.name"  type="text"  class="input1" id="moduleCategory.name" verify="��������|NotNull" /></td>
    </tr>
    <tr>
      <td align="right" >����������</td>
      <td><input name="moduleCategory.description" type="text"  class="input1" id="moduleCategory.description"/></td>
    </tr>
    <tr>
      <td height="10" colspan="2" align="center"></td>
    </tr>
</table>
</form>
</body>
</html>
