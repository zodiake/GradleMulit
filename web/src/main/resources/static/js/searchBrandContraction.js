$(function() {
	var brandSearch = $('#brandSearch');
	var more = $('#more');
	var contraction = $('#contraction');
	$('#more a').click(function(){
		brandSearch.css("height","100%");
		more.hide();
		contraction.show();
	});
	$('#contraction a').click(function(){
		brandSearch.css("height","40px");
		more.show();
		contraction.hide();
	});
});
