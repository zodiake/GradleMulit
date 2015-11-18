(function($) {
	$.fn.loginBeforeAjaxSubject = function(options) {
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
    	var checksVal = [];
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
				data : {"productIds":checksVal},
				url : settings.url,
				traditional:true,
				dataType : 'json',
				type : 'post'
			}).success(function(response) {
				settings.success(response);
			}).fail(function(res) {
				settings.fail(res);
			});
		};

		this.click(function(event) {
        	$('input[name="checks"]:checked').each(function(){
        		checksVal.push($(this).val());
        	});
        	if(checksVal.length!=0){
				$.ajax({
					url : settings.url,
					data : {"productIds":checksVal},
					type : "post",
	            	traditional:true,
	            	dataType:"json",
				}).success(function(res) {
					if (res.data == "login") {
						$('.hide-wrap').empty();
						$('.hide-wrap').append(loginForm$);
						$('.fixed').fadeIn();
						$('.hide-wrap').fadeIn();
					} else if(res.data=="no authority"){
						promptError("对不起您没有权限");
	    			}else{
	    				var strs = "";
	    				var num =0;
	    				$(res).each(function(key,val){
	    					var str = '<li id="cart'+val.productId+'"><div class="fl ct-img"><a href="/products/'+val.productId+'"><img width="50" height="50" src="'+val.image+'"/></a></div><div class="fl ct-name"><a href="/products/'+val.productId+'">'+val.name+'</a>'+
	    					'</div><div class="fr ct-detail"><span class="ct-price"><b>'+val.price+'</b>×<i id="cartNumber'+val.productId+'">'+val.num+'</i></span><br/><a class="fr cartRemove" data-id="'+val.productId+'">删除</a></div></li>';
	    					strs= strs+str;
	    					num++;
	    				});
	    				var cartUl = $("#cartUl");
	    				cartUl.empty();
	    				cartUl.append(strs);
	    				var allNum = $("#allNum");
	    				allNum.html(num);
	    				var totalNum =$("#totalNum");
	    				totalNum.html(num);
	    				checksVal.length=0;
	    				promptSuccess("已加入购物车");
	    			}
	    		}).error(function(data){
	    			promptError("系统异常");
	    		});
        	}else{
        		promptError("请选择要加入购物车的商品");
        	}
			return false;
		});
		return this;
	};
})(jQuery);
