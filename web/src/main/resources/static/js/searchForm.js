/**
 * 
 */
$(function(){
    $('#searchForm a').click(function(event){
        event.preventDefault();
        var form=$('#searchForm');
        var source=$(event.target);   
        var url=source.attr('data-name');
        form.find('input').attr('name',source.attr('data-input'));
        form.find('span').text(source.text());
        form.attr('action',url);
    });
});
