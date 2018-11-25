<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div>
	<sf:form id="add-appointment-form" method="POST"
		commandName="appointment" onsubmit="addAppointment(event)">
		<table class="w-100">
			<tbody>

				<tr>
					<td>Full Name</td>
					<td><sf:input cssClass="form-control form-control-sm"
							path="fullName" /></td>
					<td><sf:errors path="fullName" /></td>
				</tr>

				<tr>
					<td>Phone</td>
					<td><sf:input cssClass="form-control form-control-sm"
							path="phone" /></td>
					<td><sf:errors path="phone" /></td>
				</tr>

				<tr>
					<td>Visit Case</td>
					<td>
						<table>
							<tr>
								<td>Control</td>
								<td><sf:radiobutton path="visitCase" value="Control" /></td>
							</tr>

							<tr>
								<td>Hair Removal Laser</td>
								<td><sf:radiobutton path="visitCase"
										value="Hair Removal Laser" /></td>
							</tr>

							<tr>
								<td>Next Session</td>
								<td><sf:radiobutton path="visitCase" value="Next Session" /></td>
							</tr>
							<tr>
								<td>Doctor Visit</td>
								<td><sf:radiobutton path="visitCase" value="Doctor Visit" /></td>
							</tr>
						</table>
					</td>
					<td><sf:errors path="visitCase" /></td>
				</tr>

				<tr>
					<td>Date</td>
					<td><sf:input readonly="true"
							cssClass="form-control form-control-sm" path="date" /></td>
					<td><sf:errors path="date" /></td>
				</tr>

				<tr>
					<td>time</td>
					<td><sf:input type="time"
							cssClass="form-control form-control-sm" path="time" /></td>
					<td><sf:errors path="time" /></td>
				</tr>

				<tr>
					<td>
						<button class="btn btn-sm btn-success">
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
		$("#date").datepicker();

	}

	function addAppointment(event) {
		event.preventDefault();
		console.log("addAppointment->fired");

		var data = $("#add-appointment-form").serializeJSON();
		console.log("data=", data);

		$.ajax({
			type : "POST",
			url : "<c:url value="/appointments/add"/>",
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