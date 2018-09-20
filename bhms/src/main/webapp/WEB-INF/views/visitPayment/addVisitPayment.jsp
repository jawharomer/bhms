<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div>
	<sf:form id="add-visit-payment-form" method="POST"
		commandName="visitPayment" onsubmit="addVisitPayment(event)">

		<input name="patientVisit[id]" value="visitPayment.patientVisit[id]">
		<table class="w-100">
			<tbody>

				<tr>
					<td>Payment Amount</td>
					<td><sf:input path="paymentAmount" /></td>
				</tr>

				<tr>
					<td>Note</td>
					<td><sf:input path="note" /></td>
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
	function addVisitPayment(event) {
		event.preventDefault();
		console.log("addVisitPayment->fired");

		var data = $("#add-visit-payment-form").serializeJSON();
		console.log("data=", data);

		$
				.ajax({
					type : "POST",
					url : "<c:url value="/classSubjects/add/classLevel/"/>${classLevelId}",
					data : JSON.stringify(data),
					contentType : "application/json",
					success : function(response) {
						$("#add-class-subject-container").html(response);
					},
					error : function(response) {
						$("#modal-body").html(response.responseText);
						$("#modal").modal("show");
					}
				});
	}
</script>