// Angular
app = angular.module("addPatientVisit", []);

app.controller('addPatientVisit', function($scope, $http) {

	$scope.patientVisit = {};

	$scope.operations = [];
	
	$scope.resetNewProductUsed = angular.copy($scope.newProductUsed);

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

	$scope.save = function() {
		console.log("save->fired");
		console.log("$scope.patientVisit=", $scope.patientVisit);

		$http({
			method : 'POST',
			data : $scope.patientVisit,
			url : $$ContextURL + '/patientVisits/add'
		}).then(
				function(response) {
					console.log(response);
					if (response.data.status == 200) {
						$("#modal-body").html(response.data.message);
						$("#modal").modal("show");
						setTimeout(function() {
							window.location.href = $$ContextURL
									+ '/patientVisits/edit/'
									+ response.data.etc;
						}, 1000);

					}
				}, function(response) {
					$("#modal-body").html(response.data);
					$("#modal").modal("show");
				});

	}

});
