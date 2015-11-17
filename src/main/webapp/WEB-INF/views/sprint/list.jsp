<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<div class="col-lg-12">
		<h4>&nbsp;</h4>
	</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
		<div class="panel-heading">Sprints
			</div>
			<div class="panel-body">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Name</th>
                                            <th>Description</th>
                                            <th>Date</th>
                                            <th>Status</th>
                                            <th>Release Backlog</th>
                                            <th align="center">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach items="${list}" var="item" varStatus="count">
										    <tr id="row-${count.count}">
										      <td>${count.count}</td>
										      <td>${item.name}</td>
										      <td>${item.description}</td>
										      
										      <td><fmt:formatDate value="${item.startDate }" pattern="MM/dd/yyyy"/>  - <fmt:formatDate value="${item.endDate }" pattern="MM/dd/yyyy"/></td>
										      <td>${item.status}</td>
										      <td>${item.releaseBacklog.name }</td>
										      <td align="right">
										      	<a href="<spring:url value="/burndown/sprint/${item.id}" />" title="View Burndown Chart"><i class="fa fa-line-chart fa-fw"></i></a>
										      	<a href="<spring:url value="/sprint/edit/${item.id}" />" title="Edit"><i class="fa fa-edit fa-fw"></i></a>
										      	<a href="<spring:url value="/sprint/detail/${item.id}" />" title="Detail"><i class="fa fa-eye fa-fw"></i></a>
										      	<a href="<spring:url value="/sprint/estimate/${item.id}" />" title="Estimate"><i class="fa fa-clock-o fa-fw"></i></a>
										      </td>
										   
										    </tr>
										    </c:forEach>
                                        
                                    </tbody>
                                </table>
                            </div>
			</div>
		
		</div>
	</div>
		
		
		
		
		
		
		
		