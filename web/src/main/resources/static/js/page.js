/**
 * 
 */
$(function() {
	$("#goToPage").click(function(){
		var btn = $(this);
		var number = $("#number").val()-1;
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
});