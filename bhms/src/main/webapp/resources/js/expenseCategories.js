function getAddingExpenseCategory() {
	console.log("getAddingExpenseCategory->fired");
	$.ajax({
		url : $$ContextURL + '/expenseCategories/add',
		type : 'GET',
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

function getEditingExpenseCategory(id) {
	console.log("getEditingExpenseCategory->fired");
	console.log("id=" + id)
	$.ajax({
		url : $$ContextURL + '/expenseCategories/edit/' + id,
		type : 'GET',
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

function deleteExpenseCategory(id) {
	console.log("deleteExpenseCategory->fired");
	console.log("id=" + id);

	$.when(cusConfirm()).done(function() {
		$.ajax({
			url : $$ContextURL + '/expenseCategories/delete/' + id,
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