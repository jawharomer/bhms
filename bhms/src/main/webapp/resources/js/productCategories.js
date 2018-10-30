function getAddingProductCategory() {
	console.log("getAddingProductCategory->fired");
	$.ajax({
		type : "GET",
		url : $$ContextURL + "/productCategories/add",
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

function getEditingProductCategory(id) {
	console.log("getEditingProductCategory->fired");
	console.log("id=" + id);
	$.ajax({
		type : "GET",
		url : $$ContextURL + "/productCategories/edit/" + id,
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

function deleteProductCategory(id) {
	console.log("deleteProductCategory->fired");
	console.log("id=" + id);
	$.ajax({
		type : "POST",
		url : $$ContextURL + "/productCategories/delete/" + id,
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