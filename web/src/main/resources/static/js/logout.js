$(function() {
	$("#logout").click(function(){
		$.ajax({
			url : "/logout",
			type : "post",
			success:function(data){
				$("body").html(data);
			}
		});
	});
});