function getAddingVendor() {
	console.log("getAddingVendor->fired");
	$.ajax({
		type : "GET",
		url : $$ContextURL + "/vendors/add",
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

function getEditingVendor(id) {
	console.log("getEditingVendor->fired");
	console.log("id=" + id);
	$.ajax({
		type : "GET",
		url : $$ContextURL + "/vendors/edit/" + id,
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

function deleteVendor(id) {
	console.log("deleteVendor->fired");
	console.log("id=" + id);
	$.ajax({
		type : "POST",
		url : $$ContextURL + "/vendors/delete/" + id,
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