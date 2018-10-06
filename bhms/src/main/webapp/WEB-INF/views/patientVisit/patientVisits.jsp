<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<div>
	<div class="py-2">
		<h3>Patient Visits</h3>
	</div>

	<div>
		<form action="<c:url value="/patientVisits" />">
			<table>
				<tr>
					<td class="text-left">From</td>
					<td><input class="form-control" id="from" name="from"
						value="<fmt:formatDate pattern = "yyyy-MM-dd"  
         value = "${from}" />" /></td>
				</tr>

				<tr>
					<td class="text-left">To</td>
					<td><input class="form-control" id="to" name="to"
						value="<fmt:formatDate pattern = "yyyy-MM-dd"  
         value = "${to}" />" /></td>
				</tr>
				<tr>
					<td><input class="btn btn-outline-info" type="submit"
						value="View" /></td>
				</tr>
			</table>
		</form>


	</div>

	<hr>


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