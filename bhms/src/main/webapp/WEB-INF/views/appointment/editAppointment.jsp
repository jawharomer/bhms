<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div>
	<sf:form id="add-appointment-form" method="POST"
		commandName="appointment" onsubmit="addAppointment(event)">

		<sf:input type="hidden" path="id" />
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
					<td>Date</td>
					<td><sf:input readonly="true" cssClass="form-control form-control-sm"
							path="date" /></td>
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
						<button class="btn btn-sm btn-warning">
							<i class="fa fa-edit"></i>
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