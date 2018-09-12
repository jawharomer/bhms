<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<div>
	<div class="py-2">
		<h3>Expenses</h3>
		<button class="btn btn-success" onclick="getAddingExpense()">
			<i class="fa fa-plus"></i>
		</button>

	</div>

	<div class="p-1">
		<form action="<c:url value="/expenses" />">
			<table class="w-100">
				<tr>
					<td>
						<div class="d-flex">
							<div class="d-flex align-items-center p-1">From</div>
							<div class="w-100">
								<input readonly="readonly" class="form-control" id="from"
									name="from"
									value="<fmt:formatDate pattern = "yyyy-MM-dd"  value = "${from}" />" />
							</div>
						</div>
					</td>
					<td>
						<div class="d-flex">
							<div class="d-flex align-items-center p-1">To</div>
							<div class="w-100">
								<input readonly="readonly" class="form-control" id="to"
									name="to"
									value="<fmt:formatDate pattern = "yyyy-MM-dd"  value = "${to}" />" />
							</div>
						</div>
					</td>
					<td>
						<div class="p-1">
							<button class="btn btn-success">
								<i class="fa fa-eye"></i>
							</button>
						</div>
					</td>
				</tr>
			</table>

		</form>

	</div>


	<table id="expenses-table" class="display nowrap">
		<thead>
			<tr>
				<th>#</th>
				<th>Time</th>
				<th>Amount</th>
				<th>Category</th>
				<th>ReceivedBy</th>
				<th>Reference</th>
				<th>Note</th>
				<th class="cus-not-export">F</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="totalAmount" value="${0}" />
			<c:forEach items="${expenses}" var="item">
				<tr>
					<td>${item.id}</td>
					<td><fmt:formatDate value="${item.time}"
							pattern="yyyy-MM-dd hh:mm:ss" /></td>
					<td><fmt:formatNumber value="${item.amount}"
							maxFractionDigits="2"></fmt:formatNumber></td>
					<td>${item.expenseCategory.name}</td>
					<td>${item.receivedBy}</td>
					<td>${item.reference}</td>
					<td class="cus-note-td" title="${item.note}">${item.note}</td>
					<td>
						<button class="btn btn-sm btn-danger"
							onclick="deleteExpense(${item.id})">
							<i class="fa fa-times"></i>
						</button> <a class="btn btn-sm btn-info"
						href="<c:url value="/expenses/"/>${item.id}"> <i
							class="fa fa-eye"></i>
					</a>

					</td>

					<c:set var="totalAmount" value="${totalAmount+item.amount}" />
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<th>#</th>
				<th>Time</th>
				<th>Amount</th>
				<th>Category</th>
				<th>ReceivedFrom</th>
				<th>Reference</th>
				<th>Note</th>
				<th class="cus-not-search">&nbsp;</th>
			</tr>

		</tfoot>

	</table>

	<div>
		Total Amount=
		<fmt:formatNumber value="${totalAmount}" maxFractionDigits="2" />
	</div>

</div>