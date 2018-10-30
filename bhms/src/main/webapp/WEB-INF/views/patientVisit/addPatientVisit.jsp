<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>


<script type="text/javascript">
	var jsonPatientVisit = '${jsonPatientVisit}';
	var jsonOperations = '${jsonOperations}';
	var jsonDoctors = '${jsonDoctors}';
</script>

<div id="add-patient-visit-contaner" ng-app="addPatientVisit"
	ng-controller="addPatientVisit" ng-init="init()">
	<table>
		<tr>
			<td>Patient</td>
			<td>{{patientVisit.patient.fullName}}</td>
		</tr>
	</table>

	<table class="table table-borderd">
		<thead>
			<tr>
				<th>Operation</th>
				<th>Price</th>
				<th>Note</th>
				<th>F</th>
			</tr>
			<tr ng-form name="form">
				<th><select class="form-control form-control-sm"
					name="selectedOperation" required="required"
					ng-model="selectedOperation">
						<option value="">Choose</option>
						<option ng-repeat="item in operations" ng-value="item">{{item.name}}</option>
				</select></th>
				<th><input ng-disabled="!selectedOperation" type="number"
					required="required" ng-model="selectedOperation.price" name="price"
					class="form-control form-control-sm"></th>
				<th><input ng-model="selectedOperationNote"
					class="form-control form-control-sm"></th>
				<th>
					<button ng-disabled="form.$invalid"
						class="btn btn-sm btn-success rounded-circle"
						ng-click="addOperation()">
						<i class="fa fa-plus"></i>
					</button>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr ng-repeat="item in patientVisit.patientOperations">
				<td>{{item.operation}}</td>
				<td>{{item.price}}</td>
				<td>{{item.note}}</td>
				<td>
					<button class="btn btn-sm btn-danger rounded-circle"
						ng-click="deleteOperation($index)">
						<i class="fa fa-times"></i>
					</button>
				</td>
			</tr>

		</tbody>

	</table>
	<hr>
	<div class="card card-body bg-secondary">
		<table>
			<tr>
				<td>Total Price</td>
				<td>{{totalPrice()|number}}</td>
			</tr>
			<tr>
				<td>Total Payment</td>
				<td>{{totalPayment()|number}}</td>
			</tr>
		</table>
	</div>


	<div>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Doctor</th>
					<th>F</th>
				</tr>
				<tr>

					<th><select class="form-control form-control-sm"
						ng-model="selectedDoctor">
							<option value="" selected="selected">Choose</option>
							<option ng-repeat="item in doctors" ng-value="item">
								{{item.fullName}}</option>
					</select></th>
					<th>
						<button ng-disabled="!selectedDoctor"
							class="btn btn-sm btn-success rounded-circle"
							ng-click="addDoctor()">
							<i class="fa fa-plus"></i>
						</button>

					</th>
				</tr>

			</thead>
			<tbody>
				<tr ng-repeat="item in patientVisit.doctors">
					<td>{{item.fullName}}</td>
					<td>
						<button class="btn btn-sm btn-danger rounded-circle"
							ng-click="deleteDoctor($index)">
							<i class="fa fa-times"></i>
						</button>
					</td>
				</tr>
			</tbody>

		</table>

	</div>

	<button class="btn btn-success" ng-click="save()">
		<i class="fa fa-save"></i>
	</button>


</div>
