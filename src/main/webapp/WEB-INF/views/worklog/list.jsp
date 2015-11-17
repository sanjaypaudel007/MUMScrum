<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="col-lg-12">
	<h4>&nbsp;</h4>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Estimation History</div>
			<div class="panel-body" style="font-size: 85%;">
				<table class="table">
					<thead>
						<tr>
							<th width="5%">#</th>
							<th>User Story</th>
							<th width="17%">Date</th>
							<th width="23%">Estimate</th>
						</tr>
					</thead>
					<tbody id="membertablelist">
						<c:forEach items="${list}" var="item" varStatus="count">
							<tr id="row-${count.count}">
								<td>${count.count}</td>
								<td>${item.userStory.name }</td>
								<td><fmt:formatDate value="${item.date}"
										pattern="MM/dd/yyyy" /></td>
								<td>${item.remainingWorkEstimation}</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>







