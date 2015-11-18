<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="col-lg-12">
		<h4 class="page-header">Employee Detail</h4>
	</div>
</div>
<div class="row">
	<div class="col-lg-6">
		<div class="panel panel-default">
			<div class="panel-heading">
				<i class="fa fa-list fa-fw"></i>Detail
			</div>
			<div class="panel-body">
				<div class="media">
					<div class="media-left">
						<a href="#"> <img class="media-object"
							alt=<c:out value="${employee.fullName}"></c:out>
							src="<spring:url value="/resource/employeeImages/${employee.imageUrl}" />"
							width="90px" />
						</a>
					</div>
					<div class="media-body">
						<h4 class="media-heading">
							<c:out value="${employee.fullName}"></c:out>
						</h4>
						<c:out value="${employee.address}"></c:out>
						<br> Phone:
						<c:out value="${employee.phone}"></c:out>
						<br> Email:
						<c:out value="${employee.email}"></c:out>
						<br> Date of Birth:
						<c:out value="${employee.dob}"></c:out>
						<br> Role:
						<c:forEach items="${employee.roles}" var="role">


							<c:out value="${role.role }" /> / 
								</c:forEach>

					</div>
				</div>


			</div>
		</div>
	</div>	
</div>
<input type="button" onclick="document.location.href='<spring:url value="/employee" />'" 
					class="btn btn-outline btn-primary btn-xs" value="Back to List ">
  

