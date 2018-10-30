<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<div>
	<div class="py-2">
		<h3>Patients</h3>

		<a href="<c:url value="/patients/add" />" class="btn btn-success"
			onclick="getAddingPatient()"> <i class="fa fa-plus"></i>
		</a>
	</div>

	<div>
		<form action="<c:url value="/patients" />">
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

	<hr>


	<table id="patients-table" class="display nowrap">
		<thead>
			<tr>
				<th>#</th>
				<th>Full Name</th>
				<th>Phone</th>
				<th>Birth Date</th>
				<th>Address</th>
				<th>MaritalStatus</th>
				<th>Gender</th>
				<th>V.R</th>
				<th class="cus-not-export">F</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${patients}" var="item">
				<tr>
					<td>${item.id}</td>
					<td>${item.fullName}</td>
					<td>${item.phone}</td>
					<td><fmt:formatDate value="${item.birthDate}"
							pattern="yyyy-MM-dd" /></td>
					<td>${item.address}</td>
					<td>${item.maritalStatus}</td>
					<td><c:choose>
							<c:when test="${item.gender==0}">F</c:when>
							<c:when test="${item.gender==1}">M</c:when>
						</c:choose></td>

					<td>${item.visitReference.reference}</td>
					<td><a href="<c:url value="/patientVisits/add/"/>${item.id}"
						class="btn btn-sm btn-info" title="add patient visit"> <i
							class="fa fa-ticket"></i>
					</a> <a href="<c:url value="/patients/edit/"/>${item.id}"
						class="btn btn-sm btn-warning"> <i class="fa fa-edit"></i>
					</a>
						<button class="btn btn-sm btn-danger"
							onclick="deletePatient(${item.id})">
							<i class="fa fa-times"></i>
						</button></td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<th>#</th>
				<th>Full Name</th>
				<th>Phone</th>
				<th>Birth Date</th>
				<th>Address</th>
				<th>MaritalStatus</th>
				<th>Gender</th>
				<th>V.R</th>
				<th class="cus-not-search">&nbsp;</th>
			</tr>
		</tfoot>

	</table>

</div>