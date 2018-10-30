$(document).ready()
{
	console.log("csrf=", csrf);

	$("#expiration-date,#production-date").datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : "yy-mm-dd"
	}).datepicker("setDate", new Date());

}

// Angular

appAddOrder = angular.module("addOrder", []);

appAddOrder.factory('httpRequestInterceptor', function() {
	return {
		request : function(config) {
			config.headers['X-CSRF-TOKEN'] = csrf;
			return config;
		}
	};
});
appAddOrder.config(function($httpProvider) {
	$httpProvider.interceptors.push('httpRequestInterceptor');
});

appAddOrder
		.controller(
				'addOrder',
				function($scope, $http, $q) {

					$scope.isNumber = angular.isNumber;

					$scope.vendors;
					$scope.order;
					$scope.newOrderDetail = {
						product : {
							id : "",
							name : "",
							productCategory : {
								id : ""
							}
						},
						quantity : "",
						productionDate : "",
						expirationDate : "",
						paymentAmount : ""
					};

					$scope.resetNewOrderDetail = angular
							.copy($scope.newOrderDetail);

					$scope.productCategories;

					$scope.init = function() {

						console.log("init->fired");
						console.log("jsonVendors=", jsonVendors);
						console.log("jsonOrder=", jsonOrder);
						console.log("jsonProductCategories=",
								jsonProductCategories);

						$scope.vendors = JSON.parse(jsonVendors);
						$scope.order = JSON.parse(jsonOrder);
						$scope.productCategories = JSON
								.parse(jsonProductCategories);
						console.log("$scope.vendors=", $scope.vendors);
						console.log("$scope.order=", $scope.order);
						console.log("$scope.productCategories=",
								$scope.productCategories);
					}

					$scope.totalPaymentAmount = function() {
						var totalPayment = 0;

						for (var i = 0; i < $scope.order.orderDetails.length; i++) {
							totalPayment += $scope.order.orderDetails[i].paymentAmount
						}

						return totalPayment;
					}

					$scope.getProduct = function() {
						console.log("$scope.getProduct->fired");
						console.log("$scope.newOrderDetail.product.code=",
								$scope.newOrderDetail.product.code);

						$scope.newOrderDetail.product.name = "";
						$scope.newOrderDetail.product.id = "";
						$scope.newOrderDetail.product.productCategory.id = "";

						$http(
								{
									method : 'GET',
									url : $$ContextURL
											+ '/products/code/'
											+ $scope.newOrderDetail.product.code
								})
								.then(
										function(response) {

											if (response.data) {
												console.log("response.data=",
														response.data);
												$scope.newOrderDetail.product.name = response.data.name;
												$scope.newOrderDetail.product.id = response.data.id;

												//
												$scope.newOrderDetail.product.productCategory.id = response.data.productCategory.id;
											}

										},
										function(response) {
											$("#modal-body")
													.html(response.data);
											$("#modal").modal("show");
										});

					}

					$scope.addOrderDetail = function() {
						console.log("newOrderDetail->fired");
						$scope.order.orderDetails.push($scope.newOrderDetail);
						$scope.newOrderDetail = angular
								.copy($scope.resetNewOrderDetail);

					}

					$scope.deleteOrderDetail = function(index) {
						console.log("Delete item index=", index);
						console.log("Delete item =",
								$scope.order.orderDetails[index]);
						$scope.order.orderDetails.splice(index, 1);
					}

					$scope.saveOrder = function() {
						console.log("saveOrder->fired");
						$scope.order.totalPayment = $scope.totalPaymentAmount();
						console.log("$scope.order=", $scope.order);
						$http({
							method : 'POST',
							data : $scope.order,
							url : $$ContextURL + '/orders/add'
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