(function($) {
	$.fn.loginBeforeAjaxCollection = function(options) {
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
				data : {"id":data},
				url : settings.url,
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
				data : {"id":data},
				type : 'post'
			}).success(function(response) {
				if (response == 'login'){
					$('.hide-wrap').empty();
					$('.hide-wrap').append(loginForm$);
					$('.fixed').fadeIn();
					$('.hide-wrap').fadeIn();
				}
				else if(response=="no authority")
      				alert("对不起您没有权限");
      			else if(response=="duplicate")
      				alert("该商品已被您收藏");
      			else if(response == "success"){
      				alert("添加收藏成功");
      			}
			});
			return false;
		});
		return this;
	};
})(jQuery);