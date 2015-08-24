$(function() {
	  	var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");

		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
		function verify(productId){
			$.ajax({
				type : 'put',
				url : '/provider/products/'+productId+'/VERIFY',
				success : function(data){
					var td = $("#tr"+productId);
					td.empty();
					td.append(data);
				},error : function(data){
					alert(data);
				}
			});
		}
		function out(productId){
			$.ajax({
				type : 'put',
				url : '/provider/products/'+productId+'/OUT',
				success : function(data){
					var td = $("#tr"+productId);
					td.empty();
					td.append(data);
				},error : function(data){
					alert("error");
				}
			});
		}
		$(".pass").click(function(){
			var pass = $(this);
			var productId = pass.attr("data-id");
			verify(productId);
		});
		$(".nopass").click(function(){
			var pass = $(this);
			var productId = pass.attr("data-id");
			out(productId);
		});
		$(".off").click(function(){
			var pass = $(this);
			var productId = pass.attr("data-id");
			out(productId);
		});
	});