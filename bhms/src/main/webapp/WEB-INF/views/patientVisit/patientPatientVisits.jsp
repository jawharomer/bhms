<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<div>
	<div class="py-2">
		<h3>Patient Visit History</h3>
	</div>
	<c:if test="${fn:length(patientVisits)>0}">
		<c:set var="patient" value="${patientVisits[0].patient}" />
		<div>
			<table class="w-100">
				<tr>
					<td>#</td>
					<td>${patient.id }</td>
				</tr>
				<tr>
					<td>Full Name</td>
					<td>${patient.fullName }</td>
				</tr>
				<tr>
					<td>Arabic Full Name</td>
					<td>${patient.arabicFullName }</td>
				</tr>
			</table>
		</div>


		<hr>
		<h6 class="text-info">Operations</h6>
		<table class="w-00 table table-bordered">
			<thead>
				<tr>
					<th>Operation</th>
					<th>Price</th>
					<th>Note</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${patientVisits}" var="patientVisit">
					<c:forEach items="${patientVisit.patientOperations}"
						var="operation">
						<tr>
							<td>${operation.operation }</td>
							<td>${operation.price }</td>
							<td class="cus-note-td">${operation.note}</td>
						</tr>

					</c:forEach>
				</c:forEach>
			</tbody>
		</table>


		<hr>
		<h6 class="text-info">Patient Product Used</h6>
		<table class="w-00 table table-bordered">
			<thead>
				<tr>
					<th>P-Code</th>
					<th>P-Name</th>
					<th>QYT</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${patientVisits}" var="patientVisit">
					<c:forEach items="${patientVisit.patientProductUseds}"
						var="productUsed">
						<tr>
							<td>${productUsed.product.code }</td>
							<td>${productUsed.product.name }</td>
							<td>${productUsed.quantity}</td>
						</tr>

					</c:forEach>
				</c:forEach>
			</tbody>
		</table>


		<hr>
		<h6 class="text-info">Patient Product Used</h6>
		<table class="w-00 table table-bordered">
			<thead>
				<tr>
					<th>Date</th>
					<th>P-Code</th>
					<th>P-Name</th>
					<th>QYT</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${patientVisits}" var="patientVisit">
					<c:forEach items="${patientVisit.patientProductUseds}"
						var="productUsed">
						<tr>
							<td><fmt:formatDate value="${patientVisit.time }"
									pattern="yyyy-MM-dd" /></td>
							<td>${productUsed.product.code }</td>
							<td>${productUsed.product.name }</td>
							<td>${productUsed.quantity}</td>
						</tr>

					</c:forEach>
				</c:forEach>
			</tbody>
		</table>


		<hr>
		<h6 class="text-info">Examinations</h6>
		<table class="w-00 table table-bordered">
			<thead>
				<tr>
					<th>Date</th>
					<th>Name</th>
					<th>Normal</th>
					<th>Result</th>
					<th>Note</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${patientVisits}" var="patientVisit">
					<c:forEach items="${patientVisit.examinations}" var="examination">
						<tr>
							<td><fmt:formatDate value="${patientVisit.time }"
									pattern="yyyy-MM-dd" /></td>
							<td>${examination.name }</td>
							<td>${examination.normal }</td>
							<td>${examination.result}</td>
							<td class="cus-note-td">${examination.note}</td>
						</tr>

					</c:forEach>
				</c:forEach>
			</tbody>
		</table>


		<hr>
		<h6 class="text-info">Treatments</h6>
		<table class="w-00 table table-bordered">
			<thead>
				<tr>
					<th>Date</th>
					<th>Name</th>
					<th>Note</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${patientVisits}" var="patientVisit">
					<c:forEach items="${patientVisit.treatments}" var="treatment">
						<tr>
							<td><fmt:formatDate value="${patientVisit.time }"
									pattern="yyyy-MM-dd" /></td>
							<td>${treatment.name }</td>
							<td class="cus-note-td">${treatment.note }</td>
						</tr>

					</c:forEach>
				</c:forEach>
			</tbody>
		</table>



		<hr>
		<h6 class="text-info">Images</h6>
		<table class="w-00 table table-bordered">
			<thead>
				<tr>
					<th>Date</th>
					<th>Operation...</th>
					<th>Note</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${patientVisits}" var="patientVisit">

					<tr>
						<td><fmt:formatDate value="${patientVisit.time }"
								pattern="yyyy-MM-dd" /></td>
						<td><c:if
								test="${fn:length(patientVisit.patientOperations)>0}">
								${ patientVisit.patientOperations[0].operation}
								</c:if></td>
						<td><c:forEach items="${patientVisit.attachedFiles}"
								var="attachedFile">
								<a data-fancybox="gallery"
									href="<c:url value="/attachedFiles/0/" />${attachedFile.id}">
									<img src="<c:url value="/attachedFiles/1/" />${attachedFile.id}">
								</a>
							</c:forEach></td>
					</tr>


				</c:forEach>
			</tbody>
		</table>




	</c:if>


</div>