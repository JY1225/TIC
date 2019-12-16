//create by duncan
/*
 * cookies��
 */
var Cookies=new Object();

/**
 * ͨ������ȡcookieֵ
 * cookieName    cookie��
 */
Cookies.getCookie=function(/*String*/ cookieName){
  document.cookie= "history='' path=/;expires="+new Date((new Date()).getTime() - 10000) ;
  var cookies = document.cookie;
  var finder = cookies.indexOf(cookieName + '=');
  if (finder == -1) // �Ҳ���
    return null;
  finder += cookieName.length + 1;
  var end = cookies.indexOf(';', finder);
  if (end == -1) return unescape(cookies.substring(finder));
  var value = unescape(cookies.substring(finder, end));
 
  if(!value){
  	value = '';
  }
  return unescape(value);
	
};
/**
 * ��cookieд������
 * @param cookieName  cookie��
 * @param value    cookieֵ
 * @param time    ����ʱ�� ���Ϊnull �򱣴�һ��
 */
Cookies.addCookie=function(/*String*/ cookieName,/*String*/ value,/*String*/ time){
	var expires = null;
	if(time!=null){
		expires = time;
	}else{
		expires = new Date((new Date()).getTime() + 1*56*24*60* 60000);//����cookis�����ʱ��Ϊһ��
	}
	document.cookie= cookieName + "="+escape(value)+ "; expires=" + expires.toGMTString() + "; path=/; " ;
};

/**
 * @param cookieName    cookie��
 */
Cookies.deleteCookie=function(/*String*/ cookieName){
	var value = Cookies.getCookie(cookieName);
	var expires = new Date();
	expires.setTime  (expires.getTime()  -  1);
	Cookies.addCookie(cookieName,value,expires);
};
