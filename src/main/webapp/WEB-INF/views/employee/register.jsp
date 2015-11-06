<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="col-lg-12">
		<h4 class="page-header">Employee Registration</h4>
	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="row">
	<div class="col-lg-12">


	<form:form method="post" modelAttribute="userform" enctype="multipart/form-data" autocomplete="off" class="form">
	
	<c:set var="validationErrors"><form:errors path="*"/></c:set>
	<c:if test="${not empty validationErrors}">
	<c:forEach items="${validationErrors}" var="item" >
		<p>${item}</p>
	</c:forEach>
	</c:if> 	 
	
		<div class="row">
           <div class="col-lg-6">
           		<spring:bind path="firstName">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label">First Name: </label>
					    <form:input path="firstName" placeholder="First Name" class="form-control" />
					    <form:errors path="firstName" class="help-block"></form:errors>
					</div>
				</spring:bind>
				<spring:bind path="lastName">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label">Last Name: </label>
					    <form:input path="lastName" placeholder="Last Name" class="form-control" />
					    <form:errors path="lastName" class="help-block"></form:errors>
					</div>
				</spring:bind>
				<spring:bind path="dob">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label">DOB: </label>
					    <div class='input-group date' id="dobdate-picker">
		                    <form:input path="dob" placeholder="DOB dd/mm/yyyy" class="form-control"  />
		                    <span class="input-group-addon">
		                        <span class="glyphicon glyphicon-calendar"></span>
		                    </span>
		                </div>
					    <form:errors path="dob" class="help-block"></form:errors>
					</div>
					 
				</spring:bind>
				
        	</div>
        	<div class="col-lg-6">
        		<spring:bind path="email">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label">Email: </label>
					    <form:input path="email" placeholder="Email" class="form-control" />
					    <form:errors path="email" class="help-block"></form:errors>
					</div>
				</spring:bind>
           		<spring:bind path="phone">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label">Phone: </label>
					    <form:input path="phone" placeholder="Phone" class="form-control" />
					    <form:errors path="phone" class="help-block"></form:errors>
					</div>
				</spring:bind>
				<spring:bind path="address">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label">Address: </label>
					    <form:textarea path="address" placeholder="Address" class="form-control" />
					    <form:errors path="address" class="help-block"></form:errors>
					</div>
				</spring:bind>
				<spring:bind path="image">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label">Image: </label>
					    <form:input path="image" type="file"/>
					    <form:errors path="image" class="help-block"></form:errors>
					</div>
				</spring:bind>
			</div>
        </div>
         <h1 class="page-header"></h1>
        <div class="row">
           <div class="col-lg-6">

					<spring:bind path="role.id">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="control-label">Role: </label>
						   <form:select id="roleid" path="role.id"
							items="${roleList}" itemValue="id" itemLabel="name"  class="form-control" ></form:select>
						    <form:errors path="role.id" class="help-block"></form:errors>
						</div>
					</spring:bind>
				
					<spring:bind path="username">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label">Username: </label>
					    <form:input path="username" placeholder="Username" class="form-control" />
					    <form:errors path="username" class="help-block"></form:errors>
					</div>
				</spring:bind>
				<spring:bind path="password">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label">Password: </label>
					    <form:password path="password" placeholder="Password" class="form-control" />
					    <form:errors path="password" class="help-block"></form:errors>
					</div>
				</spring:bind>
				<spring:bind path="rePassword">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label">Re-Password: </label>
					    <form:password path="rePassword" placeholder="Re-Password" class="form-control" />
					    <form:errors path="rePassword" class="help-block"></form:errors>
					</div>
				</spring:bind>
           </div>
        </div>
		<input class="btn btn-primary" type="submit" value="Register"/>
		<input type="button" onclick="document.location.href='<spring:url value="/employee" />'" 
		class="btn btn-warning" value=" Cancel ">
    
	</form:form>


	</div>
</div>

 <!-- datetimepicker CSS -->
 <link href="<spring:url value="/resource/bootstrap-datepicker/bootstrap-datetimepicker.css" />" rel="stylesheet" type="text/css" />
  <!-- datepicker js -->
  <script type="text/javascript" src="<spring:url value="/resource/bootstrap-datepicker/moment-with-locales.js" />"></script>
 
 <script type="text/javascript" src="<spring:url value="/resource/bootstrap-datepicker/bootstrap-datetimepicker.js" />"></script>
 
<script>
$(document).ready(function(){
	

	$("#enablePasswordChange").on("change", function() {
		if ($(this).is(":checked")) {
			$("#password, #rePassword").removeAttr("disabled");
			$("#password, #rePassword").removeAttr("readonly");
		} else {

			$("#password, #rePassword").attr("disabled", "disabled");
			$("#password, #rePassword").attr("readonly", "readonly");

		}

	}).change();
	$(function() {
		 $(function () {
             $('#dobdate-picker').datetimepicker({
            	 format: 'DD/MM/YYYY'
            	 
             });
         });
	});
	
	
});
</script>
