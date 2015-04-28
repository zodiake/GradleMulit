(function($) {
	$.fn.ajaxLogin = function(options) {
		var settings = $.extend({
			url : null,
			data : null,
			div : null,
			callback : null
		}, options || {});

		var loginTemplate = '<form><input type="text" name="name"/><input type="password" name="password"/><input type="submit"/></form>';

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
				data : settings.data,
				url : settings.url,
				type : 'post'
			}).success(function(response) {
				settings.callback(response);
			});
		};

		this.submit(function(event) {
			$.ajax({
				url : settings.url,
				data : settings.data,
				type : 'post'
			}).success(function(response) {
				if (response == 'login')
					$('#' + settings.div).append(loginForm$);
				else
					ajaxPost();
			});
			return false;
		});
	};
})(jQuery);