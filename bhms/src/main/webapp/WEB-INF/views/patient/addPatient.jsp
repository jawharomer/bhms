<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<script type="text/javascript">
	var jsonPatient = '<spring:escapeBody  javaScriptEscape="true">${jsonPatient}</spring:escapeBody>';
	var jsonChronicDiseases = '<spring:escapeBody  javaScriptEscape="true">${jsonChronicDiseases}</spring:escapeBody>';
	var jsonVisitReferences = '${jsonVisitReferences}';
</script>

<div id="add-patient-contaner" ng-app="addPatient"
	ng-controller="addPatient" ng-init="init()" ng-form name="form">
	<h4>Add Patient</h4>



	<div class="p-1 m-1">
		<sf:form commandName="patient">
			<sf:errors path="*"></sf:errors>
		</sf:form>
	</div>

	<table class="add-patient-table">
		<tr>
			<td>FullName</td>
			<td><input name="fullName" required="required"
				class="form-control form-control-sm" ng-model="patient.fullName"></td>
		</tr>


		<tr>
			<td>ArabicFullName</td>
			<td><input name="arabicFullName" required="required"
				class="form-control form-control-sm"
				ng-model="patient.arabicFullName"></td>
		</tr>

		<tr>
			<td>Phone</td>
			<td><input required name="phone"
				class="form-control form-control-sm" ng-model="patient.phone"></td>
		</tr>


		<tr>
			<td>BirthDate</td>
			<td><input required name="birthDate" id="birth-date"
				readonly="readonly" class="form-control form-control-sm"
				ng-model="patient.birthDate"></td>
		</tr>


		<tr>
			<td>Address</td>
			<td><input class="form-control form-control-sm"
				ng-model="patient.address"></td>
		</tr>

		<tr>
			<td>Visit Reference</td>
			<td><select required name="visitReference"
				class="form-control form-control-sm"
				ng-model="patient.visitReference.id"
				ng-options="a.id as a.reference for a in visitReferences">
					<option value="">Choose</option>
			</select></td>
		</tr>


		<tr>
			<td>MaritalStatus</td>
			<td class="py-1"><label> <input required="required"
					name="maritalStatus" type="radio" class="radio-inline"
					ng-model="patient.maritalStatus" value="Single"> Single
			</label> <label> <input type="radio" name="maritalStatus"
					class="radio-inline" ng-model="patient.maritalStatus"
					value="Married">Married
			</label> <label> <input type="radio" name="maritalStatus"
					class="radio-inline" ng-model="patient.maritalStatus"
					value="Widowed"> Widowed
			</label> <label> <input type="radio" name="maritalStatus"
					class="radio-inline" ng-model="patient.maritalStatus"
					value="Divorced"> Divorced
			</label>
		</tr>


		<tr>
			<td>Gender</td>
			<td><label> <input name="gender" required="required"
					type="radio" class="radio-inline" ng-model="patient.gender"
					ng-value="0"> Male
			</label> <label> <input type="radio" name="gender"
					class="radio-inline" ng-model="patient.gender" ng-value="0">Female
			</label></td>
		</tr>


		<tr>
			<td>Job</td>
			<td><input class="form-control form-control-sm"
				ng-model="patient.job"></td>
		</tr>


		<tr>
			<td>Smoking</td>
			<td><input type="checkbox" ng-model="patient.smoking"
				value="true"></td>
		</tr>
		<tr>
			<td>Drinking</td>
			<td><input type="checkbox" ng-model="patient.drinking"
				value="true"></td>
		</tr>

		<tr>
			<td class="text-info border-top">Allergy</td>
			<td class="text-info border-top">
				<div>
					<label> <input name="allergy" type="radio"
						class="radio-inline" ng-model="patient.allergy" value="Yes">Yes
					</label> <label><input name="allergy" type="radio"
						class="radio-inline" ng-model="patient.allergy" value="No">No
					</label> <label><input name="allergy" type="radio"
						class="radio-inline" ng-model="patient.allergy" value="Unkown">
						Unkown </label>
				</div>
				<div>
					<input ng-hide="!patient.allergy||patient.allergy=='No'" placeholder="Allergy Note" ng-model="patient.allergyNote" class="form-control form-control-sm">
				</div>

			</td>
		</tr>
	</table>


	<div class="border  p-1">

		<h6 class="text-secondary">Chronic Disease</h6>

		<table>
			<tr>
				<td><input id="newChronicDisease"
					class="form-control form-control-sm" ng-model="newChronicDisease"></td>
				<td>
					<button class="btn btn-success btn-sm"
						ng-click="addChronicDisease()" ng-disabled="!newChronicDisease">
						<i class="fa fa-plus"></i>
					</button>
				</td>
			</tr>

			<tr ng-repeat="item in patient.chronicDiseases track by $index">
				<td>{{item}}</td>
				<td>
					<button class="btn btn-sm btn-danger"
						ng-click="deleteChronicDisease($index)">
						<i class="fa fa-times"></i>
					</button>

				</td>
			</tr>
		</table>

	</div>



	<div class="border  p-1">

		<h6 class="text-secondary">History Operations</h6>

		<table>
			<tr>
				<td><input class="form-control form-control-sm"
					ng-model="newHistoryOperation"></td>
				<td>
					<button class="btn btn-success btn-sm"
						ng-click="addHistoryOperation()"
						ng-disabled="!newHistoryOperation">
						<i class="fa fa-plus"></i>
					</button>
				</td>
			</tr>

			<tr ng-repeat="item in patient.historyOperations track by $index">
				<td>{{item}}</td>
				<td>
					<button class="btn btn-sm btn-danger"
						ng-click="deleteHistoryOperation($index)">
						<i class="fa fa-times"></i>
					</button>

				</td>
			</tr>
		</table>

	</div>


	<div class="p-2">
		<button class="btn btn-success" ng-disabled="form.$invalid"
			ng-click="save()">
			<i class="fa fa-plus"></i>
		</button>

	</div>



</div>
