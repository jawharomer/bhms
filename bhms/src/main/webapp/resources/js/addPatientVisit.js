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

});