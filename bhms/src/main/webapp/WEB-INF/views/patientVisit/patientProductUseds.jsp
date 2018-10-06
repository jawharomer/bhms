<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<div>
	<div class="py-2">
		<h3>Patient Product Used</h3>
	</div>

	<div>
		<form action="<c:url value="/patientVisits/patientProductUseds" />">
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

	<div>


		<table id="productUsedTable" class="display nowrap">
			<thead>
				<tr>
					<th>VisitID</th>
					<th>Patient Name</th>
					<th>Time</th>
					<th>Product Code</th>
					<th>Product Name</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${patientVisits}" var="item">
					<tr>

						<c:forEach items="${item.patientProductUseds}" var="productItem">
							<tr>
								<td>${item.id}</td>
								<td>${item.patient.fullName}</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss"
										value="${item.time}" /></td>
								<td>${productItem.code}</td>
								<td>${productItem.name}</td>
							</tr>
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
			<thead>
				<tr>
					<th>VisitID</th>
					<th>Patient Name</th>
					<th>Time</th>
					<th>Product Code</th>
					<th>Product Name</th>
				</tr>
			</thead>
		</table>
	</div>

</div>