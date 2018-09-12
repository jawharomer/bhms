<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="income-container" class="p-1">
	<div class="p-1">
		<button class="btn btn-success" onclick="window.print()">
			<i class="fa fa-print"></i>
		</button>
	</div>

	<div id="section-to-print">
		<p>Income Invoice</p>
		<table class="table table-bordered w-100">

			<tr>
				<td width="20%">#</td>
				<td>${income.id}</td>
			</tr>
			<tr>
				<td>Amount</td>
				<td><fmt:formatNumber value="${income.amount}"
						maxFractionDigits="2" /></td>
			</tr>

			<tr>
				<td>Time</td>
				<td><fmt:formatDate value="${income.time}"
						pattern="yyyy-MM-dd hh:mm:ss" /></td>
			</tr>


			<tr>
				<td>Received From</td>
				<td>${income.receivedFrom}</td>
			</tr>

			<tr>
				<td>Income Category</td>
				<td>${income.incomeCategory.name}</td>
			</tr>

			<tr>
				<td>Reference</td>
				<td>${income.reference}</td>
			</tr>

			<tr>
				<td>Note</td>
				<td>${income.note}</td>
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