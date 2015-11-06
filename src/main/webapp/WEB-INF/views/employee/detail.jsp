<%-- <%@ page import="edu.mum.mumscrum.entity.ProductBacklog" %> --%>

 
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="col-lg-12">
		<h4 class="page-header">Employee Detail</h4>
	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="row">
	<div class="col-lg-6">
		<div class="panel panel-default">
			<div class="panel-heading"><i class="fa fa-list fa-fw"></i>Detail</div>
			<div class="panel-body">
				<dl class="dl-horizontal">
				  <dt>Name: </dt><dd><c:out value="${employee.fullName}"></c:out></dd>
				  <dt>Address: </dt><dd><c:out value="${employee.address}"></c:out></dd>
				  <dt>Phone: </dt><dd><c:out value="${employee.phone}"></c:out></dd>
				  <dt>Email: </dt><dd><c:out value="${employee.email}"></c:out></dd>
				  <dt>Date Of Birth: </dt><dd><c:out value="${employee.dob}"></c:out></dd>
				  <dt>Salary: </dt><dd><c:out value="${employee.salary}"></c:out></dd>
				  <dt>Image: </dt><dd><img src="<spring:url value="/resource/employeeImages/${employee.imageUrl}" />" width="90px"/></dd>
				  <dt>Role: </dt><dd><c:out value="${employee.role.name}"></c:out><br/> </dd>
				</dl>
			
				<input type="button" onclick="document.location.href='<spring:url value="/employee" />'" 
					class="btn btn-outline btn-default btn-xs" value=" List ">
  
			</div>
		</div>
	</div>
 </div>
	  
