<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<script type="text/javascript"
	src="<spring:url value="/resource/dist/js/items_list.js" />"></script>
<div class="row">
	<div class="col-lg-12">
		<h4 class="page-header">Sprint Estimation</h4>
	</div>
	<!-- /.col-lg-12 -->
</div>
<form:form modelAttribute="userStory" method="post">
	<div class="col-lg-6">
		<div class="panel panel-info">
			<div class="panel-heading">
				<i class="fa fa-list fa-fw"></i>User Story
			</div>
			<div class="panel-body">
				<table class="table">
					<thead>
						<tr>
							<th>Name</th>
							<th>Priority</th>
							<th>Estimation</th>
						</tr>
					</thead>
					<tbody>
							<tr id="row-" style="background-color: CCFFFF;">
								<td>${userStory.name}<form:hidden
										path="id" /></td>
								<td>${userStory.priority }</td>
								<security:authorize access="hasRole('DEVELOPER')">
								<td><form:input path="developmentEstimate" /></td>
								</security:authorize>
								<security:authorize access="hasRole('TESTER')">
								<td><form:input path="testingEstimate" /></td>
								</security:authorize>
							</tr>
					</tbody>
				</table>
			</div>
		</div>
		<input type="submit" value="Estimate"
			class="btn btn-outline btn-default btn-xs" /> <input type="button"
			onclick="document.location.href='<spring:url value="/userstory/" />'"
			class="btn btn-outline btn-default btn-xs" value="Back">
	</div>
</form:form>