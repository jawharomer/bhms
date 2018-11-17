<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


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

			<tr>
				<td>Procedures</td>
				<td><c:forEach items="${patientVisit.patientOperations}"
						var="item" varStatus="loop">
				${item.operation }
				<c:if
							test="${loop.index!=fn:length(patientVisit.patientOperations)-1}">
,
						</c:if>
					</c:forEach></td>
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
				<th class="d-print-none">F</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${visitPayments}" var="item">
				<tr>
					<td>${item.id}</td>
					<td>${item.paymentAmount}</td>
					<td><fmt:formatDate value="${item.time}"
							pattern="yyyy-MM-dd hh:mm:ss" /></td>
					<td class="cus-note-td" title="${item.note}">${item.note}</td>
					<td class="d-print-none">
						<button class="btn btn-sm btn-danger"
							onclick="deleteVisitPayment(${item.id})">
							<i class="fa fa-times"></i>
						</button>

					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>