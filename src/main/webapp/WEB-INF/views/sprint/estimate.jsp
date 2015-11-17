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
<form:form modelAttribute="model">
	<div class="row">
		<div class="col-lg-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<i class="fa fa-list fa-fw"></i>Sprint Detail
				</div>
				<div class="panel-body">
					<dl class="dl-horizontal">
						<dt>Sprint Name:</dt>
						<dd>
							<c:out value="${model.sprint.name}"></c:out>
							<form:hidden path="sprint.id" />
						</dd>
						<dt>Description:</dt>
						<dd>
							<c:out value="${model.sprint.description}"></c:out>
						</dd>
						<dt>Date:</dt>
						<dd>
							<fmt:formatDate value="${model.sprint.startDate }"
								pattern="MM/dd/yyyy" />
							-
							<fmt:formatDate value="${model.sprint.endDate }"
								pattern="MM/dd/yyyy" />
						</dd>
					</dl>
				</div>
			</div>
		</div>
	</div>

	<div class="col-lg-6">
		<div class="panel panel-info">
			<div class="panel-heading">
				<i class="fa fa-list fa-fw"></i>User Stories
			</div>
			<div class="panel-body">
				<table class="table">
					<thead>
						<tr>
							<th>#</th>
							<th>Name</th>
							<th>Priority</th>
							<th>Estimation</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${model.userStories}" var="item"
							varStatus="count">
							<tr id="row-${count.count}" style="background-color: CCFFFF;">
								<td>${count.count}</td>
								<td>${item.name}<form:hidden
										path="userStories[${count.index }].id" /></td>
								<td>${item.priority }</td>
								<td><form:input
										path="userStories[${count.index }].developmentEstimate" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<input type="submit" value="Estimate"
			class="btn btn-outline btn-default btn-xs" /> <input type="button"
			onclick="document.location.href='<spring:url value="/sprint/" />'"
			class="btn btn-outline btn-default btn-xs" value="Back">
	</div>
</form:form>