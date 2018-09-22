function getAddingDoctor() {
	console.log("getAddingDoctor->fired");
	$.ajax({
		type : "GET",
		url : $$ContextURL + "/doctors/add",
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

function getEditingDoctor(doctorId) {
	console.log("getEditingDoctor->fired");
	console.log("doctorId=" + doctorId);
	$.ajax({
		type : "GET",
		url : $$ContextURL + "/doctors/edit/" + doctorId,
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

function deleteDoctor(doctorId) {
	console.log("deleteDoctor->fired");
	console.log("doctorId=" + doctorId);
	$.ajax({
		type : "POST",
		url : $$ContextURL + "/doctors/delete/" + doctorId,
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