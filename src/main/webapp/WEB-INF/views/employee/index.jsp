<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row">
	<div class="col-lg-12">
		<h4 class="page-header">Employee List</h4>
	</div>
	<div class="pull-right">
		<input type="button"
			onclick="document.location.href='<spring:url value="/employee/register" />'"
			class="btn btn-outline btn-primary btn-xs" value="Add New Employee">
	</div>

</div>
<div>
	<div>
		<div>
			<div>
				<div></div>
			</div>
			<div>
				<div>
					<table class="table table-striped">
						<thead>
							<tr>
								<th width="5%">#</th>
								<th width="15%">Full Name</th>
								<th width="20%">Username</th>
								<th width="25%">Email</th>
								<th width="15%">Phone</th>
								<th width="10%">Image</th>
								<th width="20%">Action</th>
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
									<td><img
										src="<spring:url value="/resource/employeeImages/${item.imageUrl}" />"
										width="90px" /></td>
									<td align="center"><a
										href="<spring:url value="/employee/edit/${item.id}" />"
										title="Edit"><i class="fa fa-edit fa-fw"></i></a> <a
										href="<spring:url value="/employee/detail/${item.id}" />"
										title="Detail"><i class="fa fa-eye fa-fw"></i></a> <a
										href="<spring:url value="/employee/delete/${item.id}" />"
										title="Delete"
										onclick="return confirm('Are you sure to delete this record?')"><i
											class="fa  fa-trash-o fa-fw"></i></a></td>

								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
			</div>

		</div>
	</div>
</div>







