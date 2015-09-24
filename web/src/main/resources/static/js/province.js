$(function() {
	$('#province').change(function(){
		var obj = $(this);
		var id = obj.val();
		if(obj.val() != ""){
			$.ajax({
				type : 'get',
				url : '/provinces/'+id,
				success : function(data){
					$('#city').empty();
					var str = '<option value="">选择城市</option>';
					$.each(data,function(key,val){
						str = str+'<option value="'+val.id+'">'+val.name+'</option>';
					});
					$('#city').append(str);
				},error : function(data){
					promptError("系统异常");
				}
			}); 
		}else{
			$('#city').empty();
        	var str = '<option value="">选择城市</option>';
        	$('#city').append(str);
       }
	});
});