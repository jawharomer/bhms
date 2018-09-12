<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="expense-container" class="p-1">
	<div class="p-1">
		<button class="btn btn-success" onclick="window.print()">
			<i class="fa fa-print"></i>
		</button>
	</div>

	<div id="section-to-print">
		<p>Expense Invoice</p>
		<table class="table table-bordered w-100">

			<tr>
				<td width="20%">#</td>
				<td>${expense.id}</td>
			</tr>
			<tr>
				<td>Amount</td>
				<td><fmt:formatNumber value="${expense.amount}"
						maxFractionDigits="2" /></td>
			</tr>

			<tr>
				<td>Time</td>
				<td><fmt:formatDate value="${expense.time}"
						pattern="yyyy-MM-dd hh:mm:ss" /></td>
			</tr>


			<tr>
				<td>Received By</td>
				<td>${expense.receivedBy}</td>
			</tr>

			<tr>
				<td>Expense Category</td>
				<td>${expense.expenseCategory.name}</td>
			</tr>

			<tr>
				<td>Reference</td>
				<td>${expense.reference}</td>
			</tr>

			<tr>
				<td>Note</td>
				<td>${expense.note}</td>
			</tr>


			<tr>
				<td>F-Signature</td>
				<td>&nbsp;</td>
			</tr>

			<tr>
				<td>S-Signature</td>
				<td>&nbsp;</td>
			</tr>



		</table>

	</div>

</div>