$(function() {
	getCount();
	$(".deleteCart").click(function() {
		var product = $(this);
		var productId = product.attr("data-id");
		$.ajax({
			type : 'DELETE',
			url : "/user/carts/" + productId,
			success : function(data) {
				if (data == "success") {
					$("#" + productId).remove();
					getCount();
					var listSize = $("#listSize");
					listSize.html(parseInt(listSize.html()) - 1);
					if($(".check").length==0){
						$("#noProduct").show();
					}
				}
			},
			error : function(data) {
				alert("系统异常");
			}
		});
	});

	$(".minus").click(function() {
		var i = $(this);
		var cartId = i.attr("cart-id");
		var productId = i.attr("data-id");
		var product = $("#num" + productId);
		var priceDom = $("#small" + cartId);
		
		var price = parseFloat(priceDom.attr("price")).toFixed(1);
		var number = product.val();
		if (number > 1) {
			number = parseFloat(number) - 1;
			product.val(number);
			priceDom.html(parseFloat(number * price).toFixed(1));
			getCount();
		}
	});
	$(".minus").mouseout(function(){
		var i = $(this);
		var productId = i.attr("data-id");
		var priceDom = $("#small" + cartId);
		var price = parseFloat(priceDom.attr("price")).toFixed(1);
		var product = $("#num" + productId);
		var number = product.val();
		var cartId = i.attr("cart-id");
		$.ajax({
			type : 'PUT',
			url : '/user/carts/' + cartId + '/' + number,
			success : function(data) {
			},
			error : function(data) {
				alert("系统异常");
			}
		});
	});
	$(".plus").click(function() {
		var i = $(this);
		var productId = i.attr("data-id");
		var cartId = i.attr("cart-id");
		var priceDom = $("#small" + cartId);
		var price = parseFloat(priceDom.attr("price")).toFixed(1);
		var product = $("#num" + productId);
		var number = product.val();
		if(number<999){
			number = parseFloat(number) + 1;
			product.val(number);
			priceDom.html(parseFloat(number * price).toFixed(1));
			getCount();
		}
	});
	$(".plus").mouseout(function(){
		var i = $(this);
		var productId = i.attr("data-id");
		var cartId = i.attr("cart-id");
		var priceDom = $("#small" + cartId);
		var price = parseFloat(priceDom.attr("price")).toFixed(1);
		var product = $("#num" + productId);
		var number = product.val();
		$.ajax({
			type : 'PUT',
			url : '/user/carts/' + cartId + '/' + number,
			success : function(data) {
			},
			error : function(data) {
				alert("系统异常");
			}
		});
	});
	$(".check").click(function() {
		var check = $(this);
		var cartId = check.attr('data-id');
		if (check.attr("checked") == "checked") {
			check.removeAttr("checked");
			$.ajax({
				type : "PUT",
				url : '/user/carts/' + cartId + '/0?check',
				success : function(data) {
				},
				error : function(data) {
					alert('error');
				}
			});
		} else {
			$.ajax({
				type : "PUT",
				url : '/user/carts/' + cartId + '/1?check',
				success : function(data) {
				},
				error : function(data) {
					alert('error');
				}
			});
			check.attr('checked', 'checked');
		}
		getCount();
	});
	$(".text-amount").change(function() {
		var i = $(this);
		var number = i.val();
		var cartId = i.attr("cart-id");
		var price = i.attr("price");
		var productId = i.attr("product-id");
		var priceDom = $("#small" + cartId);
		var product = $("#num" + productId);
		if(number<1||number>999){
			alert("数量只能在1~999之间");
			number = 999;
			product.val(number);
		}
		$.ajax({
			type : 'PUT',
			url : '/user/carts/' + cartId + '/' + number,
			success : function(data) {
				if (data == "success") {
					product.val(number);
					priceDom.html(parseFloat(number * price).toFixed(1));
					getCount();
				}
			},
			error : function(data) {
				alert(data);
			}
		});
	});
	$("#checkAll").click(
			function() {
				if ($(this).is(':checked')) {
					$.ajax({
						type : 'put',
						url : '/user/carts/all/1?check',
						success : function(data) {
							var checkboxs = $('tr').find('input[name="carts"]');
							checkboxs.prop('checked', true);
							checkboxs.attr('checked', true);
							getCount();
						},
						error : function(data) {
							alert(error);
						}
					});
				} else {
					$.ajax({
						type : 'put',
						url : '/user/carts/all/0?check',
						success : function(data) {
							var checkboxs = $('tr').find('input[name="carts"]');
							checkboxs.prop('checked', false);
							checkboxs.attr('checked', false);
							getCount();
						},
						error : function(data) {
							alert(error);
						}
					});
				}

			});
	$(".delete").click(function(){
		var obj = $(this);
		var productId = obj.attr('data-id');
    	$.ajax({
    		type:"delete",
    		url :'/user/carts/'+productId,
    		success:function(data){
    			$("#cart"+productId).remove();
    			var allNum = $("#allNum");
            	allNum.html(parseInt(allNum.html())-1);
            	var totalNum =$("#totalNum");
            	totalNum.html(parseInt(totalNum.html())-1);
            	$("#"+productId).remove();
    		},error:function(data){
    			alert("error");
    		}
    	});
	});
});
function getCount() {
	var checksAll = $(".check").length;
	var checks = $(".check[checked='checked']");
	var countNum = checks.length;
	$("#num").html(countNum);
	var count = 0;
	for (var i = 0; countNum > i; i++) {
		var id = $(checks[i]).attr('data-id');
		var j = $("#small" + id).html();
		count = count + parseFloat(j);
	}
	$("#count").html(count.toFixed(1));
	if(countNum==0){
		$('#createButton').attr('disabled','disabled');
	}else{
		$('#createButton').removeAttr("disabled");
	}
	if(countNum!=0 && checksAll==countNum){
		$('#checkAll').prop('checked', true);
		$('#checkAll').attr('checked', true);
	}else{
		$('#checkAll').prop('checked', false);
		$('#checkAll').attr('checked', false);
	}
}