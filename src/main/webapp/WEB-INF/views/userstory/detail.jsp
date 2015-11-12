<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="col-lg-12">
		<h4 class="page-header">User Story Detail</h4>
	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="row">
	<div class="col-lg-6">
		<div class="panel panel-default">
			<div class="panel-heading"><i class="fa fa-list fa-fw"></i>Detail</div>
			<div class="panel-body">
				<dl class="dl-horizontal">
				  <dt>Name: </dt><dd><c:out value="${userStory.name}"></c:out></dd>
				  <dt>Description: </dt><dd><c:out value="${userStory.description}"></c:out></dd>
				  <dt>Status: </dt><dd><c:out value="${userStory.status}"></c:out></dd>
				  <dt>Priority: </dt><dd><c:out value="${userStory.priority}"></c:out></dd>
				  <dt>Release: </dt><dd><c:out value="${userStory.releaseBacklog.name}"></c:out></dd>
				  <dt>Sprint: </dt><dd><c:out value="${userStory.sprint.name}"></c:out></dd>
				  <dt>Assignee: </dt><dd><c:out value="${userStory.developer.fullName}"></c:out><br/> <c:out value="${userStory.tester.fullName}"></c:out></dd>
				</dl>
				<input type="button" onclick="document.location.href='<spring:url value="/userstory/edit/${userStory.id}" />'" 
					class="btn btn-outline btn-default btn-xs" value=" Edit ">
  
			</div>
		</div>
	</div>
 </div>
	  
