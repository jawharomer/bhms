<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div>
	<sf:form id="add-operation-form" method="POST" commandName="operation"
		onsubmit="addOperation(event)">
		<table class="w-100">
			<tbody>

				<tr>
					<td>Name</td>
					<td><sf:input cssClass="form-control form-control-sm"
							path="name" /></td>
					<td><sf:errors path="name" /></td>
				</tr>

				<tr>
					<td>Price</td>
					<td><sf:input cssClass="form-control form-control-sm"
							path="price" /></td>
					<td><sf:errors path="price" /></td>
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
	function addOperation(event) {
		event.preventDefault();
		console.log("addOperation->fired");

		var data = $("#add-operation-form").serializeJSON();
		console.log("data=", data);

		$.ajax({
			type : "POST",
			url : "<c:url value="/operations/add"/>",
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