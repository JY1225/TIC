<%@page contentType="text/html;charset=GBK" language="java"%>
<%@include file="/frame/Init.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<sw:cssjs/>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
</head>
<body class="dialogBody">
<form id="form2">
<table width="100%" cellpadding="2" cellspacing="0">
	<tr>
      <td width="30%" height="10" align="right" ></td>
      <td height="10"> <input id="returnTicket.id" type="hidden"/></td>
    </tr>
    <tr height="25">
      <td align="right" >��Աid��</td>
      <td><input   type="text" class="input1" id="returnTicket.userId"  verify="����|NotNull"/></td>
    </tr>
    <tr height="25">
      <td align="right" >Ʊ��id��</td>
      <td>
      	<select class="input1" id="returnTicket.ticketId">
      	<option>��ѡ��</option>
      	<option value="1">��Ա��</option>
      	<option value="2">��Ʊ��</option>
      	<option value="3">��ѵ��</option>
      	<option value="4">Ա����</option>
      	<option value="5">��ʱ��</option>
    	  </select>
      </td>
    </tr>
    <tr height="25">
      <td align="right" >���ţ�</td>
      <td><input   type="text" class="input1" id="returnTicket.number" verify="����|NotNull"/></td>
    </tr>
    <tr height="25">
      <td align="right" >�ɿ��ţ�</td>
      <td><input   type="text" class="input1" id="returnTicket.oldNumber" verify="����|NotNull"/></td>
    </tr>
    <tr height="25">
      <td align="right" >���������ѣ�</td>
      <td><input   type="text" class="input1" id="returnTicket.price" /></td>
    </tr>
    <tr height="25">
      <td align="right" >����Ѻ��</td>
      <td><input   type="text" class="input1" id="returnTicket.prePrice" verify="����|NotNull"/></td>
    </tr>
    <tr height="25">
      <td align="right" >�����ˣ�</td>
      <td><input   type="text" class="input1" id="returnTicket.creator" verify="����|NotNull"/></td>
    </tr>
    
    <tr height="25">
      <td align="right" >����ʱ�䣺</td>
      <td><input  type="text" ztype="date" format="yyyy-MM-dd HH:mm:ss"  class="input1 inputText" size="18" id="returnTicket.createTime" /></td>
    </tr>
    <tr height="25">
      <td align="right" >�������ڣ�</td>
      <td><input   type="text" ztype="date" format="yyyy-MM-dd HH:mm:ss"  class="input1 inputText" size="18" id="returnTicket.mendTime" /></td>
    </tr>
    
  
</table>
</form>
</body>
</html>