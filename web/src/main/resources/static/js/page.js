/**
 * 
 */
$(function() {
	$("#goToPage").click(function(){
		var btn = $(this);
		var number = $("#number").val();
		if(number!=0)
			number = number - 1;
		var href = btn.attr('href')+"&page="+number;
		console.log(href);
		location.href=href;
	});
	
	$("#number").blur(function(){
		var max = $(this).attr('max');
		var min = $(this).attr('min');
		var value = $(this).val();
		if(value > max)
			$(this).val(max);
		else if(min > value)
			$(this).val(min);
			
	});
	
	$("#numberPage").blur(function(){
		var max = $(this).attr('max');
		var min = $(this).attr('min');
		var value = $(this).val();
		if(value > max)
			$(this).val(max);
		else if(min > value)
			$(this).val(min);
			
	});
	
	$("#ajaxGoToPage").click(function(){
		var fixed = $(".fixed");
		var gif = $("#gif");
		fixed.fadeIn();
		gif.fadeIn();
		var page = $("#numberPage").val();
		var url = $(this).attr("url");
		$.ajax({
			url : url+"?page="+page,
			type : "get",
			success : function(data){
				window.scrollTo(0,590);
				var parent = $("#reviewsList").parent();
				parent.empty();
				parent.append(data);
				$(".fixed").fadeOut();
				$("#gif").fadeOut();
			}
		});
	});
});