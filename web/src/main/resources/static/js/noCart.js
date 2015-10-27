$(function(){	
	/**头部购物车**/
	$('.searched').mouseenter(function(){
		$(this).find('.ct-cart-wrap').show();
		$(this).parent().find('.search-sort').show();
		$(this).find('.news-sort').show();
	})
	$('.head-cart').mouseleave(function(){
		$(this).find('.ct-cart-wrap').hide();
	})
	$('.head-search').mouseleave(function(){
		$(this).find('.search-sort').hide();
	})	
	$('.make-orders').click(function(){
		$('.temp-wrap').fadeIn();		
		$('.fixed').fadeIn();
	})
	$(".cancel").click(function(){
		  $(".hide-wrap").fadeOut();
		  $(".hide-wrap-small").fadeOut();
		  $(".fixed").fadeOut();
	 })
	$(".fixed:not(.hide-wrap)").click(function(){
		  $(".hide-wrap").fadeOut();
		  $(".hide-wrap-small").fadeOut();
		  $(".fixed").fadeOut();
	 })
	 $('.category-con').mouseenter(function() {
		  $(this).find('.category-child').show();
     }).mouseleave(function() {
          $(this).find('.category-child').hide();
     })
     $('.plans-list li').mouseenter(function() {
        $(this).find('input').show();
    }).mouseleave(function() {
        if ($(this).find('input').is(':checked')) {
            $(this).find('input').show();
        } else {
            $(this).find('input').hide();
        }
    })
    $('.nav-box li,.search-tab a').click(function() {
        $(this).addClass('active').siblings().removeClass('active')

    })
})
