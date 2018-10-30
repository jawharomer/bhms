// Angular
app = angular.module("addPatientVisit", []);

app.controller('addPatientVisit', function($scope, $http) {

	$scope.patientVisit = {};

	$scope.operations = [];

	$scope.doctors;
	$scope.selectedDoctor;

	//
	$scope.selectedOperation;
	$scope.totalPrice = function() {
		var total = 0;
		for (var i = 0; i < $scope.patientVisit.patientOperations.length; i++) {
			total = total + $scope.patientVisit.patientOperations[i].price;
		}
		return total;
	}

	$scope.totalPayment = function() {
		var total = 0;
		for (var i = 0; i < $scope.patientVisit.visitPayments.length; i++) {
			total = total + $scope.patientVisit.visitPayments[i].paymentAmount;
		}
		return total;
	}

	$scope.selectedOperationNote = "";

	$scope.newProductUsed = {
		product : {
			code : "",
			name : ""
		},
		quantity : ""
	};
	$scope.resetNewProductUsed = angular.copy($scope.newProductUsed);

	$scope.init = function() {
		console.log("init->fired");
		console.log("jsonPatientVisit=" + jsonPatientVisit);
		console.log("jsonOperations=" + jsonOperations);
		$scope.patientVisit = JSON.parse(jsonPatientVisit);
		$scope.operations = JSON.parse(jsonOperations);
		$scope.doctors = JSON.parse(jsonDoctors);

	};
	$scope.addOperation = function() {
		console.log("addOperation->fired");
		var item = {};
		item.operation = $scope.selectedOperation.name;
		item.price = $scope.selectedOperation.price;
		item.note = $scope.selectedOperationNote;

		$scope.patientVisit.patientOperations.push(item);
		$scope.selectedOperation = {};
		$scope.selectedOperationNote = "";

	}

	$scope.deleteOperation = function(index) {
		console.log("deleteOperation->fired");
		$scope.patientVisit.patientOperations.splice(index, 1);
	}

	$scope.addDoctor = function() {
		console.log("addDoctor->fired");
		if ($scope.patientVisit.doctors.indexOf($scope.selectedDoctor) == -1) {
			$scope.patientVisit.doctors.push($scope.selectedDoctor);
		}
		$scope.selectedDoctor = null;
	}
	$scope.deleteDoctor = function(index) {
		$scope.patientVisit.doctors.splice(index, 1);
	}

	$scope.getProduct = function() {
		console.log("getProduct->fired");
		console.log("$scope.newProductUsed=", $scope.newProductUsed);

		if ($scope.newProductUsed.product.code) {

			$http(
					{
						method : 'GET',
						url : $$ContextURL + '/products/code/'
								+ $scope.newProductUsed.product.code
					}).then(function(response) {
				console.log(response);

				angular.copy(response.data, $scope.newProductUsed.product);

				console.log("$scope.newProductUsed=", $scope.newProductUsed);

			}, function(response) {
				$("#modal-body").html(response.data);
				$("#modal").modal("show");
			});

		}

	}

	$scope.addProductUsed = function() {
		console.log("addProductUsed->fired");

		$http(
				{
					method : 'POST',
					data : $scope.newProductUsed,
					url : $$ContextURL + '/patientVisits/'
							+ $scope.patientVisit.id
							+ '/patientProductUsed/add'
				}).then(function(response) {
			console.log(response);
			$("#modal-body").html(response.data);
			$("#modal").modal("show");
		}, function(response) {
			$("#modal-body").html(response.data);
			$("#modal").modal("show");
		});

	}

	$scope.deleteProductUsed = function(patientProductUsedId) {
		console.log("deleteProductUsed->fired");
		console.log("patientProductUsedId=", patientProductUsedId);

		$http(
				{
					method : 'POST',
					data : $scope.newProductUsed,
					url : $$ContextURL + '/patientVisits/'
							+ $scope.patientVisit.id
							+ '/patientProductUsed/delete/' + patientProductUsedId
				}).then(function(response) {
			console.log(response);
			$("#modal-body").html(response.data);
			$("#modal").modal("show");
		}, function(response) {
			$("#modal-body").html(response.data);
			$("#modal").modal("show");
		});
		
	}

	$scope.save = function() {
		console.log("save->fired");
		console.log("$scope.patientVisit=", $scope.patientVisit);

		$http({
			method : 'POST',
			data : $scope.patientVisit,
			url : $$ContextURL + '/patientVisits/update'
		}).then(function(response) {
			console.log(response);
			$("#modal-body").html(response.data);
			$("#modal").modal("show");
		}, function(response) {
			$("#modal-body").html(response.data);
			$("#modal").modal("show");
		});

	}

	$scope.addAttachedFile = function() {
		console.log("addAttachedFile->fired");

		var formData = new FormData();
		formData.append("file", document.getElementById('file').files[0]);

		$http(
				{
					method : 'POST',
					url : $$ContextURL + "/patientVisits/"
							+ $scope.patientVisit.id + "/attachedFile",
					headers : {
						'Content-Type' : undefined
					},
					data : formData,
					transformRequest : function(data, headersGetterFunction) {
						return data;
					}
				}).then(function(response) {

			document.getElementById('file').value = "";// reset

			console.log(response);
			$("#modal-body").html(response.data);
			$("#modal").modal("show");
		}, function(response) {
			$("#modal-body").html(response.data);
			$("#modal").modal("show");
		});

	}

	$scope.deleteAttachedFile = function(attachedFileId) {
		console.log("deleteAttachedFile->fired");
		console.log("attachedFileId=", attachedFileId);
		$http(
				{
					method : 'POST',
					data : $scope.patientVisit,
					url : $$ContextURL + '/patientVisits/'
							+ $scope.patientVisit.id + '/attachedFiles/delete/'
							+ attachedFileId
				}).then(function(response) {
			console.log(response);
			$("#modal-body").html(response.data);
			$("#modal").modal("show");
		}, function(response) {
			$("#modal-body").html(response.data);
			$("#modal").modal("show");
		});

	}

});
