/**
 * 
 */
$(function() {
	$(".page").click(function(){
		var page = $(this).attr("page");
		var url = $(this).attr("url");
		$.ajax({
			url : url+"?page="+page,
			type : "get",
			success : function(data){
				var parent = $("#reviewsList").parent();
				console.log(parent);
				parent.empty();
				parent.append(data);
			}
		});
	});
});