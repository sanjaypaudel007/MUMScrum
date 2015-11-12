<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div>
	<div>
		<h4>Employee</h4>
	</div>
</div>
<div>
	<div>
		<div>
			<div>
				List
				<div>
					<a href="<spring:url value="/employee/register" />" type="submit">Add</a>
				</div>
			</div>
			<div>
				<div>
					<table>
						<thead>
							<tr>
								<th width="5%">#</th>
								<th width="17%">Full Name</th>
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
									<td><img
										src="<spring:url value="/resources/employeeImages/${item.imageUrl}" />"
										width="90px" /></td>
									<td align="right"><a
										href="<spring:url value="/employee/edit/${item.id}" />"
										title="Edit">Edit</a> <a
										href="<spring:url value="/employee/detail/${item.id}" />"
										title="Detail">Detail</a> <a title="Delete"
										onclick="return false;" data-action="delete-toggle"
										data-target="row-${count.count}"
										href="<spring:url value="/employee/delete/${item.id}" />">Delete</a></td>

								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
			</div>

		</div>
	</div>
</div>







