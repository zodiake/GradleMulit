$(function() {
	var formObj = $('form');
	$("#firstCategory").change(function(){
		var categoryId = $(this).val();
		switch(categoryId){
		case '1':
			formObj.attr("action","/provider/instrument?form");
			break;
		case '2':
			formObj.attr("action","/provider/reagents?form");
			break;
		case '3':
			formObj.attr("action","/provider/consumable?form");
			break;
		case '4':
			formObj.attr("action","/provider/service?form");
			break;
		}
	});
});