<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<div>
	<div class="py-2">
		<h3>Stock</h3>

	</div>

	<div class="p-1">
		<form action="<c:url value="/orderDetails" />">
			<table class="w-100">
				<tr>
					<td>
						<div class="d-flex">
							<div class="d-flex align-items-center p-1">From</div>
							<div class="w-100">
								<input readonly="readonly" class="form-control" id="from"
									name="from"
									value="<fmt:formatDate pattern = "yyyy-MM-dd"  value = "${from}" />" />
							</div>
						</div>
					</td>
					<td>
						<div class="d-flex">
							<div class="d-flex align-items-center p-1">To</div>
							<div class="w-100">
								<input readonly="readonly" class="form-control" id="to"
									name="to"
									value="<fmt:formatDate pattern = "yyyy-MM-dd"  value = "${to}" />" />
							</div>
						</div>
					</td>
					<td>
						<div class="p-1">
							<button class="btn btn-success">
								<i class="fa fa-eye"></i>
							</button>
						</div>
					</td>
				</tr>
			</table>

		</form>

	</div>


	<table id="order-details-table" class="display nowrap">
		<thead>
			<tr>
				<th>OrderID</th>
				<th>Time</th>
				<th>P-Code</th>
				<th>P-Name</th>
				<th>Payment</th>
				<th>PROD-Date</th>
				<th>EXP-Date</th>
				<th>QYT</th>
				<th>Sold QYT</th>
				<th>Remain</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${orderDetails}" var="item">
				<tr>
					<td>${item.order.id}</td>
					<td><fmt:formatDate value="${item.order.orderTime}"
							pattern="yyyy-MM-dd hh:mm:ss" /></td>
					<td>${item.product.code}</td>
					<td>${item.product.name}</td>
					<td>${item.paymentAmount}</td>
					<td>${item.productionDate}</td>
					<td>${item.expirationDate}</td>
					<td>${item.quantity}</td>
					<td>${item.soldAmount}</td>
					<td>${item.quantity-item.soldAmount}</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<th>OrderID</th>
				<th>Time</th>
				<th>P-Code</th>
				<th>P-Name</th>
				<th>QYT</th>
				<th>Payment</th>
				<th>PROD-Date</th>
				<th>EXP-Date</th>
				<th>Sold QYT</th>
				<th>Remain</th>
			</tr>
		</tfoot>

	</table>

	<div>
		Total Amount=
		<fmt:formatNumber value="${totalAmount}" maxFractionDigits="2" />
	</div>

</div>