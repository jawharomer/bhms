$(document).ready()
{
	$.datepicker.setDefaults({
		minDate : 0,
		dateFormat : "yy-mm-dd",
		changeMonth : true,
		changeYear : true,
		yearRange : "-100:+10"
	});

	$("#nextSession").datepicker().datepicker("setDate",
			$("#nextSession").val());
}

// Angular
app = angular.module("addPatientVisit", []);

app
		.controller(
				'addPatientVisit',
				function($scope, $http) {

					$scope.patientVisit = {};

					$scope.operations = [];

					$scope.doctors;
					$scope.selectedDoctor;
					$scope.doctorRatio;

					$scope.doctorTotalRatio = function() {
						var totalRatio = 0;
						for (var i = 0; i < $scope.patientVisit.patientDoctors.length; i++) {
							totalRatio += $scope.patientVisit.patientDoctors[i].ratio;
						}
						return totalRatio;

					}

					$scope.products;

					//
					$scope.selectedOperation = {};
					$scope.totalPrice = function() {
						var total = 0;
						for (var i = 0; i < $scope.patientVisit.patientOperations.length; i++) {
							total = total
									+ $scope.patientVisit.patientOperations[i].price;
						}
						return total;
					}

					$scope.totalPayment = function() {
						var total = 0;
						for (var i = 0; i < $scope.patientVisit.visitPayments.length; i++) {
							total = total
									+ $scope.patientVisit.visitPayments[i].paymentAmount;
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

					$scope.resetNewProductUsed = angular
							.copy($scope.newProductUsed);

					$scope.newExamination = {
						name : "",
						result : "",
						note : ""
					};

					$scope.resetNewExamination = angular
							.copy($scope.newExamination);

					$scope.newTreatment = {
						name : "",
						note : ""
					};

					$scope.resetNewTreatment = angular
							.copy($scope.newTreatment);

					$scope.init = function() {
						console.log("init->fired");
						console.log("jsonPatientVisit=" + jsonPatientVisit);
						console.log("jsonOperations=" + jsonOperations);
						$scope.patientVisit = JSON.parse(jsonPatientVisit);

						if ($scope.patientVisit.nextSession) {
							$scope.patientVisit.hasNextSession = true;
						} else {
							$scope.patientVisit.hasNextSession = false;
						}

						$scope.operations = JSON.parse(jsonOperations);
						$scope.doctors = JSON.parse(jsonDoctors);

						// S-Operation AutoCompletion
						var operationAuto = [];

						angular.forEach($scope.operations,
								function(value, key) {
									var obj = {
										label : value.name + " " + value.price,
										value : value.name,
										data : value
									}
									operationAuto.push(obj);
								});

						$("#operation-autocomplete").autocomplete({
							source : operationAuto,
							select : function(event, ui) {
								var item = ui.item.data;
								console.log("selected item =", item);

								$scope.selectedOperation.name = item.name;
								$scope.selectedOperation.price = item.price;

								$scope.$digest();
							}
						});

						// E-Operation AutoCompletion

						$scope.products = JSON.parse(jsonProducts);

						// S-Product AutoCompletion
						var productAuto = [];

						angular.forEach($scope.products, function(value, key) {
							var obj = {
								label : value.name + " " + value.code,
								value : value.code,
								data : value
							}
							productAuto.push(obj);
						});

						$("#porduct-autocomplete").autocomplete({
							source : productAuto,
							select : function(event, ui) {
								var item = ui.item.data;
								console.log("selected item =", item);

								$scope.newProductUsed.product.code = item.code;

								$scope.$digest();
							}
						});

						// E-Product AutoCompletion

						// S-Examination AutoCompletion
						console.log("jsonExaminations=", jsonExaminations);
						var examinations = JSON.parse(jsonExaminations);
						$("#examination-name").autocomplete({
							source : examinations
						});
						// E-Examination AutoCompletion

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

					$scope.addPatientDoctor = function() {
						var newPatientDoctor = {
							doctor : $scope.selectedDoctor,
							ratio : $scope.doctorRatio
						};

						console.log("newPatientDoctor=", newPatientDoctor);

						var isExist = false;

						angular
								.forEach(
										$scope.patientVisit.patientDoctors,
										function(v, k) {
											console.log("newPatientDoctor=",
													newPatientDoctor);
											console.log("v=", v);
											if (newPatientDoctor.doctor.id == v.doctor.id) {
												isExist = true;
											}
										});
						if (!isExist) {
							$scope.patientVisit.patientDoctors
									.push(newPatientDoctor);
						} else {
							alert("Already added");
						}

						$scope.selectedDoctor = null;
						$scope.doctorRatio = null;
					}
					$scope.deletePatientDoctor = function(index) {
						$scope.patientVisit.patientDoctors.splice(index, 1);
					}

					$scope.getProduct = function(event) {
						console.log("getProduct->fired");
						if (event.which == 13) {
							console.log("$scope.newProductUsed=",
									$scope.newProductUsed);

							if ($scope.newProductUsed.product.code) {

								$http(
										{
											method : 'GET',
											url : $$ContextURL
													+ '/products/code/'
													+ $scope.newProductUsed.product.code
										})
										.then(
												function(response) {
													console.log(response);

													angular
															.copy(
																	response.data,
																	$scope.newProductUsed.product);
													$("#product-quantity")
															.focus();
													console
															.log(
																	"$scope.newProductUsed=",
																	$scope.newProductUsed);

												},
												function(response) {
													$("#modal-body").html(
															response.data);
													$("#modal").modal("show");
												});

							}
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
						console.log("patientProductUsedId=",
								patientProductUsedId);

						$http(
								{
									method : 'POST',
									data : $scope.newProductUsed,
									url : $$ContextURL + '/patientVisits/'
											+ $scope.patientVisit.id
											+ '/patientProductUsed/delete/'
											+ patientProductUsedId
								}).then(function(response) {
							console.log(response);
							$("#modal-body").html(response.data);
							$("#modal").modal("show");
						}, function(response) {
							$("#modal-body").html(response.data);
							$("#modal").modal("show");
						});

					}

					// S-Examination
					$scope.addExamination = function() {
						console.log("addExamination->fired");

						$scope.patientVisit.examinations
								.push($scope.newExamination);
						$scope.newExamination = angular
								.copy($scope.resetNewExamination);
					}
					$scope.deleteExamination = function(index) {
						$scope.patientVisit.examinations.splice(index, 1);
					}

					// E-Examination

					// S-Treatment
					$scope.addTreatment = function() {
						console.log("addTreatment->fired");

						$scope.patientVisit.treatments
								.push($scope.newTreatment);
						$scope.newTreatment = angular
								.copy($scope.resetNewTreatment);
					}
					$scope.deleteTeatment = function(index) {
						$scope.patientVisit.treatments.splice(index, 1);
					}
					// E-Treatment

					$scope.save = function() {
						console.log("save->fired");

						if (!$scope.patientVisit.hasNextSession) {
							$scope.patientVisit.nextSession = null;
						}

						console
								.log("$scope.patientVisit=",
										$scope.patientVisit);

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

						if (document.getElementById('file').files.length != 0) {
							formData.append("file", document
									.getElementById('file').files[0]);

							$http(
									{
										method : 'POST',
										url : $$ContextURL + "/patientVisits/"
												+ $scope.patientVisit.id
												+ "/attachedFile",
										headers : {
											'Content-Type' : undefined
										},
										data : formData,
										transformRequest : function(data,
												headersGetterFunction) {
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

						} else {
							alert("Please choose a file")
						}

					}

					$scope.deleteAttachedFile = function(attachedFileId) {
						console.log("deleteAttachedFile->fired");
						console.log("attachedFileId=", attachedFileId);
						$http(
								{
									method : 'POST',
									data : $scope.patientVisit,
									url : $$ContextURL + '/patientVisits/'
											+ $scope.patientVisit.id
											+ '/attachedFiles/delete/'
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
