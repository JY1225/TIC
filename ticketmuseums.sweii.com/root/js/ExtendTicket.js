function extendTicket(user) {
	var info = $('extendTicket');
	if (info == null) {// ������
		contentFrame.window.extend(user);
	} else {
		extend(user);
	}
}
function extend(user){
	$S('id',user.id);
    $S('name',user.name);
    $S('number',user.number);
    $S('sex',user.sex);
    $S('phone',user.phone);
    $S('personNo',user.personNo);
    $S('endTime',user.endTime);
    $S('price',user.price);
    $S('ticketName',user.ticketName);
    $S('saleTime',user.createTime);
    $S('extendPrice',user.extendPrice);
    $S('nextEndTime',user.nextEndTime);
    $S('total',user.extendPrice);
    $S('returnTotal',"0");
    if(user.photo.length>0){
    	var img="<img border='0' width='120' height='160' src='"+user.photo+"'/>"
    	$('photo').innerHTML=img;
    }
    setAbled('submitButton');
    setAbled('clearButton');
}

function sumbitExtendTicket(){
	var total=$V('total');
	var total=parseInt(total);
	var extendPrice=parseInt($V("extendPrice"));
	if(total<extendPrice){
		Dialog.alert("�տ���������Ӧ�ս��!",function(){
			$('total').focus();
			var r = $('total').createTextRange(); 
			r.moveStart('character', $('total').value.length); 
			r.collapse(false); 
			r.select();
			
		
		});
		return;
	}
	var dc=new DataCollection();
	dc.add('id',$V('id'));
	dc.add('price',$V('extendPrice'));
	Server.sendRequest("ticket/extendTicket.do",dc,function(response){
		if(response.status==1){//��Ч
			Dialog.alert(response.message,function(){
				clearExtendTicket();
			});
		}else{//��Ч
			Dialog.alert(response.message);
		}
	},'json');
}
function clearExtendTicket(){
	$S('id','');
    $S('name','');
    $S('number','');
    $S('sex','');
    $S('phone','');
    $S('personNo','');
    $S('endTime','');
    $S('price','');
    $S('ticketName','');
    $S('saleTime','');
    $S('icStatus','');
    $S('activeTimes','');
    $('photo').innerHTML='��Ƭ';
    $S('extendPrice','');
    $S('nextEndTime','');
    $S('total','');
    $S('returnTotal','');
    setDisabled('submitButton');
    setDisabled('clearButton');
}
function checkTotal(){
	var value=$V("total");
	if(value.length>0){
		 if (!isNumber(value)) {
			 Dialog.alert("����������!",function(){
				 $('total').focus();
					var r = $('total').createTextRange(); 
					r.moveStart('character', $('total').value.length); 
					r.collapse(false); 
					r.select();
			 });
			 return;
		 }
		var total=parseInt($V("total"));
		var extendPrice=parseInt($V("extendPrice"));
		$S('returnTotal',(total-extendPrice));
	}
}
function setDisabled(id){
	$(id).className='da-button gray large';
	$(id).onmouseover='';
	$(id).onmouseout='';
	$(id).style.cursor='';
	$(id).disabled=true;
}
function setAbled(id){
	$(id).className='da-button blue large';
	$(id).onmouseover="this.className='da-button red large";
	$(id).onmouseout="this.className='da-button blue large";
	$(id).style.cursor='hand';
	$(id).disabled=false;
}