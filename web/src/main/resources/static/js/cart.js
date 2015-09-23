$(function() {
	getCount();
	var deleteId;
	$(".deleteCart").click(function() {
		var product = $(this);
		var productId = product.attr("data-id");
		$(".fixed").fadeIn();
		$(".deletePrompt").fadeIn();
		deleteId = productId;
	});
	$("#delete").click(function(){
		$.ajax({
			type : 'DELETE',
			url : "/user/carts/" + deleteId,
			success : function(data) {
				if (data == "success") {
					$("#" + deleteId).remove();
					getCount();
					var listSize = $("#listSize");
					listSize.html(parseInt(listSize.html()) - 1);
					var checksLength = $(".check").length;
					if($(".check").length == 0){
						$("#noProduct").show();
					}
					$("#allNum").html(checksLength);
				}
				$(".fixed").fadeOut();
				$(".deletePrompt").fadeOut();
			},
			error : function(data) {
				promptError("系统异常");
			}
		});
	});

	$(".minus").click(function() {
		var i = $(this);
		var productId = i.attr("data-id");
		var cartId = i.attr("cart-id");
		var priceDom = $("#small" + cartId);
		var price = parseFloat(priceDom.attr("price")).toFixed(1);
		var product = $("#num" + productId);
		var number = product.val();
		if (number > 1) {
			number = parseFloat(number) - 1;
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
					promptError("系统异常");
				}
			});
		}

	});
	$(".plus").click(function() {
		var i = $(this);
		var productId = i.attr("data-id");
		var cartId = i.attr("cart-id");
		var priceDom = $("#small" + cartId);
		var price = parseFloat(priceDom.attr("price")).toFixed(1);
		var product = $("#num" + productId);
		var number = product.val();
		number = parseFloat(number) + 1;
		$.ajax({
			type : 'PUT',
			url : '/user/carts/' + cartId + '/' + number,
			success : function(data) {
				if (data != "error") {
					product.val(number);
					priceDom.html(parseFloat(number * price).toFixed(1));
					getCount();
				}
			},
			error : function(data) {
				promptError("系统异常");
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
					promptError('系统异常');
				}
			});
		} else {
			$.ajax({
				type : "PUT",
				url : '/user/carts/' + cartId + '/1?check',
				success : function(data) {
				},
				error : function(data) {
					promptError('系统异常');
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
		if(number<1 || number>999){
			promptError("数量只能在1~999之间");
			number = 999;
			i.val(number);
		}else{
		$.ajax({
			type : 'PUT',
			url : '/user/carts/' + cartId + '/' + number,
			success : function(data) {
				if (data == "success") {
					i.val(number);
					priceDom.html(parseFloat(number * price).toFixed(1));
					getCount();
				}
			},
			error : function(data) {
				promptError("系统异常");
			}
		});
		}
	});
	$("#checkAll").click(
			function() {
				if ($(this).is(':checked')) {
					$.ajax({
						type : 'put',
						url : '/user/carts/1?check',
						success : function(data) {
							var checkboxs = $('tr').find('input[name="carts"]');
							checkboxs.prop('checked', true);
							checkboxs.attr('checked', true);
							getCount();
						},
						error : function(data) {
							promptError("系统异常");
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
							promptError("系统异常");
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
            	promptSuccess("删除成功");
    		},error:function(data){
    			promptError("系统异常");
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