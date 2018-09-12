<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div id="edit-expense-category-container">
	<sf:form id="edit-expense-category-form" commandName="expenseCategory"
		onsubmit="editExpenseCategory(event)">
		<sf:input type="hidden" path="id" />
		<table class="w-100">
			<tbody>
				<tr>
					<td>Name</td>
					<td><sf:input cssClass="form-control form-control-sm"
							path="name" /></td>
					<td><sf:errors path="name" /></td>
				</tr>

				<tr>
					<td>
						<button class="btn btn-sm  btn-warning">
							<i class="fa fa-edit"></i>
						</button>
					</td>

				</tr>

			</tbody>

		</table>


	</sf:form>

</div>


<script>
	function editExpenseCategory(event) {
		event.preventDefault();
		console.log("editExpenseCategory->fired");
		var data = $("#edit-expense-category-form").serializeJSON();
		console.log("data=", data);
		$.ajax({
			type : "POST",
			url : "<c:url value="/expenseCategories/add"/>",
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