<%@page contentType="text/html;charset=GBK" language="java"%>
<%@page import="java.util.*"%>
<%@page import="com.sweii.vo.*"%>
<%@include file="/frame/Init.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<link rel="stylesheet" type="text/css" href="/jquery/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/jquery/themes/icon.css">
<link type="text/css" rel="stylesheet" href="/frame/css/frame.css">
<link type="text/css" rel="stylesheet" href="/frame/css/ext-all.css">
 <link href="/style/style.css" rel="stylesheet" type="text/css" />
<script src="/frame/js/Main.js"></script>
<script type="text/javascript" src="/frame/datepicker/WdatePicker.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<style>
.day {
	background-color: #A2C5FF
}
</style>
<script>
function setting(obj,field){
    var value=1;
    if(!obj.checked){
        value=2;
    }
    var ids='1';
    var dc = new DataCollection();
    dc.add('ids',ids);
    dc.add('fields',field);
    dc.add('values',value);
    Server.sendRequest('common/changeSetting.do',dc,function(response){},'json');
}
function bakup(){
    Server.sendRequest('common/backup.do',"",function(response){
        Dialog.alert(response.message);
        },'json');
}
function edit(field,javaType){
	var dc = new DataCollection();
	dc.add("id","1");
	dc.add("entity","Setting");
	dc.add("field",field);
	dc.add("value",$V(field));
    if(javaType!=null){
    	dc.add("javaType",javaType);
    }
	Server.sendRequest("common/fieldEditEntity.do",dc,function(response){
           if(response.status==1){
               Dialog.alert("����ɹ���");
           }
  },'json');
}
function view(){
    location.href='common/querySettingBakDatabase.do?subHeight=100';
}
</script>
<%
    Setting setting = (Setting) request.getAttribute("sweii_entity");
