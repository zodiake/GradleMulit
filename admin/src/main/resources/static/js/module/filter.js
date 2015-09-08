var filterModel = angular.module('filterModel', []);

filterModel.filter('stateFilter', function() {
    return function(state) {
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

filterModel.filter('activeFilter', function() {
    return function(state) {
        return state == 'ACTIVATE' ? '有效' : '无效';
    };
});

filterModel.filter('reverseActiveFilter', function() {
    return function(state) {
        return state == 'ACTIVATE' ? '无效':'有效' ;
    };
});