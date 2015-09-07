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
