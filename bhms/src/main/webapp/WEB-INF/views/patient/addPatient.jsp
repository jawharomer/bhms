<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div id="add-patient-container">
	<sf:form id="add-patient-form" commandName="patient"
		onsubmit="addPatient(event)">
		<table class="w-100">
			<tbody>


				<tr>
					<td>Full Name</td>
					<td><sf:input cssClass="form-control form-control-sm"
							path="fullName" /></td>
					<td><sf:errors path="fullName" /></td>
				</tr>

				<tr>
					<td>BirthDate</td>
					<td><sf:input readonly="true"
							cssClass="form-control form-control-sm" path="birthDate" /></td>
					<td><sf:errors path="birthDate" /></td>
				</tr>


				<tr>
					<td>Phone</td>
					<td><sf:input cssClass="form-control form-control-sm"
							path="phone" /></td>
					<td><sf:errors path="phone" /></td>
				</tr>



				<tr>
					<td>Gender</td>
					<td>
						<div class="form-check form-check-inline">
							<sf:radiobutton path="gender" label="Female" value="${0}" />
						</div>
						<div class="form-check form-check-inline">
							<sf:radiobutton path="gender" label="Male" value="${1}" />
						</div>
					</td>
					<td><sf:errors path="gender" /></td>
				</tr>

				<tr>
					<td>Address</td>
					<td><sf:input cssClass="form-control form-control-sm"
							path="address" /></td>
					<td><sf:errors path="address" /></td>
				</tr>

				<tr>
					<td>VisitReference</td>
					<td><select class="form-control form-control-sm"
						name="visitReference[id]">
							<option>Choose</option>
							<c:forEach items="${visitReferences}" var="item">
								<c:choose>
									<c:when test="${patient.visitReference.id==item.id}">
										<option selected="selected" value="${item.id}">${item.reference}</option>
									</c:when>
									<c:otherwise>
										<option value="${item.id}">${item.reference}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select></td>
					<td><sf:errors path="visitReference" /> <sf:errors
							path="visitReference.id" /></td>
				</tr>

				<tr>
					<td>
						<button class="btn btn-sm  btn-success">
							<i class="fa fa-plus"></i>
						</button>
					</td>

				</tr>

			</tbody>

		</table>


	</sf:form>

</div>


<script>
	$(document).ready()
	{
		$("#birthDate").datepicker({
			dateFormat : "yy-mm-dd"
		}).datepicker("setDate", new Date());

		$("#ui-datepicker-div").appendTo("#modal");
	}

	function addPatient(event) {
		event.preventDefault();
		console.log("addPatient->fired");
		var data = $("#add-patient-form").serializeJSON();
		console.log("data=", data);
		$.ajax({
			type : "POST",
			url : "<c:url value="/patients/add"/>",
			data : JSON.stringify(data),
			contentType : "application/json",
			success : function(response) {
				$("#modal-body").html(response);
				$("#modal").modal("show");
			},
			error : function(response) {
				$("#modal-body").html(response.responseText);
				$("#modal").modal("show");
			}
		});
	}
</script>