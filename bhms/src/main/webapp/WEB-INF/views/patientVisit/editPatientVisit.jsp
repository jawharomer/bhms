<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<script type="text/javascript">
	var jsonPatientVisit = '${jsonPatientVisit}';
	var jsonOperations = '<spring:escapeBody  javaScriptEscape="true">${jsonOperations}</spring:escapeBody>';
	var jsonProducts = '<spring:escapeBody  javaScriptEscape="true">${jsonProducts}</spring:escapeBody>';
	var jsonExaminations = '<spring:escapeBody  javaScriptEscape="true">${jsonExaminations}</spring:escapeBody>';
	var jsonDoctors = '${jsonDoctors}';
	var histry = "";
</script>

<div id="add-patient-visit-contaner" ng-app="addPatientVisit"
	ng-controller="addPatientVisit" ng-init="init()">
	<table>
		<tr>
			<td>Patient</td>

			<td>{{patientVisit.patient.fullName}} <a
				href="<c:url value="/patients/edit/"/>{{patientVisit.patient.id}} "
				class="btn btn-sm btn-warning"> <i class="fa fa-edit"></i>
			</a> <a
				href="<c:url value="/patientVisits/patient/"/>{{patientVisit.patient.id}} "
				class="btn btn-sm btn-warning"> <i class="fa fa-history"></i>
			</a>
			</td>
		</tr>

		<tr class="text-info">
			<td>Visit Case</td>
			<td>{{patientVisit.visitCase}}</td>
		</tr>
	</table>

	<table class="table table-borderd">
		<thead>
			<tr>
				<th>Procedure</th>
				<th>Price</th>
				<th>Note</th>
				<th>F</th>
			</tr>
			<tr ng-form name="form">
				<th><input class="form-control form-control-sm"
					ng-model="selectedOperation.name" id="operation-autocomplete"></th>
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
	<div class="card card-body bg-secondary text-white">
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


	<div class="py-1">
		<span> TotalRatio={{doctorTotalRatio()}} </span>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Doctor</th>
					<th>Ratio</th>
					<th>F</th>
				</tr>

				<tr ng-form name="newPatientDoctorForm">

					<th><select required name="doctor"
						class="form-control form-control-sm" ng-model="selectedDoctor">
							<option value="" selected="selected">Choose</option>
							<option ng-repeat="item in doctors" ng-value="item">
								{{item.fullName}}</option>
					</select></th>
					<th><input required name="ratio" ng-model="doctorRatio"
						class="form-control form-control-sm" type="number" max="1" min="0"></th>
					<th>
						<button ng-disabled="newPatientDoctorForm.$invalid"
							class="btn btn-sm btn-success rounded-circle"
							ng-click="addPatientDoctor()">
							<i class="fa fa-plus"></i>
						</button>

					</th>
				</tr>

			</thead>
			<tbody>
				<tr ng-repeat="item in patientVisit.patientDoctors">
					<td>{{item.doctor.fullName}}</td>
					<td>{{item.ratio|number:3}}</td>
					<td>
						<button class="btn btn-sm btn-danger rounded-circle"
							ng-click="deletePatientDoctor($index)">
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
					<th>COST</th>
					<th>F</th>
				</tr>
				<tr ng-form name="productUsedForm">
					<th><input id="porduct-autocomplete"
						ng-model="newProductUsed.product.code" required name="code"
						class="form-control form-control-sm"
						ng-keypress="getProduct($event)"></th>
					<th><input ng-model="newProductUsed.product.name"
						readonly="readonly" required name="name"
						class="form-control form-control-sm"></th>
					<th><input id="product-quantity" type="number" min="1"
						ng-model="newProductUsed.quantity" required name="quantity"
						class="form-control form-control-sm"></th>
					<th>&nbsp;</th>
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
					<td>{{item.cost}}</td>
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
		<h5 class="text-info">Investigation</h5>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Name</th>
					<th>Result</th>
					<th>Note</th>
					<th>F</th>
				</tr>
				<tr ng-form name="examinationForm">
					<th><input id="examination-name"
						ng-model="newExamination.name" required name="name"
						class="form-control form-control-sm"></th>
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
		<label class="mx-1">Next Session</label> <input type="checkbox"
			ng-model="patientVisit.hasNextSession"> <input
			ng-show="patientVisit.hasNextSession" required name="nextSession"
			id="nextSession" readonly="readonly"
			class="form-control form-control-sm"
			ng-model="patientVisit.nextSession">
	</div>

	<button class="btn btn-warning" ng-click="save()">
		<i class="fa fa-save"></i>
	</button>



</div>
