<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<div>
	<div class="py-2">
		<h3>Patient Visits</h3>
		<button class="btn btn-success" onclick="getAddingPatient()">
			<i class="fa fa-plus"></i>
		</button>

	</div>


	<table id="patients-table" class="display nowrap">
		<thead>
			<tr>
				<th>#</th>
				<th>FullName</th>
				<th>Time</th>
				<th>T-Price</th>
				<th>T-Payment</th>
				<th>T-Unpaid</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${patientVisits}" var="item">
				<tr>
					<td>${item.id}</td>
					<td>${item.patient.fullName}</td>
					<td><fmt:formatDate value="${item.time}"
							pattern="yyyy-MM-dd hh:mm:ss" /></td>

					<c:set var="totalPrice" value="${0}" />
					<c:forEach items="${item.patientOperations}" var="oItem">
						<c:set var="totalPrice" value="${totalPrice+oItem.price}" />
					</c:forEach>
					<td>${totalPrice}</td>

					<c:set var="totalPayment" value="${0}" />
					<c:forEach items="${item.visitPayments}" var="pItem">
						<c:set var="totalPayment"
							value="${totalPayment+pItem.paymentAmount}" />
					</c:forEach>
					<td><a class="btn btn-sm btn-info"
						href="<c:url value="/visitPayments/patientVisit/" />${item.id}">
							<i class="fa fa-eye"></i>
					</a> &nbsp; ${totalPayment}</td>
					<td>${totalPrice-totalPayment}</td>

				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>