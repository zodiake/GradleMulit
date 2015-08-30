	var formObj = $('form');
	$('#firstCategory').change(function(){
		var categoryId = $(this).val();
		switch(categoryId){
		case '1':
			formObj.attr("action","/provider/instruments?form");
			break;
		case '2':
			formObj.attr("action","/provider/reagents?form");
			break;
		case '3':
			formObj.attr("action","/provider/consumables?form");
			break;
		case '4':
			formObj.attr("action","/provider/services?form");
			break;
		}
	});
