<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="col-lg-12">
		<h4 class="page-header">Release Backlog ${title}</h4>
	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="row">
	<div class="col-lg-12">
	<form:form modelAttribute="releaseBacklog" method="post" class="form">
		<div class="row">
           <div class="col-lg-6">
           		<spring:bind path="name">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label">Name: </label>
					    <form:input path="name" placeholder="Name" class="form-control" />
					    <form:errors path="name" class="help-block"></form:errors>
					</div>
				</spring:bind>
				<spring:bind path="description">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label">Description: </label>
					    <form:textarea path="description" placeholder="Description" class="form-control" />
					    <form:errors path="description" class="help-block"></form:errors>
					</div>
				</spring:bind>
			
				<spring:bind path="startDate">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label">Start Date: </label>
					    <div class='input-group date' id="startDatedate-picker">
		                    <form:input path="startDate" placeholder="mm/dd/yyyy" class="form-control"  />
		                    <span class="input-group-addon">
		                        <span class="glyphicon glyphicon-calendar"></span>
		                    </span>
		                </div>
					    <form:errors path="startDate" class="help-block"></form:errors>
					</div>
					 
				</spring:bind>
				<spring:bind path="deadline">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label">Deadline: </label>
					    <div class='input-group date' id="deadlinedate-picker">
		                    <form:input path="deadline" placeholder="mm/dd/yyyy" class="form-control"  />
		                    <span class="input-group-addon">
		                        <span class="glyphicon glyphicon-calendar"></span>
		                    </span>
		                </div>
					    <form:errors path="deadline" class="help-block"></form:errors>
					</div>
					 
				</spring:bind>
        	</div>
        </div>
		<input class="btn btn-primary" type="submit" value="${buttonName}"/>
		<c:if test="${buttonName == 'Update' }">
			<c:set var="cancel_url" value="/releasebacklog/detail/${releaseBacklog.id }"></c:set>
		</c:if>
		<input type="button" onclick="document.location.href='<spring:url value="${cancel_url}" />'" 
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
	
	$(function() {
		 $(function () {
             $('#deadlinedate-picker, #startDatedate-picker').datetimepicker({
            	 format: 'MM/DD/YYYY'
            	 
             });
         });
	});
	
	
});
</script>
