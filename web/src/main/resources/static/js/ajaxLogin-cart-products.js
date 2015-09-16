(function($) {
	$.fn.loginBeforeAjaxCart = function(options) {
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
		var data="";
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
					$('#includeHeader').load('/head');
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
				data : {"productId":data,'number':1},
				url : settings.url,
				dataType : 'json',
				type : 'post'
			}).success(function(response) {
				settings.success(response);
			}).fail(function(res) {
				settings.fail(res);
			});
		};

		this.click(function(event) {
			data = $(this).attr("data-id");
			$.ajax({
				url : settings.url,
				data : {
					'productId' : data,
					'number' : 1
				},
				dataType : 'json',
				type : 'post'
			}).success(function(res) {
				if (res.data == "login") {
					$('.hide-wrap').empty();
					$('.hide-wrap').append(loginForm$);
					$('.fixed').fadeIn();
					$('.hide-wrap').fadeIn();
				} else if (res.data == "no authority") {
					alert("对不起您没有权限");
				} else if (res.data == "addone") {
					var cartNumber = $("#cartNumber"+ data);
					var numberVal = cartNumber.html();
					cartNumber.html(parseInt(numberVal)+1);
				} else {
					var str = '<li id="cart'+ data+ '"><div class="fl ct-img"><a href="/products/'+ data
					+ '"><img width="50" height="50" src="'+ res.image+ '"/></a></div><div class="fl ct-name"><a href="/products/'
					+ data+ '">'+ res.name+ '</a>'+ '</div><div class="fr ct-detail"><span class="ct-price"><b>'+ res.price
					+ '</b>×<i id="cartNumber'+ data+ '">'+ 1+ '</i></span><br/><a class="fr cartRemove" data-id="'+ data
					+ '">删除</a></div></li>';
					$("#cartUl").append(str);
					var allNum = $("#allNum");
					allNum.html(parseInt(allNum.html()) + 1);
					var totalNum = $("#totalNum");
					totalNum.html(parseInt(totalNum.html()) + 1);
				}
			});
			return false;
		});
		return this;
	};
})(jQuery);