var DateUtil = {};

/*
 * yyyy-MM-dd HH:mm:ss <BR> �ַ���ת��Ϊ���ڸ�ʽ
 * 
 */
DateUtil.toDate = function(dateStr) {
	var d = new Date(Date.parse(dateStr.replace(/-/g, "/")));
	return d;
}

/**
 *  begin >end���� + ,��ȷ���0��С�ڷ���- �Ƚ����ڴ�С
 */
DateUtil.compare = function(beginDate, endDate) {
	
	var iDays = parseInt(Math.abs(beginDate - endDate) / 1000 / 60 / 60 /24);      
    if((beginDate-endDate)<0){    
        return -iDays;    
    }    

    return iDays;
    
	if (endDate.getYear() > beginDate.getYear())
		return -1;
	else if (endDate.getYear() < beginDate.getYear())
		return 1;

	if (endDate.getMonth() > beginDate.getMonth())
		return -1;
	else if (endDate.getMonth() < beginDate.getMonth())
		return 1;

	if (endDate.getDate() > beginDate.getDate())
		return -1;
	else if (endDate.getDate() < beginDate.getDate())
		return 1;

	 

	return 0;
}
