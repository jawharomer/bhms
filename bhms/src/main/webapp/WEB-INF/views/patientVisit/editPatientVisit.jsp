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
			
			<td>{{patientVisit.patient.fullName}} <a
				href="<c:url value="/patients/edit/"/>{{patientVisit.patient.id}} "
				class="btn btn-sm btn-warning"> <i class="fa fa-edit"></i>
			</a>
			</td>
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
		{{selectedDoctor}}
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

	<div>
		<h5 class="text-warning">Product Used</h5>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>P-Code</th>
					<th>P-Name</th>
					<th>QYT</th>
					<th>F</th>
				</tr>
				<tr ng-form name="productUsedForm">
					<th><input ng-model="newProductUsed.product.code" required
						name="code" class="form-control form-control-sm"
						ng-blur="getProduct()"></th>
					<th><input ng-model="newProductUsed.product.name"
						readonly="readonly" required name="name"
						class="form-control form-control-sm"></th>
					<th><input type="number" min="1"
						ng-model="newProductUsed.quantity" required name="quantity"
						class="form-control form-control-sm"></th>
					<th>
						<button ng-disabled="productUsedForm.$invalid"
							class="btn btn-sm btn-success rounded-circle"
							ng-click="addProductUsed()">
							<i class="fa fa-plus"></i>
						</button>
					</th>
				</tr>

			</thead>
			<tbody>
				<tr ng-repeat="item in patientVisit.patientProductUseds">
					<td>{{item.product.code}}</td>
					<td>{{item.product.name}}</td>
					<td>{{item.quantity}}</td>
					<td>
						<button class="btn btn-sm btn-danger rounded-circle"
							ng-click="deleteProductUsed(item.id)">
							<i class="fa fa-times"></i>
						</button>
					</td>
				</tr>

			</tbody>
		</table>

	</div>


	<div>
		<h5 class="text-info">Examination</h5>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Name</th>
					<th>Normal</th>
					<th>Result</th>
					<th>Note</th>
					<th>F</th>
				</tr>
				<tr ng-form name="examinationForm">
					<th><input ng-model="newExamination.name" required name="name"
						class="form-control form-control-sm"></th>
					<th><input ng-model="newExamination.normal" required
						name="normal" class="form-control form-control-sm"></th>
					<th><input ng-model="newExamination.result" required
						name="result" class="form-control form-control-sm"></th>

					<th><input ng-model="newExamination.note"
						class="form-control form-control-sm"></th>
					<th>
						<button ng-disabled="examinationForm.$invalid"
							class="btn btn-sm btn-success rounded-circle"
							ng-click="addExamination()">
							<i class="fa fa-plus"></i>
						</button>
					</th>
				</tr>

			</thead>
			<tbody>
				<tr ng-repeat="item in patientVisit.examinations">
					<td>{{item.name}}</td>
					<td>{{item.normal}}</td>
					<td>{{item.result}}</td>
					<td class="cus-note-td" title="{{item.note}}">{{item.note}}</td>
					<td>
						<button class="btn btn-sm btn-danger rounded-circle"
							ng-click="deleteExamination(item.id)">
							<i class="fa fa-times"></i>
						</button>
					</td>
				</tr>

			</tbody>
		</table>

	</div>



	<div>
		<h5 class="text-success">Treatment</h5>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Name</th>
					<th>Note</th>
					<th>F</th>
				</tr>
				<tr ng-form name="treatmentForm">
					<th><input ng-model="newTreatment.name" required name="name"
						class="form-control form-control-sm"></th>
					<th><input ng-model="newTreatment.note"
						class="form-control form-control-sm"></th>
					<th>
						<button ng-disabled="treatmentForm.$invalid"
							class="btn btn-sm btn-success rounded-circle"
							ng-click="addTreatment()">
							<i class="fa fa-plus"></i>
						</button>
					</th>
				</tr>

			</thead>
			<tbody>
				<tr ng-repeat="item in patientVisit.treatments">
					<td>{{item.name}}</td>
					<td class="cus-note-td" title="{{item.note}}">{{item.note}}</td>
					<td>
						<button class="btn btn-sm btn-danger rounded-circle"
							ng-click="deleteTreatment(item.id)">
							<i class="fa fa-times"></i>
						</button>
					</td>
				</tr>

			</tbody>
		</table>

	</div>

	<div>
		<div class="p-2">
			<input class="form-contrl d-inline-block" id="file" type="file">
			<button class="btn btn-sm btn-success" ng-click="addAttachedFile()">
				<i class="fa fa-image"></i>
			</button>
		</div>

		<div class="p-1">

			<table style="width: auto;">
				<tr ng-repeat="item in patientVisit.attachedFiles">
					<td><a data-fancybox="gallery"
						href="<c:url value="/attachedFiles/0/" />{{item.id}}"> <img
							src="<c:url value="/attachedFiles/1/" />{{item.id}}">
					</a></td>
					<td>
						<button class="btn btn-danger btn-sm roundled-circle"
							ng-click="deleteAttachedFile(item.id)">
							<i class="fa fa-times"></i>
						</button>
					</td>
				</tr>

			</table>
		</div>
	</div>

	<div class="form-inline form-group">
		<label class="mx-1">Next Session</label> <input required
			name="nextSession" id="nextSession" readonly="readonly"
			class="form-control form-control-sm"
			ng-model="patientVisit.nextSession">
	</div>

	<button class="btn btn-warning" ng-click="save()">
		<i class="fa fa-save"></i>
	</button>



</div>
