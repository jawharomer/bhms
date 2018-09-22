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
				<th>P-FullName</th>
				<th>Date</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${patientVisits}" var="item">
				<tr>
					<td>${item.id}</td>
					<td>${item.patient.fullName}</td>
					<td><fmt:formatDate value="${item.date}" pattern="yyyy-MM-dd" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>