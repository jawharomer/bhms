<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<script type="text/javascript">
	var jsonVendors = '${jsonVendors}';
	var jsonOrder = '${jsonOrder}';
	var jsonProductCategories = '${jsonProductCategories}';
	var csrf = '${_csrf}';
</script>


<div ng-app="editOrder" ng-controller="editOrder" ng-init="init()">
	<h2>Edit Order</h2>

	<table class="table table-sm cus-table-borderless">
		<tbody ng-form="orderForm">
			<tr>
				<td>Vendor</td>
				<td><select required class="form-control form-control-sm"
					ng-model="order.vendor.id"
					ng-options="item.id as item.name for item in vendors">
						<option value=""></option>
				</select></td>
			</tr>
			<tr>
				<td title="Most of the time Vendor's Invoice ID">Reference</td>
				<td><input type="number" ng-model="order.reference"
					class="form-control  form-control-sm"></td>
			</tr>

			<tr>
				<td title="Most of the time Vendor's Invoice ID">Note</td>
				<td><input ng-model="order.note"
					class="form-control  form-control-sm"></td>
			</tr>
		</tbody>
	</table>


	<div class="border-top">
		<table class="table table-bordered">
			<thead>
				<tr>
					<td>P-Code</td>
					<td>P-Name</td>
					<td>P-Category</td>
					<td>QTY</td>
					<td>PROD-Date</td>
					<td>EXP-Date</td>
					<td>Payment</td>
					<td>&nbsp;</td>
				</tr>
				<tr ng-form="newOrderDetailForm">
					<td><input required ng-model="newOrderDetail.product.code"
						class="form-control form-control-sm" ng-blur="getProduct()"></td>
					<td><input ng-readonly="newOrderDetail.product.id" required
						ng-model="newOrderDetail.product.name"
						class="form-control form-control-sm"></td>

					<td><select ng-disabled="newOrderDetail.product.id" required
						class="form-control form-control-sm"
						ng-model="newOrderDetail.product.productCategory.id"
						ng-options="item.id as item.name for item in vendors">
							<option value=""></option>
					</select></td>
					<td><input type="number" min="1" required
						ng-model="newOrderDetail.quantity"
						class="form-control form-control-sm"></td>
					<td><input readonly="true" id="production-date"
						ng-model="newOrderDetail.productionDate"
						class="form-control form-control-sm"></td>
					<td><input readonly="true" id="expiration-date"
						ng-model="newOrderDetail.expirationDate"
						class="form-control form-control-sm"></td>

					<td><input required="required"
						ng-model="newOrderDetail.paymentAmount"
						class="form-control form-control-sm"></td>
					<td>
						<button ng-disabled="newOrderDetailForm.$invalid"
							class="btn btn-success bnt-sm rounded-circle"
							ng-click="addOrderDetail()">
							<i class="fa fa-plus"></i>
						</button>
					</td>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="item in order.orderDetails">
					<td>{{item.product.code}}</td>
					<td>{{item.product.name}}</td>
					<td>{{item.product.productCategory.name}}</td>
					<td>{{item.quantity}}</td>
					<td>{{item.productionDate}}</td>
					<td>{{item.expirationDate}}</td>
					<td>{{item.paymentAmount|number}}</td>
					<td>
						<button class="btn btn-danger btn-sm rounded-circle"
							ng-click="deleteOrderDetail($index)">
							<i class="fa fa-times"></i>
						</button>
					</td>
				</tr>
			</tbody>
		</table>

	</div>

	<div class="border-top pt-2">
		<div>
			Total Payment&nbsp;<input ng-value="totalPaymentAmount()|number"
				style="width: 200px"
				class="d-inline-block form-control form-control-sm"
				disabled="disabled">
		</div>

		<button
			ng-disabled="!order||!order.orderDetails.length>0||orderForm.$invalid"
			class="btn btn-warning" ng-click="saveOrder()">
			<i class="fa fa-edit"></i>
		</button>
	</div>

</div>