<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div>
	<div class="py-2">
		<button class="btn btn-success btn-sm"
			onclick="getAddingAppointment()">
			<i class="fa fa-plus"></i>
		</button>
		<h3>Appointments</h3>
	</div>

	<div>
		<form action="<c:url value="/appointments"/>">
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
	</div>
	<div>
		<c:set var="datesSize" value="${fn:length(dates)}" />
		<c:forEach items="${dates}" varStatus="loop">
			<c:if test="${loop.index>0 && loop.index<=datesSize-2}">
				<form class="d-inline-block pt-1"
					action="<c:url value="/appointments"/>">
					<input type="hidden" name="from" value="${dates[loop.index-1]}" />
					<input type="hidden" name="to" value="${dates[loop.index]}" /> <input
						class="btn btn-sm btn-info" type="submit"
						value="${dates[loop.index-1]}" />

				</form>
			</c:if>
		</c:forEach>
	</div>

	<hr>


	<table id="patients-table" class="display nowrap">
		<thead>
			<tr>
				<th>#</th>
				<th>Full Name</th>
				<th>Phone</th>
				<th>Visit Case</th>
				<th>Date</th>
				<th>Time</th>
				<th class="cus-not-export">F</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${appointments}" var="item">
				<tr>
					<td>${item.id }</td>
					<td>${item.fullName}</td>
					<td>${item.phone}</td>
					<td>${item.visitCase}</td>
					<td>${item.date}</td>
					<td><fmt:formatDate value="${item.time}" pattern="hh:mm a" /></td>
					<td><button class="btn btn-sm btn-warning"
							onclick="getEditingAppointment(${item.id})">
							<i class="fa fa-edit"></i>
						</button>
						<button class="btn btn-sm btn-danger"
							onclick="deleteAppointment(${item.id})">
							<i class="fa fa-times"></i>
						</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>