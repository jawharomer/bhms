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
		<tr>
			<td class="border" title="Visit Case">Visit Case</td>
			<td class="border">
				<table>
					<tr>
						<td>Control</td>
						<td><input type="radio" name="visitCase"
							ng-model="patientVisit.visitCase" value="Control" /></td>
					</tr>

					<tr>
						<td>Hair Removal Laser</td>
						<td><input type="radio" name="visitCase"
							ng-model="patientVisit.visitCase" value="Hair Removal Laser" /></td>
					</tr>

					<tr>
						<td>Next Session</td>
						<td><input type="radio" name="visitCase"
							ng-model="patientVisit.visitCase" value="Next Session" /></td>
					</tr>
					<tr>
						<td>Doctor Visit</td>
						<td><input type="radio" name="visitCase"
							ng-model="patientVisit.visitCase" value="Doctor Visit" /></td>
					</tr>
				</table>
			</td>
		</tr>
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


	<div class="py-1">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Doctor</th>
					<th>F</th>
				</tr>

				<tr ng-form name="newPatientDoctorForm">

					<th><select required name="doctor"
						class="form-control form-control-sm" ng-model="selectedDoctor">
							<option value="" selected="selected">Choose</option>
							<option ng-repeat="item in doctors" ng-value="item">
								{{item.fullName}}</option>
					</select></th>
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

	<button class="btn btn-success" ng-click="save()">
		<i class="fa fa-save"></i>
	</button>


</div>
