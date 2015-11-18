(function($) {
	$.fn.loginBeforeAjaxCart  = function(options) {
		var self = $(this);

		var settings = $.extend({
			url : null,
			data : null,
			div : null,
			success : null,
			fail : null
		}, options || {});

		var loginTemplate = '<div class="ajaxLogin-close"><span style="width:30px;height:30px;font-size:30px;">&times;</span><div class="form-group"><label>用户名</label><input type="text" name="name" placeholder="用户名"/></div><div class="form-group"><label>密码</label><input type="password" name="password" placeholder="密码"/><i class="error block pass"></i></div>'
			+ '<div class="clearfix"><button class="btn orange" type="submit">登录</button><a class="fr blue" href="/forgetPw">忘记密码？</a></div></form>';
		var num = "";
		var productId = "";
		var loginForm$ = $(loginTemplate).on('submit', function(event) {
			var self = $(this);
			$.ajax({
				url : '/ajaxLogin',
				data : self.serialize(),
				type : 'post'
			}).success(function(response) {
				if(response=="success"){
					$('.fixed').fadeOut();
					$('.hide-wrap').fadeOut();
					self.remove();
					ajaxPost();
				}else if(response = "fail"){
					$(".pass").html("密码错误");
				}
			});
			return false;
		});

		var ajaxPost = function() {
			$.ajax({
				data : {'productId' : productId,'number' : num},
				url : '/ajax/carts',
				dataType : 'json',
				type : 'post'
			}).success(function(response) {
				settings.success(response);
			}).fail(function(res) {
				settings.fail(res.data);
			});
		};

		this.click(function(event) {
			num = $("#num").val();
			if(num > 999){
				promptError('商品数量最多为999');
				$("#num").val(999);
				return;
			}
			if (1 > num) {
				promptError("数量最少为1");
				$("#num").val(1);
				return;
			} 
			productId = $(this).attr("data-id");
			var cartNumber = $("#cartNumber"+ productId);
			if(parseInt(cartNumber.html())+num>999){
				var surplus = 999-parseInt(cartNumber.html());
				promptError("该商品还可添加"+surplus+"件");
				if(surplus==0)
					surplus = 1;
				$("#num").val(surplus);
				return;
			}
			$.ajax({
				type : 'POST',
				url : '/ajax/carts',
				dataType : 'json',
				data : {
					'productId' : productId,
					'number' : num
				},
				success : function(data) {
					if (data.data == "login") {
						$('.hide-wrap').empty();
						$('.hide-wrap').append(loginForm$);
						$('.fixed').fadeIn();
						$('.hide-wrap').fadeIn();
					} else if (data.data == "no authority") {
						promptError("对不起您没有权限");
					} else if (data.data == "addone") {	
						cartNumber.html(data.number);
						promptSuccess("已加入购物车");
					} else {
						var str = '<li id="cart'+ productId+ '"><div class="fl ct-img"><a href="/products/'+ productId
						+ '"><img width="50" height="50" src="'+ data.image+ '"/></a></div><div class="fl ct-name"><a href="/products/'
						+ productId+ '">'+ data.name+ '</a>'+ '</div><div class="fr ct-detail"><span class="ct-price"><b>'+ data.price
						+ '</b>×<i id="cartNumber'+ productId+ '">'+ num+ '</i></span><br/><a class="fr cartRemove" data-id="'+ productId
						+ '">删除</a></div></li>';
						$("#cartUl").prepend(str);
						var allNum = $(".cart-num");
	                 	allNum.html(parseInt(allNum.html())+1);
						var totalNum = $("#totalNum");
						totalNum.html(parseInt(totalNum.html()) + 1);
						promptSuccess("已加入购物车");
					}
				},
				error : function(data) {
					promptError("系统异常");
				}
			});
		return false;
		});
	return this;
	};
})(jQuery);
