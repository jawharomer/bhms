$(document).ready()
{
	$("#birth-date").datepicker().datepicker("setDate", new Date());

}

// Angular
app = angular.module("addPatient", []);

app.controller('addPatient', function($scope, $http) {

	$scope.patient;
	$scope.newChronicDisease;
	$scope.newHistoryOperation;
	$scope.visitReferences;
	$scope.hasAllergy;

	$scope.init = function() {
		console.log("init->fired");
		console.log("jsonPatient=", jsonPatient);
		$scope.patient = JSON.parse(jsonPatient);

		if ($scope.patient.allergy) {
			$scope.hasAllergy = true;
		} else {
			$scope.hasAllergy = false;
		}

		console.log("jsonVisitReferences=", jsonVisitReferences);
		$scope.visitReferences = JSON.parse(jsonVisitReferences);

		var availableTags = JSON.parse(jsonChronicDiseases);
		$("#newChronicDisease").autocomplete({
			source : availableTags
		});

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

		if (!$scope.hasAllergy) {
			$scope.patient.allergy = "";
		}

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
