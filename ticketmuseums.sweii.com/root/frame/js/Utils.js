/**
 * 
 * ����js
 * 
 */

var Utils = {
	// ��ѯ���Ͽ��
	getScrapStorage : function(customerId,produceCustomerId,type,callback) {
var	dc=new DataCollection();
	dc.add("customerId",customerId);
	dc.add("type",type);
	dc.add("produceCustomerId",produceCustomerId);
	Server.sendRequest('scrapstorage/queryStorage.do',dc,function(response){
           callback(response);
	},'json');
 
	},
    updatePrintCount:function(entity,id,field,count,reload){
		var	dc=new DataCollection();
		dc.add("entity",entity);
		dc.add("id",id);
		dc.add("field",field);
		dc.add("count",count);
		if(reload==null)reload=true;
		Server.sendRequest('common/updatePrintCount.do',dc,function(response){
			if(reload){
			location.reload();
			}
	           
		},'json');
		
	}
	,
	open : function(entity, id, field,value) {
		Dialog.confirm("��ȷ��Ҫ������ӡ��?",function(){
			var dc = new DataCollection();
			dc.add("entity", entity);
			dc.add("id", id);
			dc.add("field", field);
			dc.add("value", value);
			dc.add("javaType", 'int');
			Server.sendRequest('common/fieldEditEntity.do', dc,
					function(response) {
				       Dialog.alert("�ѿ�ͨ��ӡ���ܡ�",function(){
				    	   location.reload();
				       });
					}, 'json');
			
		})
		
	}
		
};
