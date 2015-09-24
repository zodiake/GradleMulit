    function cartRemove(productId){
    	$.ajax({
    		type:"delete",
    		url :'/user/carts/'+productId,
    		success:function(data){
    			$("#cart"+productId).remove();
    			var allNum = $("#allNum");
            	allNum.html(parseInt(allNum.html())-1);
            	var totalNum =$("#totalNum");
            	totalNum.html(parseInt(totalNum.html())-1);
    		},error:function(data){
    			promptError("系统异常");
    		}
    	});
    }
    function createCart(number,product){
    	 $.ajax({
             type : 'POST',
             url : '/ajax/carts',
             dataType: 'json',
             data : {
                 'productId' : product,
                 'number' : number
             },
             success : function(data) {
                 if (data.data == "no authority") {
                	 promptError("对不起您没有权限");
                 } else if(data.data == "addone"){
                 	var cartNumber = $("#cartNumber"+product);
                 	cartNumber.html(data.number);
                 	promptSuccess("已加入购物车");
                 }else{
                 	var str = '<li id="cart'+product+'"><div class="fl ct-img"><a href="/products/'+product+'"><img width="50" height="50" src="'+data.image+'"/></a></div><div class="fl ct-name"><a href="/products/'+product+'">'+data.name+'</a>'+
						'</div><div class="fr ct-detail"><span class="ct-price"><b>'+data.price+'</b>×<i id="cartNumber'+product+'">'+number+'</i></span><br/><a class="fr cartRemove" data-id="'+product+'">删除</a></div></li>';
                 	$("#cartUl").append(str);
                 	var allNum = $("#allNum");
                 	allNum.html(parseInt(allNum.html())+1);
                 	var totalNum =$("#totalNum");
                 	totalNum.html(parseInt(totalNum.html())+1);
                 	promptSuccess("已加入购物车");
                 }
             },
             error : function(data) {
            	 promptError("系统异常");
             }
    });
    }
    $(function() {
    $(".head-dorpdown").on("click",".cartRemove",function(){
    	var obj = $(this);
    	var productId = obj.attr("data-id");
    	cartRemove(productId);
    });
});