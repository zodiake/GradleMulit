$(function(){	
	var tabs_nav=$('.tabs-nav a')
	tabs_nav.click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		var index=tabs_nav.index(this);
		$('.tab-conts .tab-panel').eq(index).show().siblings().hide();
	})
	$('.change-color tr:even').css('background','#eee');
	$('.show-wrap a,button.show-wrap,.temped,.show-wrap').click(function(){
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
	$('.head-user,.searched,.news-down').mouseenter(function(){
		$(this).find('.ct-cart-wrap,.user-menu').slideDown();
		$(this).parent().find('.search-sort').slideDown();
		$(this).find('.news-sort').slideDown();
	})
	$('.head-cart,.head-user').mouseleave(function(){
		$(this).find('.ct-cart-wrap,.user-menu').slideUp();
	})
	$('.news-sort').mouseleave(function(){
		$(this).slideUp();
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
	$('.plans-list li').click(function(){
		if($(this).find('input').is(':checked')){
			$(this).find('input').prop('checked', false);
		}else{
			$(this).find('input').prop('checked', true);
		}
	})
	$('#price').keyup(function(){      
        $(this).val($(this).val().replace(/[^0-9.]/g,''));      
    }).bind("paste",function(){       
        $(this).val($(this).val().replace(/[^0-9.]/g,''));       
    }) 
})

function selectAll(){
 if ($(".checkAll input").is(':checked')) {  
       $('.plans-list li').find("input").prop('checked', true)
    } else {  
       $('.plans-list li').find("input").prop('checked', false)
    }  

}