<%@page contentType="text/html;charset=GBK" language="java"%>
<%@include file="/frame/Init.jsp"%>
<sw:init id="dg1">
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>���Ź���</title>
 <link href="/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/frame/js/Main.js"></script>
<script type="text/javascript" src="/frame/datepicker/WdatePicker.js"></script>
<script type="text/javascript" >
function importCardInfo(){
	var diag = new Dialog('Dialog_add');
	diag.Width = 450;
	diag.Height = 150;
	diag.Title = '���뿨��';
	diag.URL = 'cardInfo/CardInfoDialog.jsp';
	var args=arguments;
	diag.onLoad = function(){
	};
	diag.OKEvent = importCardSave;
	diag.show();
}

function importCardSave(){//�����½���������
	if($DW.Verify.hasError()){
		return;
	}
	$DW.$('form2').submit();
}

function closeDialog(size){
	   setTimeout('addAlert('+size+')',50);
	}
	function addAlert(size){
		Dialog.alert("���뿨�ųɹ���һ������"+size+"�ſ��š�",function(){
		DataGrid.loadData("dg1");
	});
	}

</script>
</head>
<body  style="background-color: #DFE6F8;">
<div class="box01" align="center"><span class="left">&nbsp;</span> <span class="tit" style="font-size:20px"><b>δ��ͨ���Ź��� </b></span></div>
<table class="tools" style="width:100%">
        <tr>
            <td>
<sw:button src="/Icons/icon002a2.gif"  limit="true" onClick="importCardInfo()">���뿨��</sw:button>
</td>
        </tr>
    </table>
    
<sw:datagrid>       
           <table class="table" cellpadding="0" cellspacing="0" style="table-layout: fixed">
              <tr ztype="head" class="tr">
                <td width="34px" ztype="RowNo">&nbsp;<b>���</td>
                <td width="34px" ztype="selector" field="id">&nbsp;</td>
                <td width="100px">&nbsp;<b>����</b></td>
                <td width="60px">&nbsp;<b>״̬</b></td>
                <td width="60px">&nbsp;<b>����ʱ��</b></td>
            </tr>
            <tr>
                <td align="center">&nbsp;</td>
                <td >&nbsp;</td>
                <td>&nbsp;${number}</td>
                 <td>&nbsp;${status}</td>
                 <td>&nbsp;${createTime}</td>
            </tr>
            <tr ztype="pagebar" align="left" height="30px" style="background-color: #D3E1F1;">
                <td colspan="17" align="left" height="30px">${PageBar}</td>
            </tr>
        </table>
    </sw:datagrid>
</body>
</html>
</sw:init>