%>
</head>
<body class="dialogBody">
<table width="1" border="0" cellspacing="0" cellpadding="0" style="border-collapse: separate; border-spacing: 6px; margin-left: 70px">
    <tr valign="top">
        <td width="2px">&nbsp;<br></br>
        </td>
        <td width="470px">
        <table>
            <tr>
                <td align="center">
                <fieldset><legend><label>���ݿⱸ��</label></legend>
                <table width="450px" cellpadding="0" cellspacing="0">
                    <tr height="25">
                        <td align="left" width="50%"><input type="checkbox" onclick="setting(this,'autoBak')" <%if (setting.getAutoBak().intValue() == 1) {
				out.print("checked");
			}%>></input>�Ƿ��Զ��������ݿ�</td>
                    </tr>
                    <tr height="25">
                        <td align="left" width="50%">
                        <table>
                            <tr>
                                <td>���ݱ���·��:<input id="bakPath" type="text" value="<%=setting.getBakPath()%>" style="width: 250px"></input></td>
                                <td><sw:button src="/Icons/icon403a2.gif" onClick="edit('bakPath')">����</sw:button></td>
                            </tr>
                        </table>
                        </td>
                    </tr>
                    <tr height="25">
                        <td align="left" width="50%">
                        <table>
                            <tr>
                                <td>ÿ�챸��ʱ��1:<input id="time1" type="text" value="<% if(setting.getTime1()!=null){out.print(setting.getTime1());} %>" ztype="date" format="HH:mm" style="width: 60px"></input></td>
                                <td><sw:button src="/Icons/icon403a2.gif" onClick="edit('time1')">����</sw:button></td>
                            </tr>
                        </table>
                        </td>
                    </tr>
                    <tr height="25">
                        <td align="left" width="50%">
                        <table>
                            <tr>
                                <td>ÿ�챸��ʱ��2:<input id="time2" type="text" value="<% if(setting.getTime2()!=null){out.print(setting.getTime2());} %>" ztype="date" format="HH:mm" style="width: 60px"></input></td>
                                <td><sw:button src="/Icons/icon403a2.gif" onClick="edit('time2')">����</sw:button></td>
                            </tr>
                        </table>
                        </td>
                    </tr>
                    <tr height="25">
                        <td align="left" width="50%">
                        <table>
                            <tr>
                                <td>ÿ�챸��ʱ��3:<input id="time3" type="text" value="<% if(setting.getTime3()!=null){out.print(setting.getTime3());} %>" ztype="date" format="HH:mm" style="width: 60px"></input></td>
                                <td><sw:button src="/Icons/icon403a2.gif" onClick="edit('time3')">����</sw:button></td>
                            </tr>
                        </table>
                        </td>
                    </tr>
                    <tr height="25">
                        <td align="left" width="50%">
                        <table>
                            <tr>
                                <td>ÿ�챸��ʱ��4:<input id="time4" type="text" value="<% if(setting.getTime4()!=null){out.print(setting.getTime4());} %>" ztype="date" format="HH:mm" style="width: 60px"></input></td>
                                <td><sw:button src="/Icons/icon403a2.gif" onClick="edit('time4')">����</sw:button></td>
                            </tr>
                        </table>
                        </td>
                    </tr>
                    <tr height="25">
                        <td align="left" width="50%">
                        <table>
                            <tr>
                                <td>ÿ�챸��ʱ��5:<input id="time5" type="text" value="<% if(setting.getTime5()!=null){out.print(setting.getTime5());} %>" ztype="date" format="HH:mm" style="width: 60px"></input></td>
                                <td><sw:button src="/Icons/icon403a2.gif" onClick="edit('time5')">����</sw:button></td>
                            </tr>
                        </table>
                        </td>
                    </tr>
                    <tr height="25">
                        <td align="left" width="50%">
                        <table>
                            <tr>
                                <td>ÿ�챸��ʱ��6:<input id="time6" type="text" value="<% if(setting.getTime6()!=null){out.print(setting.getTime6());} %>" ztype="date" format="HH:mm" style="width: 60px"></input></td>
                                <td><sw:button src="/Icons/icon403a2.gif" onClick="edit('time6')">����</sw:button></td>
                            </tr>
                        </table>
                        </td>
                    </tr>
                    <tr height="25">
                        <td align="left" width="50%">
                        <table>
                            <tr>
                                <td>ÿ�챸��ʱ��7:<input id="time7" type="text" value="<% if(setting.getTime7()!=null){out.print(setting.getTime7());} %>" ztype="date" format="HH:mm" style="width: 60px"></input></td>
                                <td><sw:button src="/Icons/icon403a2.gif" onClick="edit('time7')">����</sw:button></td>
                            </tr>
                        </table>
                        </td>
                    </tr>
                    <tr height="25">
                        <td align="left" width="50%">
                        <table>
                            <tr>
                                <td>ÿ�챸��ʱ��8:<input id="time8" type="text" value="<% if(setting.getTime8()!=null){out.print(setting.getTime8());} %>" ztype="date" format="HH:mm" style="width: 60px"></input></td>
                                <td><sw:button src="/Icons/icon403a2.gif" onClick="edit('time8')">����</sw:button></td>
                            </tr>
                        </table>
                        </td>
                    </tr>
                    <tr height="25">
                        <td align="left" width="50%">
                        <table>
                            <tr>
                                <td>ÿ�챸��ʱ��9:<input id="time9" type="text" value="<% if(setting.getTime9()!=null){out.print(setting.getTime9());} %>" ztype="date" format="HH:mm" style="width: 60px"></input></td>
                                <td><sw:button src="/Icons/icon403a2.gif" onClick="edit('time9')">����</sw:button></td>
                            </tr>
                        </table>
                        </td>
                    </tr>
                    <tr height="25">
                        <td align="left" width="50%">
                        <table>
                            <tr>
                                <td>ÿ�챸��ʱ��10:<input id="time10" type="text" value="<% if(setting.getTime10()!=null){out.print(setting.getTime10());} %>" ztype="date" format="HH:mm" style="width: 60px"></input></td>
                                <td><sw:button src="/Icons/icon403a2.gif" onClick="edit('time10')">����</sw:button></td>
                            </tr>
                        </table>
                        </td>
                    </tr>
                    <tr height="5">
                        <td>&nbsp;</td>
                    </tr>
                    <tr height="25">
                        <td align="left">
                        <table align="center">
                            <tr>
                                <td><sw:button src="/Icons/icon005a2.gif" onClick="bakup()">�����ֶ�����һ��</sw:button></td>
                                <td>&nbsp;&nbsp;&nbsp;</td>
                                <td><sw:button src="/Icons/icon005a1.gif" onClick="view()">�鿴�ѱ������ݿ�</sw:button></td>
                            </tr>
                        </table>
                        </td>
                    </tr>
                </table>
                </fieldset>
                </td>
            </tr>
        </table>
        </td>
    </tr>
</table>
</body>
</html>
