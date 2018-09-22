<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>
	<div class="py-2">
		<h3>Patient Visit Payments</h3>
		<button class="btn btn-success"
			onclick="getAddingVisitPayment(${patientVisitId})">
			<i class="fa fa-plus"></i>
		</button>

	</div>

	<table id="patients-table" class="display nowrap">
		<thead>
			<tr>
				<th>#</th>
				<th>Payment Amount</th>
				<th>Time</th>
				<th>Note</th>
				<th>F</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${visitPayments}" var="item">
				<tr>
					<td>${item.id}</td>
					<td>${item.paymentAmount}</td>
					<td><fmt:formatDate value="${item.time}"
							pattern="yyyy-MM-dd hh:mm:ss" /></td>
					<td>${item.note}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>