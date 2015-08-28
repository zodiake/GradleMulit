$(function() {
	  	var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");

		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
		function verify(productId){
			$.ajax({
				type : 'put',
				url : '/provider/products/'+productId+'/EXAMINE',
				success : function(data){
					var td = $("#tr"+productId);
					td.empty();
					if($("#all").attr("class")){
						td.append(data);
					}
				},error : function(data){
					alert(data);
				}
			});
		}
		function out(productId){
			$.ajax({
				type : 'put',
				url : '/provider/products/'+productId+'/DOWN',
				success : function(data){
					var td = $("#tr"+productId);
					td.empty();
					if($("#all").attr("class")){
						td.append(data);
					}
				},error : function(data){
					alert("error");
				}
			});
		}
		$(".pass").click(function(){
			var pass = $(this).parent();
			verify(pass.attr('data-id'));
		});
		$(".nopass").click(function(){
			var pass = $(this).parent();
			out(pass.attr('data-id'));
		});
		$(".off").click(function(){
			var pass = $(this).parent();
			out(pass.attr('data-id'));
		});
	});