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
      <td height="10"> <input id="user.id" type="hidden"/></td>
    </tr>
    <tr height="25">
      <td align="right" >������</td>
      <td><input   type="text" class="input1" id="user.name"  verify="����|NotNull"/></td>
    </tr>
    <tr height="25">
      <td align="right" >���ţ�</td>
      <td><input   type="text" class="input1" id="user.number" verify="����|NotNull"/></td>
    </tr>
    <tr height="25">
      <td align="right" >�Ա�</td>
      <td><input   type="text" class="input1" id="user.sex" /></td>
    </tr>
    <tr height="25">
      <td align="right" >��Ʊ�۸�</td>
      <td><input   type="text" class="input1" id="user.price" verify="����|NotNull"/></td>
    </tr>
    <tr height="25">
      <td align="right" >��ϵ�绰��</td>
      <td><input   type="text" class="input1" id="user.phone" verify="����|NotNull"/></td>
    </tr>
    <tr height="25">
      <td align="right" >֤���ţ�</td>
      <td><input   type="text" class="input1" id="user.personNo" verify="����|NotNull"/></td>
    </tr>
    <tr height="25">
      <td align="right" >��ʼʱ�䣺</td>
      <td><input  type="text" ztype="date" format="yyyy-MM-dd HH:mm:ss"  class="input1 inputText" size="18" id="user.startTime" /></td>
    </tr>
    <tr height="25">
      <td align="right" >����ʱ�䣺</td>
      <td><input   type="text" ztype="date" format="yyyy-MM-dd HH:mm:ss"  class="input1 inputText" size="18" id="user.endTime" /></td>
    </tr>
    <tr height="25">
      <td align="right" style="width:30%">Ʊ�֣�</td>
      <td>
      <sw:select id="user.ticketId" hql="from Code where codeType ='ticketType' order by codeType asc" fields="id,codeName" style="width:130px">
      </sw:select>
      </td>
    </tr>
    
    <tr height="25">
      <td align="right" >ʹ�ô�����</td>
      <td><input   type="text" class="input1" id="user.times" /></td>
    </tr>
    <tr height="25">
      <td align="right" >��ע��</td>
      <td><input   type="text" class="input1" id="user.remark" /></td>
    </tr>
  <!--   <tr height="25">
      <td align="right" >ʱ�䣺</td>
      <td><input id="user.createTime"  type="text" ztype="date" format="yyyy-MM-dd HH:mm:ss"  class="input1 inputText" size="18"></td>
    </tr> -->
</table>
</form>
</body>
</html>