<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>
	<div class="py-2">
		<h3>Doctor Incomes</h3>
	</div>

	<div>
		<form action="<c:url value="/incomes/doctors" />">
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
	
	<div class="text-danger">
		<h5>Total Cost:${ productUsedCost}</h5>
	</div>

	<table id="doctor-incomes-table" class="display nowrap">
		<thead>
			<tr>
				<th>#Dr</th>
				<th>Full Name</th>
				<th>Income</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${doctorIncomeDs}" var="item">
				<tr>
					<td>${item.doctorId}</td>
					<td>${item.fullName}</td>
					<td>${item.income}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>