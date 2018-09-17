<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<div>
	<div class="py-2">
		<h3>Patients</h3>
		<button class="btn btn-success" onclick="getAddingPatient()">
			<i class="fa fa-plus"></i>
		</button>

	</div>


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
					<td>d</td>
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