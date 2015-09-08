var module = angular.module('Solution', []);

module.service('SolutionService', ['$http', function ($http) {
    this.findAll = function () {
        return $http.get('/admin/solutions');
    };

    function transform(obj) {
        var str = [];
        for (var p in obj)
            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
        return str.join("&");
    }

    var header = {
        'Content-Type': 'application/x-www-form-urlencoded'
    };

    this.updateName = function (item) {
        return $http({
            method: 'POST',
            url: '/admin/solutions/' + item.id,
            transformRequest: transform,
            data: {
                name: item.name,
            },
            headers: header
        });
    };

    this.delete = function (id) {
        return $http.delete('/admin/solutions/' + id);
    };

    this.save = function (item) {
        return $http({
            method: 'POST',
            url: '/admin/subjects/' + item.subject + '/solutions/',
            transformRequest: transform,
            data: {
                name: item.name,
            },
            headers: header
        });
    };

    this.findBySubject = function (id) {
        return $http.get('/admin/subjects/' + id + '/solutions');
    };
}]);
