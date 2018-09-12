<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div id="add-income-container">
	<sf:form id="add-income-form" commandName="income"
		onsubmit="addIncome(event)">
		<table class="w-100">
			<tbody>
				<tr>
					<td>Amount</td>
					<td><sf:input cssClass="form-control form-control-sm"
							path="amount" /></td>
					<td><sf:errors path="amount" /></td>
				</tr>

				<tr>
					<td>Received From</td>
					<td><sf:input cssClass="form-control form-control-sm"
							path="receivedFrom" /></td>
					<td><sf:errors path="receivedFrom" /></td>
				</tr>


				<tr>
					<td>Income Category</td>
					<td><select class="form-control" name="incomeCategory[id]">
							<option value="">Choose</option>
							<c:forEach items="${incomeCategories}" var="item">
								<c:choose>
									<c:when test="${income.incomeCategory.id==item.id}">
										<option selected="selected" value="${item.id}">${item.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${item.id}">${item.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select></td>
					<td><sf:errors path="incomeCategory" /> <sf:errors
							path="incomeCategory.id" /></td>
				</tr>

				<tr>
					<td>Reference</td>
					<td><sf:input cssClass="form-control form-control-sm"
							path="reference" /></td>
					<td><sf:errors path="reference" /></td>
				</tr>
				
				
				<tr>
					<td>Note</td>
					<td><sf:textarea cssClass="form-control"
							path="note" /></td>
					<td><sf:errors path="note" /></td>
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
	function addIncome(event) {
		event.preventDefault();
		console.log("addIncome->fired");
		var data = $("#add-income-form").serializeJSON();
		console.log("data=", data);
		$.ajax({
			type : "POST",
			url : "<c:url value="/incomes/add"/>",
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