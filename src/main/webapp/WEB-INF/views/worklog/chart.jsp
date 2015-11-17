<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<div class="row">
	<div class="col-lg-12">
		<h4 class="page-header">Burndown Chart</h4>
	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Chart</div>
			<div class="panel-body">
				<% /*<div class="table-responsive table-bordered">
					<table class="table">
						<thead>
							<tr>
								<th width="5%">#</th>
								<th>Date</th>
								<th>Sprint Estimate</th>
							</tr>
						</thead>
						<tbody id="membertablelist">
							<c:forEach items="${data}" var="item" varStatus="count">
								<fmt:formatDate var="date" value="${item.key}" pattern="MM/dd/yyyy" />
								<c:set var="jData" value="${jData},['${date}',${item.value}]"></c:set>
								<tr id="row-${count.count}">
									<td>${count.count}</td>
									<td><c:out value="${date}" /></td>
									<td>${item.value}</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div> */ %>
				<!-- form method="get">
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="control-label">Product Backlog: </label>
							<select id="productBacklog" name="productBacklog"
							class="form-control cascadeDropdownn" >
							    <option value="">-- Select --</option>
							    <c:forEach var="item" items="${listProductBackLog}">
							        <option value="${item.id}"><c:out value="${item.name}"/></option>
							    </c:forEach>
							</select>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="control-label">Release Backlog: </label>
							<select id="releaseBacklog" name="releaseBacklog"
							class="form-control cascadeDropdownn" >
							    <option value="">-- Select --</option>
							    <c:forEach var="item" items="${listProductBackLog}">
							        <option value="${item.id}"><c:out value="${item.name}"/></option>
							    </c:forEach>
							</select>
						</div>
					</div>
					</div>
				</form> -->
				<!-- chart -->
				 <div id="chart1" style="margin-top:20px; margin-left:20px; width:800px; height:400px;"></div>
				 <div id="chart2" style="margin-top:20px; margin-left:20px; width:550px; height:400px;"></div>
			</div>

		</div>
	</div>
</div>
<c:forEach items="${data}" var="item" varStatus="count">
	<fmt:formatDate var="date" value="${item.key}" pattern="MM/dd/yyyy" />
	<c:set var="jData" value="${jData},['${date}',${item.value}]"></c:set>
</c:forEach>

<script type="text/javascript" src="<spring:url value="/resource/jqplot/jquery.jqplot.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resource/jqplot/plugins/jqplot.barRenderer.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resource/jqplot/plugins/jqplot.dateAxisRenderer.js" />"></script>

<script type="text/javascript" src="<spring:url value="/resource/jqplot/plugins/jqplot.logAxisRenderer.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resource/jqplot/plugins/jqplot.canvasTextRenderer.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resource/jqplot/plugins/jqplot.canvasAxisLabelRenderer.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resource/jqplot/plugins/jqplot.canvasAxisTickRenderer.js" />"></script>

<script type="text/javascript" src="<spring:url value="/resource/jqplot/plugins/jqplot.categoryAxisRenderer.js" />"></script>



<link rel="stylesheet" type="text/css" href="<spring:url value="/resource/jqplot/jquery.jqplot.css" />" />

<script type="text/javascript">


$(document).ready(function(){
	
	var line=[${fn:substringAfter(jData,",")}];
	var line2 =line;

	 var line11 = [['2015-09-18', 7], ['2015-09-19', 9], ['2015-09-20', 15], 
	             ['2015-09-21', 12], ['2015-09-22', 3], 
	             ['2015-09-23', 6], ['2015-09-24', 0]];

	 var line21 = [['2015-09-18', 7], ['2015-09-19', 9], ['2015-09-20', 15], 
	             ['2015-09-21', 12], ['2015-09-22', 3], 
	             ['2015-09-23', 6], ['2015-09-24', 0]];
 
	 var plot4 = $.jqplot('chart1', [line, line2], {
	        title: 'Burndown Chart',
	        // Turns on animatino for all series in this plot.
	        animate: true,
	        // Will animate plot on calls to plot1.replot({resetAxes:true})
	        animateReplot: true,
	        series:[{renderer:$.jqplot.BarRenderer, 
	        	rendererOptions: {
	                barWidth: 30
	            }
	        	}, {xaxis:'x2axis', yaxis:'y2axis'}],
	        axes: {
	            xaxis: {
	                renderer: $.jqplot.CategoryAxisRenderer,
	                label: 'Date',
	                labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
	                tickRenderer: $.jqplot.CanvasAxisTickRenderer,
	                tickOptions: {
	                    angle: -30
	                }, 
	                labelOptions:{
	                    fontFamily:'Helvetica',
	                    fontSize: '14pt'
	                  },

	                 base: 10, tickDistribution: 'even'
	            },
	            x2axis: {
	                renderer: $.jqplot.CategoryAxisRenderer,
	                showLabel:false,
	                //label: 'Metal',
	                labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
	                tickRenderer: $.jqplot.CanvasAxisTickRenderer,
	                tickOptions: {
	                    angle: -30
	                }, 
	                base: 10, tickDistribution: 'even'
	            },
	            yaxis: {
	                autoscale:true,
	                label: 'Hours',
	                labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
	                //tickRenderer: $.jqplot.CanvasAxisTickRenderer,
	                tickOptions: {
	                    angle: 30
	                },
	                labelOptions:{
	                    fontFamily:'Helvetica',
	                    fontSize: '14pt'
	                  },
	                  pad:0
	            },
	            y2axis: {
	                autoscale:true,
	                //label: 'Number',
	                //labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
	                //tickRenderer: $.jqplot.CanvasAxisTickRenderer,
	                tickOptions: {
	                    angle: 30
	                }, pad:0
	            }
	        },
	        highlighter: {
	            show: true, 
	            showLabel: true, 
	            tooltipAxes: 'y',
	            sizeAdjust: 7.5 , tooltipLocation : 'ne'
	        }
    });
});
</script>






