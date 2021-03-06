var filterModel = angular.module('filterModel', []);

filterModel.filter('stateFilter', function () {
    return function (state) {
        if (state == 'UP') {
            return '已上架';
        } else if (state == 'EXAMINE') {
            return '待审核';
        } else if (state == 'DOWN') {
            return '已下架';
        } else if (state == 'NOT') {
            return '已拒绝';
        }
    };
});

filterModel.filter('activeFilter', function () {
    return function (state) {
        return state == 'ACTIVATE' ? '有效' : '无效';
    };
});

filterModel.filter('reverseActiveFilter', function () {
    return function (state) {
        return state == 'ACTIVATE' ? '无效' : '有效';
    };
});

filterModel.filter('originalFilter', function () {
    return function (original) {
        return original == 'IMPORTED' ? '国产' : '进口';
    };
});

filterModel.filter('roleFilter',function(){
	return function(role){
		return role==1?'未审核':'已审核';
	};
});

filterModel.filter('productStateFilter',function(){
   return function(state){
        if(state=='DOWN')   {
            return '下架';
        }else if(state=='UP'){
            return '已上架';
        }else if(state=='EXAMINE'){
            return '审核中';
        }else if(state=='NOT'){
            return '未通过';
        }
   };
});

filterModel.filter('subjectShowOnIndexFilter',function(){
	return function(state){
		return state == 'ACTIVATE' ? '不显示在首页' : '显示在首页';
	}
});
filterModel.filter('brandShowOnIndexFilter',function(){
	return function(show){
		return show == 'ACTIVATE' ? '显示在首页' : '不显示在首页';
	}
});
filterModel.filter('brandShowButtonFilter',function(){
	return function(show){
		return show == 'ACTIVATE' ? '取消' : '展示';
	}
});
