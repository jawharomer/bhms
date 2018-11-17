function getAddingVisitPayment(patientVisitId) {
	console.log("getAddingVisitPayment->fired");
	console.log("patientVisitId=" + patientVisitId);
	$.ajax({
		type : "GET",
		url : $$ContextURL + "/visitPayments/add/" + patientVisitId,
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

function deleteVisitPayment(id) {
	console.log("deleteVisitPayment->fired");
	console.log("id=" + id);

	$.when(cusConfirm()).done(function() {
		$.ajax({
			url : $$ContextURL + "/visitPayments/delete/" + id,
			type : 'POST',
			success : function(response) {
				$("#modal-body").html(response);
				$("#modal").modal("show");
			},
			error : function(response) {
				$("#modal-body").html(response.responseText);
				$("#modal").modal("show");
			}
		});
	});

}