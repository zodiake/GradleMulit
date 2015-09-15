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

		var loginTemplate = '<form id="ajaxForm"><div class="form-group"><label>用户名</label><input type="text" name="name" placeholder="用户名"/></div><div class="form-group"><label>密码</label><input type="password" name="password"/><i class="pass"></i></div>'
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
			if (num > 0) {
				productId = $(this).attr("data-id");
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
							alert("对不起您没有权限");
						} else if (data.data == "addone") {
							var cartNumber = $("#cartNumber"+ productId);
							var numberVal = cartNumber.html();
							cartNumber.html(parseInt(numberVal)+ parseInt(num));
						} else {
							var str = '<li id="cart'+ productId+ '"><div class="fl ct-img"><a href="/products/'+ productId
							+ '"><img width="50" height="50" src="'+ data.image+ '"/></a></div><div class="fl ct-name"><a href="/products/'
							+ productId+ '">'+ data.name+ '</a>'+ '</div><div class="fr ct-detail"><span class="ct-price"><b>'+ data.price
							+ '</b>×<i id="cartNumber'+ productId+ '">'+ num+ '</i></span><br/><a class="fr cartRemove" data-id="'+ productId
							+ '">删除</a></div></li>';
							$("#cartUl").append(str);
							var allNum = $("#allNum");
							allNum.html(parseInt(allNum.html()) + 1);
							var totalNum = $("#totalNum");
							totalNum.html(parseInt(totalNum.html()) + 1);
						}
					},
					error : function(data) {
						alert("系统异常");
					}
				});
			} else {
				alert("数量最少为1");
			}
			return false;
		});
	return this;
	};
})(jQuery);
