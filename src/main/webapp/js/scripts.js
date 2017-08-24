var taskApp = angular.module('taskApp', [ 'ui.sortable' ]);

taskApp.controller('taskController', function($scope, $http) {
	$scope.task = {}
	$scope.transaction;
	$scope.loading = false;
	$scope.callAssync = function(methodIndex, path, data, success) {
		$scope.loading = true;
		var METHOD = [ "POST", "GET", "DELETE" ];
		$scope.sending = true;
		console.log($scope.userData);
		var requestData = {
			method : METHOD[methodIndex],
			url : 'rest/' + path,
			headers : {
				"Content-Type" : "application/json; charset=UTF-8"
			},

		};
		if (data)
			requestData.data = data;
		$http(requestData).then(success, function errorCallback(response) {
			alert("Ops, houve um erro tente novamente.");
			$scope.loading = false;
		});
	}

	$scope.loadTasks = function() {
		var success = function successCallback(response) {
			$scope.loading = false;
			$scope.list = response.data;
		};
		$scope.callAssync(1, "taskrest/tasklist", false, success);
	}

	$scope.save = function() {
		console.log($scope.task);
		delete $scope.task.$$hashKey;
		var success = function successCallback(response) {
			$scope.loading = false;
			$scope.list = response.data;
			$('#modal-form').modal({
				show : 'false'
			});
			location.reload();
		};
		var method = 0;
		var url;
		if ($scope.transaction) {
			url = "taskrest/update";
		} else {
			url = "taskrest/save";
		}
		$scope.callAssync(method, url, $scope.task, success);

	}

	$scope.deleteItem = function(item) {
		var remove = confirm("Deseja mesmo apagar '" + item.title + "' ?");
		if (remove) {
			var success = function successCallback(response) {
				$scope.list = response.data;
				$scope.loading = false;
				location.reload();
			};
			$scope.callAssync(2, "taskrest/remove", item, success);
		}
	}

	$scope.edit = function(item) {
		var itemString = JSON.stringify(item);
		$scope.task = JSON.parse(itemString);
		$scope.transaction = 1;
	}

	$scope.loadTasks();
	var tmpList = [];

	for (var i = 1; i <= 6; i++) {
		tmpList.push({
			text : 'Item ' + i,
			value : i
		});
	}

	$scope.list = tmpList;

	$scope.sortingLog = [];

	$scope.sortableOptions = {
		update : function(e, ui) {
			var logEntry = tmpList.map(function(i) {
				return i.value;
			}).join(', ');
			$scope.sortingLog.push('Update: ' + logEntry);
		},
		stop : function(e, ui) {
			// this callback has the changed model
			var logEntry = tmpList.map(function(i) {
				return i.value;
			}).join(', ');
			$scope.sortingLog.push('Stop: ' + logEntry);
		}
	};

});
