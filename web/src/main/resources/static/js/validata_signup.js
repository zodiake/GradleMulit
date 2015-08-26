$(function() {
	var all = false;
	var agreementCheck = false;
	$("#agreement").click(function(){
		if(agreementCheck==true)
			agreementCheck=false;
		else
			agreementCheck=true;
		if(agreementCheck){
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