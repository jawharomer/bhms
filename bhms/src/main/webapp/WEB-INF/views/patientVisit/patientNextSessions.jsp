<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<div>
	<div class="py-2">
		<h3>Patient Next Session</h3>
	</div>

	<div>
		<form action="<c:url value="/patientVisits/nextSession" />">
			<table>
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


	<table id="patient-visits-table" class="display nowrap">
		<thead>
			<tr>
				<th>#</th>
				<th>FullName</th>
				<th>Phone</th>
				<th>Next Session</th>
				<th>T-Price</th>
				<th>T-Payment</th>
				<th>T-Unpaid</th>
				<th>F</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${patientVisits}" var="item">
				<tr>
					<td>${item.id}</td>
					<td>${item.patient.fullName}</td>
					<td>${item.patient.phone}</td>
					<td><fmt:formatDate value="${item.nextSession}"
							pattern="yyyy-MM-dd" /></td>

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

					<td>
						<div>
							<a class="btn btn-sm btn-warning"
								href="<c:url value="/patientVisits/edit/"/>${item.id}"> <i
								class="fa fa-edit"></i>
							</a>

						</div>
					</td>

				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>