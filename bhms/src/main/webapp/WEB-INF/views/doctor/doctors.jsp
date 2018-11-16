<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="now" class="java.util.Date" />
<c:set var="tomorrow"
	value="<%=new Date(new Date().getTime() + 60 * 60 * 24 * 1000)%>" />

<fmt:formatDate var="currentDate" value="${now}" pattern="yyyy-MM-dd" />
<fmt:formatDate var="tomorrow" value="${tomorrow}" pattern="yyyy-MM-dd" />

<div>
	<div class="py-2">
		<h3>Doctors</h3>
		<button class="btn btn-success" onclick="getAddingDoctor()">
			<i class="fa fa-plus"></i>
		</button>

	</div>

	<table class="table table-bordered">
		<thead>
			<tr>
				<th>#</th>
				<th>Name</th>
				<th>Phone</th>
				<th>F</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${doctors}" var="item">
				<tr>
					<td>${item.id}</td>
					<td>${item.fullName}</td>
					<td>${item.phone}</td>
					<td>
						<button class="btn btn-sm btn-danger"
							onclick="deleteDoctor(${item.id})">
							<i class="fa fa-times"></i>
						</button>

						<button class="btn btn-sm btn-warning"
							onclick="getEditingDoctor(${item.id})">
							<i class="fa fa-edit"></i>
						</button> <a target="_blank" class="btn btn-sm btn-info"
						href="<c:url value="/visitPayments/doctors/"/>${item.id}?from=${currentDate}&to=${tomorrow}">
							
							<i class="fa fa-money"></i>
							</a>

					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>