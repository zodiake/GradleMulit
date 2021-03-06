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

		var loginTemplate = '<div class="ajaxLogin-close"><span style="width:30px;height:30px;font-size:30px;">&times;</span></div><form id="ajaxForm"><div class="form-group"><label>用户名</label><input type="text" name="name" placeholder="用户名"/></div><div class="form-group"><label>密码</label><input type="password" name="password" placeholder="密码"/><i class="error block pass"></i></div>'
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
				console.log(response);
				if (response == 'login'){
					$('.hide-wrap').empty();
					$('.hide-wrap').append(loginForm$);
					$('.fixed').fadeIn();
					$('.hide-wrap').fadeIn();
				}else if(response == "no authority"){
					promptError("对不起您没有权限");
				}else if(response == "duplicate"){
					promptSuccess("该商品已经加入收藏");
					$('.collect-num').html("已收藏");
					$('.collect-num').removeAttr("id");
				}else if(response == "success"){
					var collectionNum = $("#number");
					collectionNum.html(parseInt(collectionNum.html())+1);
					$('.collect-num').html("已收藏");
					$('.collect-num').removeAttr("id");
					promptSuccess("添加收藏成功");
				}
			});
			return false;
		});
		return this;
	};
})(jQuery);
