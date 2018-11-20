<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>
	<div class="py-2">
		<h3>Visits Detail</h3>
	</div>

	<div>
		<form action="<c:url value="/patientVisits/detail" />">
			<table>
				<tr>
					<td class="text-left">From</td>
					<td><input readonly class="form-control" id="from" name="from"
						value="<fmt:formatDate pattern = "yyyy-MM-dd"  
         value = "${from}" />" /></td>
				</tr>

				<tr>
					<td class="text-left">To</td>
					<td><input readonly class="form-control" id="to" name="to"
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
				<th>P.Name</th>
				<th>Time</th>
				<th>Procedures</th>
				<th>T-Price</th>
				<th>ProductUsed</th>
				<th>Cost</th>
				<th>T-Payment</th>
				<th>Doctors</th>
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
					<td><c:forEach items="${item.patientOperations}" var="oItem">${oItem.operation},
							<c:set var="totalPrice" value="${totalPrice+oItem.price}" />
						</c:forEach></td>
					<td>${totalPrice}</td>
					<c:set var="cost" value="${0}" />
					<td><c:forEach items="${item.patientProductUseds}"
							var="productUsed">
							<c:set var="cost" value="${cost+productUsed.cost}" />
							${productUsed.product.name},
						</c:forEach></td>
					<td>${cost}</td>
					<c:set var="totalPayment" value="${0}" />
					<c:forEach items="${item.visitPayments}" var="pItem">
						<c:set var="totalPayment"
							value="${totalPayment+pItem.paymentAmount}" />
					</c:forEach>
					<td>${totalPayment}</td>
					<td><c:forEach items="${item.patientDoctors}" var="pdItem">
					${pdItem.doctor.fullName},
					</c:forEach></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>