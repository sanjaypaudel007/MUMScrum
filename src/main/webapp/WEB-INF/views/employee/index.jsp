<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row">
	<div class="col-lg-12">
		<h4 class="page-header">Employee</h4>
	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">List
				<div class="pull-right"><a href="<spring:url value="/employee/register" />" class="btn btn-outline btn-primary btn-xs" type="submit">Add</a></div>
			</div>
			<div class="panel-body">
					<div class="table-responsive table-bordered">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th width="5%">#</th>
										      <th width="17%">Full Name </th>
										      <th width="23%">Username</th>
										      <th width="21%">Email</th>
										      <th width="19%">Phone</th>
										      <th width="20%">Image</th>
										      <th width="15%" align="center">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody id="membertablelist">
                                    	<c:forEach items="${employeeList}" var="item" varStatus="count">
										    <tr id="row-${count.count}">
										      <td>${count.count}</td>
										      <td>${item.firstName}&nbsp;${item.lastName}</td>
										      <td>${item.username}</td>
										      <td>${item.email}</td>
										      <td>${item.phone}</td>
										      <td><img src="<spring:url value="/resource/employeeImages/${item.imageUrl}" />" width="90px"/></td>
										      <td align="right">
										      	<a href="<spring:url value="/employee/edit/${item.id}" />" title="Edit"><i class="fa fa-edit fa-fw"></i></a>
										      	<a href="<spring:url value="/employee/detail/${item.id}" />" title="Detail"><i class="fa fa-eye fa-fw"></i></a>
										      	<a title="Delete" onclick="return false;" data-action="delete-toggle" data-target="row-${count.count}"
										      	href="<spring:url value="/employee/delete/${item.id}" />"><i class="fa  fa-trash-o fa-fw"></i></a></td>
										   
										    </tr>
										    </c:forEach>
                                        
                                    </tbody>
                                </table>
                            </div>
			</div>
		
		</div>
	</div>
</div>
		
		
		
		
		
		
		
		