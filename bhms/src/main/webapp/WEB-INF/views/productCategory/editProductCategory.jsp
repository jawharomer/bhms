<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div>
	<sf:form id="edit-product-category-form" method="POST"
		commandName="productCategory" onsubmit="editProductCategory(event)">

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
	function editProductCategory(event) {
		event.preventDefault();
		console.log("editProductCategory->fired");

		var data = $("#edit-product-category-form").serializeJSON();
		console.log("data=", data);

		$.ajax({
			type : "POST",
			url : "<c:url value="/productCategories/edit"/>",
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