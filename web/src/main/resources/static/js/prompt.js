/**
 * 
 */
function promptSuccess(promptContent){
	var prompt = $(".prompt");
	prompt.html('<h2 class="success">'+promptContent+'</h2>');
	prompt.show();
	setTimeout('$(".prompt").hide()',2000);
}
function promptError(promptContent){
	var prompt = $(".prompt");
	prompt.html('<h2 class="error">'+promptContent+'</h2>');
	prompt.show();
	setTimeout('$(".prompt").hide()',2000);
}