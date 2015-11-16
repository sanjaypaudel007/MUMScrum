<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
	<div>
		<h4>&nbsp;</h4>
	</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
		<div class="panel-body">
				<security:authorize access="hasRole('SCRUM_MASTER')"> 
					<div class="pull-right">
						<a href="<spring:url value="/releasebacklog/add" />"
							class="btn btn-outline btn-primary btn-xs" type="submit">Add</a>
					</div>
				</security:authorize>
				
			<div class="panel-heading">Release Backlogs
			</div>
			
				
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Name</th>
                                            <th>Description</th>
                                            <th>Status</th>
                                            <th>Date</th>
                                            <th>Product Backlog</th>
                                            <th align="center">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach items="${list}" var="item" varStatus="count">
										    <tr id="row-${count.count}">
										      <td>${count.count}</td>
										      <td>${item.name}</td>
										      <td>${item.description}</td>
										      <td>${item.status }</td>
										      <td><fmt:formatDate value="${item.startDate }" pattern="MM/dd/yyyy"/>  - <fmt:formatDate value="${item.deadline }" pattern="MM/dd/yyyy"/></td>
										      <td><c:out value="${item.productBacklog.name}"/></td>
										      <td align="right">
										      	<a href="<spring:url value="/releasebacklog/edit/${item.id}" />" title="Edit"><i class="fa fa-edit fa-fw"></i></a>
										      	<a href="<spring:url value="/releasebacklog/detail/${item.id}" />" title="Detail"><i class="fa fa-eye fa-fw"></i></a>
										      </td>
										   
										    </tr>
										    </c:forEach>
                                        
                                    </tbody>
                                </table>
                            </div>
			</div>
		
		</div>
</div>
		
		
		
		
		
		
		
		