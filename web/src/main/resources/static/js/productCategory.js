function productCategoryInit(){
	$.ajax({
		url : "/productCategories/second",
		type : "get",
		success :function(data){
			$.each(data,function(key,val){
				var str = "";
				$.each(val,function(k,v){
					str = str +"<a href='/products/_search?secondCategory="+this.id+"'>"+this.name+"</a>";
				});
				switch(key){
				case "1":
					$("#instruments").append(str);
					break;
				case "2":
					$("#reagents").append(str);
					break;
				case "3":
					$("#supplies").append(str);
					break;
				case "4":
					$("#servers").append(str);
					break;
				}
			});
		},error: function(data){
			
		}
	});
}
productCategoryInit();