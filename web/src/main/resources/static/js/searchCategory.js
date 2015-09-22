	var firstCategory = $('#firstCategory');
	var secondCategory = $('#secondCategory');
	var thirdCategory = $('#thirdCategory');
	$('#firstCategory').change(function(){
		secondCategory.empty();
		thirdCategory.empty();
		$.ajax({
			type : 'get',
			url : '/ajaxProductCategory/'+firstCategory.val(),
			success:function(data){
				var str = '<option value="">请选择</option>';
				$.each(data,function(key,val){
					str = str+'<option value="'+val.id+'">'+val.name+'</option>';
				});
				secondCategory.append(str);
			},error:function(data){
				promptError("系统异常");
			}
		});
	});
	$('#secondCategory').change(function(){
		thirdCategory.empty();
		$.ajax({
			type : 'get',
			url : '/ajaxProductCategory/'+secondCategory.val(),
			success:function(data){
				var str = '<option value="">请选择</option>';
				$.each(data,function(key,val){
					str = str+'<option value="'+val.id+'">'+val.name+'</option>';
				});
				thirdCategory.append(str);
			},error:function(data){
				promptError("系统异常");
			}
		});
	});
