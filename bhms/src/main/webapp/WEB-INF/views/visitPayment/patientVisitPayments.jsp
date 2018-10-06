<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="section-to-print">
	<div class="py-2 d-print-none">
		<h3>Patient Visit Payments</h3>
		<button class="btn btn-success"
			onclick="getAddingVisitPayment(${patientVisit.id})">
			<i class="fa fa-plus"></i>
		</button>
		<button class="btn btn-info" onclick="window.print()">
			<i class="fa fa-print"></i>
		</button>

	</div>

	<div class="card card-body my-2">
		<table class="w-100">
			<tr>
				<td>Visit-Id</td>
				<td>${patientVisit.id}</td>
			</tr>

			<tr>
				<td>Patient Name</td>
				<td>${patientVisit.patient.fullName}</td>
			</tr>
		</table>
	</div>

	<table id="patients-table" class="table table-bordered">
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