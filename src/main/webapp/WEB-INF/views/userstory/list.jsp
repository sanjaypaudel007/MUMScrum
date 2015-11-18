<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
	<div>
		<h4>&nbsp;</h4>
	</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
		<div class="panel-heading">User Stories
			</div>
			<div class="panel-body">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Name</th>
                                            <th>Status</th>
                                            <th>Priority</th>
                                            <th>Sprint</th>
                                            <th align="center">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach items="${list}" var="item" varStatus="count">
										    <tr id="row-${count.count}">
										      <td>${count.count}</td>
										      <td>${item.name}</td>
										      <td>${item.status}</td>
										      <td>${item.priority}</td>
										      <td>${item.sprint.name}</td>
										      <td>
										      <security:authorize access="hasRole('SCRUM_MASTER')">
										      	<c:if test="${item.status == 'NEW'}">
										      		<a href="<spring:url value="/userstory/edit/${item.id}" />" title="Edit"><i class="fa fa-edit fa-fw"></i></a>
										      	</c:if>
										      	<a title="Delete" onclick="return false;" data-action="delete-toggle" data-target="row-${count.count}" href="<spring:url value="/userstory/delete/${item.id}" />"><i class="fa  fa-trash-o fa-fw"></i></a>
										      </security:authorize>
										      
										      <security:authorize access="hasAnyRole('SCRUM_MASTER', 'DEVELOPER', 'TESTER')">
										      	<a href="<spring:url value="/userstory/detail/${item.id}" />" title="Detail"><i class="fa fa-eye fa-fw"></i></a>
										      </security:authorize>
										      
										      <security:authorize access="hasAnyRole('DEVELOPER', 'TESTER')">
										      	<c:if test="${(item.developer.username == username || item.tester.username == username) && (item.status == 'ASSIGNED' || item.status == 'ESTIMATED')}">
										      		<a href="<spring:url value="/userstory/estimate/${item.id}" />" title="Estimate"><i class="fa fa-clock-o fa-fw"></i></a>
										      	</c:if>
										      </security:authorize>
										    </tr>
										    </c:forEach>
                                        
                                    </tbody>
                                </table>
                            </div>
			</div>
		
		</div>
	</div>
		
		
		
		
		
		
		
		