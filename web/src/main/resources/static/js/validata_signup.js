$(function() {
	var agreementCheck = true;
	$("#agreement").click(function(){
		if($(this).is(':checked')){
			$("#regirect").removeAttr("disabled");
		}else{
			$("#regirect").attr("disabled","disabled");
		}
	});
	$("#name").change(function(){
		var obj = $(this);
		$.ajax({
			type : 'POST',
			url : '/name?valid',
			data : {'name':obj.val()},
			success : function(data){
				if(data=="true"){
					$("#nameerror").remove();
				}else{
					if($("#nameerror").text()){
					}else{
						obj.after('<i class="error" id="nameerror">用户名已存在</i>');
					}
				}
			},error : function(data){
			}
		});
	});
});