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
					$('.hide-wrap').empty();
					$('.hide-wrap').append(loginForm$);
					$('.fixed').fadeIn();
					$('.hide-wrap').fadeIn();
				}
				else if(response == "content is null"){
					alert("请填写评论内容");
				}
				else{
					var username = $('#username').text();
					var str = '<li><div class="member-info fl"><img src="/img/member.png" width="60" height="60"/></div><div class="member-global fr"><div class="member-msg clearfix"><span class="me-name fl">'
																+ response
																	+ '</span><span class="evt-time fr"><i>刚刚</i></span></div><div class="msg"><p>'
																	+ $("#content").val()
																	+ '</p></div></div></li>';
					var ul = $('#reviewUl');
					var num = $('#reviewNum').text();
					if (num >= 10) {
						var li = $('#reviewUl li');
						li[9].remove();
					}
					ul.prepend(str);
						$('#reviewNum').text(parseInt(num) + 1);
						$("#content").val("");
				}
			});
			return false;
		});
		return this;
	};
})(jQuery);