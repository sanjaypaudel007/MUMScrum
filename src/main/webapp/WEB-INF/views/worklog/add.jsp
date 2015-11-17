<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>
	<h4>&nbsp;</h4>
</div>
<div style="color: #31B404;">
	<c:out value="${message }"></c:out>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Development - Progress Tracking</div>
			<form:form modelAttribute="userStoryEstimates" method="post"
				autocomplete="off" class="form">
				<div class="panel-body">
					<table class="table">
						<thead>
							<tr>
								<th>#</th>
								<th>User Story</th>
								<th>Previous Day's Estimation</th>
								<th>New Estimation(
									<i> as of <span style="text-decoration: underline;"><fmt:formatDate value="${userStoryEstimates.today}" pattern="MM/dd/yyyy" /></span></i>
									<form:hidden path="today" />)
								</th>
							</tr>
						</thead>
						<c:forEach items="${userStoryEstimates.entries }" var="item"
							varStatus="count">
							<tr id="row-${count.count}">
								<td>${count.count}</td>
								<td>${item.userStory.name}<form:hidden path="entries[${count.index }].userStory.id" /></td>
								<td>
									<c:if test="${item.lastLogDate != null}">
             							${item.lastLogEstimation }<i> (as of <fmt:formatDate value="${item.lastLogDate }" pattern="MM/dd/yyyy" />) </i>
									</c:if>
								</td>
								<td><form:input path="entries[${count.index }].newEstimation" class="form-control" /></td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="4">
								<input class="btn btn-primary" type="submit" value="Save" /> 
								<input type="button" onclick="document.location.href='<spring:url value="/" />'" class="btn btn-warning" value=" Close"> 
							</td>
						</tr>
					</table>
				</div>

			</form:form>
		</div>
	</div>
</div>
<jsp:include page="list.jsp" />

