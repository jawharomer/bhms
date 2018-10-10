$(document)
		.ready(
				function() {

					$("#from").datepicker({
						dateFormat : "yy-mm-dd"
					}).datepicker("setDate", $("#from").val());

					$("#to").datepicker({
						dateFormat : "yy-mm-dd"
					}).datepicker("setDate", $("#to").val());

					console.log("Activate data table");

					// S-DataTable
					$('#patients-table tfoot th:not(.cus-not-search)')
							.each(
									function() {
										var title = $(this).text();
										$(this)
												.html(
														'<input class="form-control fomt-control-sm cus-inline" type="text" />');
									});

					var table = $('#patients-table').DataTable({
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
						} ],
						bInfo : false
					});

					table.columns().every(
							function() {
								var that = this;
								console.log("that=", that);
								console.log("that.search()=", that.search());

								$('input', this.footer()).on('keyup change',
										function() {
											if (that.search() !== this.value) {
												that.search(this.value).draw();
											}
										});
							});

				});

function getAddingPatient() {
	console.log("getAddingPatient->fired");
	$.ajax({
		url : $$ContextURL + '/patients/add',
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

function getEditingPatient(id) {
	console.log("getEditingPatient->fired");
	console.log("id=", id);
	$.ajax({
		url : $$ContextURL + '/patients/edit/' + id,
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

function deleteIncome(id) {
	console.log("deleteIncome->fired");
	console.log("id=" + id);

	$.when(cusConfirm()).done(function() {
		$.ajax({
			url : $$ContextURL + '/incomes/delete/' + id,
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