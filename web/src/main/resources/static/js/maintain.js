$(function() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");

	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	function verify(productId) {
		$.ajax({
			type : 'put',
			url : '/provider/products/' + productId + '/EXAMINE',
			success : function(data) {
				var td = $("#tr" + productId);
				td.empty();
				if ($("#all").attr("class")) {
					td.append(data);
				}
			},
			error : function(data) {
				alert(data);
			}
		});
	}
	function out(productId) {
		$.ajax({
			type : 'put',
			url : '/provider/products/' + productId + '/DOWN',
			success : function(data) {
				var td = $("#tr" + productId);
				td.empty();
				if ($("#all").attr("class")) {
					td.append(data);
				}
			},
			error : function(data) {
				alert("error");
			}
		});
	}
	id=0;
	$(".up").click(function() {
		var obj = $(this).parent();
		var product = obj.attr('data-id');
		verify(product);
	});
	$('.revoke').click(function() {
		var pass = $(this).parent();
		$('#revoke').fadeIn();
		$('.fixed').fadeIn();
		id = pass.attr('data-id');
	});
	$('.down').click(function() {
		var pass = $(this).parent();
		$('#down').fadeIn();
		$('.fixed').fadeIn();
		id = pass.attr('data-id');
	});
	$("#revokeOk").click(function() {
		$(".hide-wrap").fadeOut();
		$(".hide-wrap-small").fadeOut();
		$(".fixed").fadeOut();
		out(id);
	});
	$("#downOk").click(function() {
		$(".hide-wrap").fadeOut();
		$(".hide-wrap-small").fadeOut();
		$(".fixed").fadeOut();
		out(id);
	});
	$("#revokeCancel").click(function() {
		$(".hide-wrap").fadeOut();
		$(".hide-wrap-small").fadeOut();
		$(".fixed").fadeOut();
	});
	$("#downCancel").click(function() {
		$(".hide-wrap").fadeOut();
		$(".hide-wrap-small").fadeOut();
		$(".fixed").fadeOut();
	});
	$(".fixed:not(.hide-wrap),.cancel,.fixed:not(.hide-wrap-small)").click(function(){
		  $(".hide-wrap").fadeOut();
		  $(".hide-wrap-small").fadeOut();
		  $(".fixed").fadeOut();
	 })
	modId=0;
	$(".modify").click(function(){
		var obj = $(this).parent();
		var mId = obj.attr('data-id');
		if(mId != modId){
			$("#modify").empty();
			$.ajax({
				type : 'get',
				url : '/provider/products/'+mId+'?edit',
				success : function(data){
					$("#modify").append(data);
					$("#modify").show();
					$('.fixed').show();
					modId=mId;
				},error : function(data){
					alert("error");
				}
			});
		}else{
			$("#modify").show();
			$('.fixed').show();
		}
	});
});