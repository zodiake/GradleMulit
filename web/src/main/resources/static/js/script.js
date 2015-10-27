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
	$(".fixed:not(.hide-wrap),.close").click(function(){
		  $(".hide-wrap").fadeOut();
		  $(".hide-wrap-small").fadeOut();
		  $(".fixed").fadeOut();
	 })
	$('.make-orders').click(function(){
		$('.temp-wrap').fadeIn();
		$('.fixed').fadeIn();
	})
	/**头部购物车**/
	$('.head-cart,.searched').mouseenter(function(){
		$(this).find('.ct-cart-wrap').show();
		$(this).parent().find('.search-sort').show();
	})
	$('.head-cart').mouseleave(function(){
		$(this).find('.ct-cart-wrap').hide();
	})
	$('.head-search').mouseleave(function(){
		$(this).find('.search-sort').hide();
	})	
	$('.more-items a').click(function(){
		var li_par=$(this).parents('.search-select').find('.list-inline');		
		if($(this).text() =='收起'){
			$(this).text('更多')
			li_par.removeClass('expand').addClass('pack');
		}else{			
			$(this).text('收起')
			li_par.removeClass('pack').addClass('expand');
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

function selectAll(){
 if ($(".checkAll input").is(':checked')) {  
       $('.plans-list li').find("input").prop('checked', true)
    } else {  
       $('.plans-list li').find("input").prop('checked', false)
    }  

}
