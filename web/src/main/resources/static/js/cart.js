$(function() {
			getCount();
			$(".delete").click(function(){
				var product = $(this);
				var productId = product.attr("data-id");
				$.ajax({
					type : 'DELETE',
					url : "/user/cart/"+productId,
					success : function(data){
						if(data=="success"){
							$("#"+productId).remove();
							getCount();
							var listSize =$("#listSize");
							listSize.html(parseInt(listSize.html())-1);
						}
					},
					error : function(data){ 
						alert(data);
					}
				});
			});
			
			
			$(".minus").click(function(){
				var i = $(this);
				var productId = i.attr("data-id");
				var cartId = i.attr("cart-id");
				var priceDom = $("#small"+cartId);
				var price = parseFloat(priceDom.attr("price")).toFixed(1);
				var product = $("#num"+productId);
				var number = product.val();
				number = parseFloat(number) - 1;
				$.ajax({
					type : 'PUT',
					url : '/user/cart/'+cartId+'/'+number,
					success : function(data){
						if(data=="success"){
							product.val(number);
							priceDom.html(parseFloat(number*price).toFixed(1));
							getCount();
						}
					},
					error : function(data){ 
						alert(data);
					}
				});
				
			});
			$(".plus").click(function(){
				var i = $(this);
				var productId = i.attr("data-id");
				var cartId = i.attr("cart-id");
				var priceDom = $("#small"+cartId);
				var price = parseFloat(priceDom.attr("price")).toFixed(1);
				var product = $("#num"+productId);
				var number = product.val();
				number = parseFloat(number) + 1;
				$.ajax({
					type : 'PUT',
					url : '/user/cart/'+cartId+'/'+number,
					success : function(data){
						if(data!="error"){
							product.val(number);
							priceDom.html(parseFloat(number*price).toFixed(1));
							getCount();
						}
					},
					error : function(data){ 
						alert(data);
					}
				});
				
			});
			$(".check").click(function(){
				var check = $(this);
				var cartId = check.attr('data-id');
				if(check.attr("checked")=="checked"){
					check.removeAttr("checked");
					$.ajax({
						type : "PUT",
						url : '/user/cart/'+cartId+'/0?check',
						success : function(data){
							alert(data);
						},
						error : function(data){
							alert('error');
						}
					});
				}else{
					$.ajax({
						type : "PUT",
						url : '/user/cart/'+cartId+'/1?check',
						success : function(data){
							alert(data);
						},
						error : function(data){
							alert('error');
						}
					});
					check.attr('checked','checked');
				}
				getCount();
			});
			$(".text-amount").change(function(){
				var i = $(this);
				var number = i.val();
				var cartId = i.attr("cart-id");
				var price = i.attr("price");
				var productId = i.attr("product-id");
				var priceDom = $("#small"+cartId);
				var product = $("#num"+productId);
				$.ajax({
					type : 'PUT',
					url : '/user/cart/'+cartId+'/'+number,
					success : function(data){
						if(data=="success"){
							product.val(number);
							priceDom.html(parseFloat(number*price).toFixed(1));
							getCount();
						}
					},
					error : function(data){ 
						alert(data);
					}
				});
			});
			$("#checkAll").click(function(){
				var obj = $(this);
				var checks = $(".check");
				$.ajax({
					type : 'put',
					url : '/user/cart/all?check',
					success : function(data){
						checks.attr("checked",true);
					},error : function(data){
						alert(error);
					}
				});
			});
		});
function getCount(){
	var checks = $(".check[checked='checked']");
	var countNum = checks.length;
	$("#num").html(countNum);
	var count = 0;
	for( var i =0 ;countNum>i;i++){
		var id = $(checks[i]).attr('data-id');
		var j = $("#small"+id).html();
		count = count+parseFloat(j);
	}
	$("#count").html(count.toFixed(1));
}