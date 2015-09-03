(function($) {
	$.fn.loginBeforeAjax = function(options) {
		var self = $(this);

		var settings = $.extend({
			url : null,
			data : null,
			div : null,
			success : null,
			fail : null
		}, options || {});

		var loginTemplate = '<form><input type="text" name="name"/><input type="password" name="password"/><input type="submit"/></form>';
		var data="";
		var loginForm$ = $(loginTemplate).on('submit', function(event) {
			var self = $(this);
			$.ajax({
				url : '/ajaxLogin',
				data : self.serialize(),
				type : 'post'
			}).success(function() {
				self.remove();
				ajaxPost();
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
				alert(data);
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
					$('#' + settings.div).append(loginForm$);
				}
				else if(response == "content is null"){
					alert("content is null");
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
						$("#includeHeader").load("/head/head.html");
				}
			});
			return false;
		});
		return this;
	};
})(jQuery);
