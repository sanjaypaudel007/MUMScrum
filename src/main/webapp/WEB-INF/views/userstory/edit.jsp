<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="col-lg-12">
		<h4 class="page-header">User Story ${title}</h4>
	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="row">
	<div class="col-lg-12">


	<form:form modelAttribute="userStory" method="post" class="form">
	
	<c:set var="validationErrors"><form:errors path="*"/></c:set>
	<c:if test="${not empty validationErrors}">
	<c:forEach items="${validationErrors}" var="item" >
		<p>${item}</p>
	</c:forEach>
	</c:if> 	 
	
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
				    <form:hidden path="testingEstimate" value="0" />
					<form:hidden path="developmentEstimate" value="0" />
        	</div>
        </div>
		<input class="btn btn-primary" type="submit" value="${buttonName}"/>
		<input type="button" onclick="document.location.href='<spring:url value="/userstory" />'" 
		class="btn btn-warning" value=" Cancel ">
    
	</form:form>


	</div>
</div>