var SelectDialog = {
	selectStudent : function(myFun) {
		var diag = new Dialog("DialogStudent");
		diag.Width = 685;
		diag.Height = 397;
		diag.Title = "ѡ��ѧ��";
		diag.URL = "common/selectStudentPage.do?student.status=0&multiSelect=false&selectSize=15&orderField=grade.id&order=asc";
		diag.OKEvent = function() {// ���ȷ�����÷���
			var dataTable = $DW.DataGrid.getSelectedData("dg1");
			if (dataTable.getRowCount() == 0) {
				Dialog.alert("��ѡ��һλ�׶���");
				return;
			}
			myFun(dataTable);
			$D.close();// �رղ�
		}
		diag.show();
	}
}