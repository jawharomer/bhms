$(document).ready()
{
	$("#birth-date").datepicker().datepicker("setDate", new Date());
}

// Angular
app = angular.module("addPatient", []);

app.controller('addPatient', function($scope, $http) {

	$scope.patient;
	$scope.newAllergy;
	$scope.newChronicDisease;
	$scope.newHistoryOperation;
	$scope.visitReferences;

	$scope.init = function() {
		console.log("init->fired");
		console.log("jsonPatient=", jsonPatient);
		$scope.patient = JSON.parse(jsonPatient);

		console.log("patient=" ,$scope.patient);

		console.log("jsonVisitReferences=", jsonVisitReferences);
		$scope.visitReferences = JSON.parse(jsonVisitReferences);
	}

	$scope.addAllergy = function() {
		console.log("addAllergy->fired");
		$scope.patient.allergies.push($scope.newAllergy);
		$scope.newAllergy = "";
	}

	$scope.deleteAllergy = function(index) {
		console.log("deleteAllergy->fired");
		$scope.patient.allergies.splice(index, 1);
	}

	$scope.addChronicDisease = function() {
		console.log("newChronicDisease->fired");
		$scope.patient.chronicDiseases.push($scope.newChronicDisease);
		$scope.newChronicDisease = "";
	}

	$scope.deleteChronicDisease = function(index) {
		console.log("deleteChronicDisease->fired");
		$scope.patient.chronicDiseases.splice(index, 1);
	}

	$scope.addHistoryOperation = function() {
		console.log("addHistoryOperation->fired");
		$scope.patient.historyOperations.push($scope.newHistoryOperation);
		$scope.newHistoryOperation = "";
	}

	$scope.deleteHistoryOperation = function(index) {
		console.log("deleteHistoryOperation->fired");
		$scope.patient.historyOperations.splice(index, 1);
	}

	$scope.save = function() {
		console.log("save->fired");
		console.log("$scope.patient=", $scope.patient);

		$http({
			method : 'POST',
			data : $scope.patient,
			url : $$ContextURL + '/patients/add'
		}).then(function(response) {
			$("#modal-body").html(response.data);
			$("#modal").modal("show");
		}, function(response) {
			console.log(response);
			document.open();
			document.write(response.data);
			document.close();
		});

	}

});
