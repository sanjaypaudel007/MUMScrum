<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

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
										      <td align="right">
										      	<a href="<spring:url value="/userstory/edit/${item.id}" />" title="Edit"><i class="fa fa-edit fa-fw"></i></a>
										      	<a href="<spring:url value="/userstory/detail/${item.id}" />" title="Detail"><i class="fa fa-eye fa-fw"></i></a>
										      	<a href="<spring:url value="/userstory/estimate/${item.id}" />" title="Estimate"><i class="fa fa-clock-o fa-fw"></i></a>
										    </tr>
										    </c:forEach>
                                        
                                    </tbody>
                                </table>
                            </div>
			</div>
		
		</div>
	</div>
		
		
		
		
		
		
		
		