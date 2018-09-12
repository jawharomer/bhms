function getAddingIncomeCategory() {
	console.log("getAddingIncomeCategory->fired");
	$.ajax({
		url : $$ContextURL + '/incomeCategories/add',
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

function getEditingIncomeCategory(id) {
	console.log("getEditingIncomeCategory->fired");
	console.log("id=" + id)
	$.ajax({
		url : $$ContextURL + '/incomeCategories/edit/' + id,
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

function deleteIncomeCategory(id) {
	console.log("deleteIncomeCategory->fired");
	console.log("id=" + id);

	$.when(cusConfirm()).done(function() {
		$.ajax({
			url : $$ContextURL + '/incomeCategories/delete/' + id,
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