<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
	var reportTitle = '<fmt:formatDate value="${from}" pattern="yyyy-MM-dd"/>_To_<fmt:formatDate value="${to}" pattern="yyyy-MM-dd"/>';
</script>


<div>

	<h4>Orders</h4>
	<hr>

	<div>
		<form action="<c:url value="/orders" />">
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

	<div style="overflow: auto">
		<table id="customerOrdersTable" class="display nowrap">
			<thead>
				<tr>
					<td>#</td>
					<td>Vendor</td>
					<td>Time</td>
					<td>Reference</td>
					<td>TotalPayment</td>
					<td>Note</td>
					<td class="cus-not-export">F</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${orders}" var="item">
					<tr>
						<td>${item.id}</td>

						<td>${item.vendor.name}</td>

						<td><fmt:formatDate value="${item.orderTime}"
								pattern="yyyy-MM-dd hh:mm:ss" /></td>

						<td>${item.reference}</td>

						<td><fmt:formatNumber type="number" maxFractionDigits="3"
								value="${item.totalPayment}" /></td>

						<td class="cus-note-td" title="${item.note}">${item.note}</td>

						<td><a class="btn btn-sm btn-outline-warning"
							href="<c:url value="/orders/edit/" />${item.id}"> <i
								class="fa fa-edit"></i>
						</a>
							<button class="btn btn-sm btn-outline-danger"
								onclick="deleteOrder(${item.id})">
								<i class="fa fa-times"></i>
							</button> <a class="btn btn-sm btn-outline-info"
							href="<c:url value="/orders/" />${item.id}"> <i
								class="fa fa-eye"></i>
						</a></td>
					</tr>



				</c:forEach>

			</tbody>
			<tfoot>
				<tr>
					<th>#</th>
					<th>Vendor</th>
					<th>Time</th>
					<th>Reference</th>
					<th>TotalPayment</th>
					<th>Note</th>
					<th class="cus-not-export cus-not-search">&nbsp;</th>
				</tr>
			</tfoot>


		</table>
	</div>

	<hr>

	<div>
		<span>Sum Total Payment=</span> <span> <fmt:formatNumber
				type="number" maxFractionDigits="3" value="${sumTotalPayment}" />

		</span>
	</div>

</div>