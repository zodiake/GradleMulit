(function($) {
	$.fn.loginBeforeAjaxReview = function(options) {
		var self = $(this);

		var settings = $.extend({
			url : null,
			data : null,
			div : null,
			success : null,
			fail : null
		}, options || {});

		var loginTemplate = '<div class="ajaxLogin-close"><img src="/img/icon-close.png" width="20px" height="20px"/></div><form id="ajaxForm"><div class="form-group"><label>用户名</label><input type="text" name="name" placeholder="用户名"/></div><div class="form-group"><label>密码</label><input type="password" name="password" placeholder="密码"/><i class="error block pass"></i></div>'
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
				data : data,
				url : settings.url,
				type : 'post'
			}).success(function(response) {
				settings.success(response);
			}).fail(function(res) {
				settings.fail(res);
			});
		};

		this.submit(function(event) {
			data = $(this).serialize();
			$.ajax({
				url : settings.url,
				data : data,
				type : 'post'
			}).success(function(response) {
				if (response == 'login'){
					$(".fixed").fadeIn();
					var wrap = $('.hide-wrap');
					wrap.empty();
					wrap.append(loginForm$);
					wrap.fadeIn();
				}
				else if(response == "content is null"){
					promptError("请填写评论内容");
				}
				else if(response == "content is too long"){
					promptError("评论内容过长");
				}
				else if(response == "success"){
					var productId = $('#reviewForm').attr('data_id');
					showReview(productId);
					$("#content").val("");
					promptSuccess("评论成功");
				}
			});
			return false;
		});
		return this;
	};
})(jQuery);
