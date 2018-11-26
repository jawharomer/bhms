<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="section-to-print">
	<div class="py-2 d-print-none">
		<h3>Visit Payments-Dr.${doctor.fullName}</h3>
	</div>

	<div>
		<form action="<c:url value="/visitPayments/doctors/" />${doctor.id}">
			<table>
				<tr>
					<td class="text-left">From</td>
					<td><input readonly="readonly" class="form-control" id="from"
						name="from"
						value="<fmt:formatDate pattern = "yyyy-MM-dd"  
         value = "${from}" />" /></td>
				</tr>

				<tr>
					<td class="text-left">To</td>
					<td><input readonly="readonly" class="form-control" id="to"
						name="to"
						value="<fmt:formatDate pattern = "yyyy-MM-dd"  
         value = "${to}" />" /></td>
				</tr>
				<tr>
					<td><input class="btn btn-outline-info" type="submit"
						value="View" /></td>
				</tr>
			</table>
		</form>
		<div class="py-1">

			<table id="patient-visit-table" class="display nowrap">
				<thead>
					<tr>
						<th>VID</th>
						<th>P.Full Name</th>
						<th>Phone</th>
						<th>Procedures</th>
						<th>Payment Time</th>
						<th>Product Used</th>
						<th>P-Cost</th>
						<th>Amount</th>
						<th>Ratio</th>
						<th>Income</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${visitPayments}" var="item">
						<tr>
							<td>${item.patientVisit.id}</td>
							<td>${item.patientVisit.patient.fullName}</td>
							<td>${item.patientVisit.patient.phone}</td>
							<c:set var="operations" value="" />
							<c:forEach items="${item.patientVisit.patientOperations}" var="i">

								<c:set var="operations" value="${operations}${i.operation} " />
							</c:forEach>
							<td title="${operations}" class="cus-note-td">${operations}</td>

							<td><fmt:formatDate value="${item.time}"
									pattern="yyyy-MM-dd hh:mm:ss" /></td>

							<td><c:set var="cost" value="${0}" /> <fmt:formatDate
									var="paymentDate" value="${item.time}" pattern="yyyy-MM-dd" />
								<c:forEach items="${item.patientVisit.patientProductUseds}"
									var="productUsed">
									<fmt:formatDate var="productUsedDate"
										value="${productUsed.time}" pattern="yyyy-MM-dd" />
									<c:if test="${paymentDate==productUsedDate}">
										<c:set var="cost" value="${cost+productUsed.cost}" />
										${productUsed.product.code},
									</c:if>
								</c:forEach>
							<td>${cost }</td>
							<td>${item.paymentAmount}</td>
							<c:forEach items="${item.patientDoctors}" var="pd">
								<c:if test="${pd.doctor.id==doctor.id}">
									<td>${pd.ratio}</td>
									<td><fmt:formatNumber maxFractionDigits="3">
									${(item.paymentAmount-cost)*pd.ratio}
									</fmt:formatNumber></td>
								</c:if>
							</c:forEach>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<th>VID</th>
						<th>P.Full Name</th>
						<th>Phone</th>
						<th>Procedures</th>
						<th>Payment Time</th>
						<th>Product Used</th>
						<th>Cost</th>
						<th>Amount</th>
						<th>Ratio</th>
						<th>Income</th>
					</tr>
				</tfoot>
			</table>
		</div>

	</div>
</div>