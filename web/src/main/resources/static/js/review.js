/**
 * 
 */
$(function() {
	$(".page").click(function(){
		var fixed = $(".fixed");
		var gif = $("#gif");
		fixed.fadeIn();
		gif.fadeIn();
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
				$(".fixed").fadeOut();
				$("#gif").fadeOut();
			}
		});
	});
});