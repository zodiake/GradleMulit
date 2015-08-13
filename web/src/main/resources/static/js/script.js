$(function(){	
	var tabs_nav=$('.tabs-nav a')
	tabs_nav.click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		var index=tabs_nav.index(this);
		$('.tab-conts .tab-panel').eq(index).show().siblings().hide();
	})
	$('.change-color tr:even').css('background','#eee');
	$('.show-wrap a,button.show-wrap,.temped').click(function(){
		$('.hide-wrap').fadeIn();
		$('.fixed').fadeIn();
	})
	$('a.download').click(function(){
		$('.hide-wrap-small').fadeIn();
		$('.fixed').fadeIn();
	})
	$(".fixed:not(.hide-wrap),.cancel,.fixed:not(.hide-wrap-small)").click(function(){
		  $(".hide-wrap").fadeOut();
		  $(".hide-wrap-small").fadeOut();
		  $(".fixed").fadeOut();
	 })
	$('.make-orders').click(function(){
		$('.temp-wrap').fadeIn();
		$('.fixed').fadeIn();
	})
	/**头部购物车**/
	$('.head-cart,.head-user,.searched').mouseenter(function(){
		$(this).find('.ct-cart-wrap,.user-menu').slideDown();
		$(this).parent().find('.search-sort').slideDown();
	})
	$('.head-cart,.head-user').mouseleave(function(){
		$(this).find('.ct-cart-wrap,.user-menu').slideUp();
	})
	$('.head-search').mouseleave(function(){
		$(this).find('.search-sort').slideUp();
	})
	$('.more-items a').click(function(){
		var li_par=$(this).parents('li');		
		if($(this).text() =="收起"){
			$(this).text('更多')
			li_par.find('.items').removeClass('expand').addClass('pack');
		}else{			
			$(this).text('收起')
			li_par.find('.items').removeClass('pack').addClass('expand');
		}
	})
	
})
