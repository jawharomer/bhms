$(document).ready()
{
	// S-DataTable
	var table = $('#operations-table').DataTable({
		paginate : false,
		dom : 'Bfrtip',
		buttons : [ {
			extend : "excel",
			charset : 'UTF-8',
			className : "btn btn-sm  btn-outline-info",
			exportOptions : {
				columns : ':not(.cus-not-export)'
			}
		}, {
			extend : "csv",
			charset : 'UTF-8',
			className : "btn btn-sm btn-outline-info",
			exportOptions : {
				columns : ':not(.cus-not-export)'
			}
		}, {
			extend : "pdf",
			className : "btn btn-sm btn-outline-info",
			exportOptions : {
				columns : ':not(.cus-not-export)'
			}
		} ],
		bInfo : false
	});
	// E-DataTable

}

function getAddingOperation() {
	console.log("getAddingOperation->fired");
	$.ajax({
		type : "GET",
		url : $$ContextURL + "/operations/add",
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

function getEditingOperation(operationId) {
	console.log("getEditingOperation->fired");
	console.log("operationId=" + operationId);
	$.ajax({
		type : "GET",
		url : $$ContextURL + "/operations/edit/" + operationId,
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

function deleteOperation(operationId) {
	console.log("deleteOperation->fired");
	console.log("operationId=" + operationId);
	$.ajax({
		type : "POST",
		url : $$ContextURL + "/operations/delete/" + operationId,
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