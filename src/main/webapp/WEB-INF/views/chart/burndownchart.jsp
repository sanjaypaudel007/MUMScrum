<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<script type="text/javascript" src="<spring:url value="/resource/jqplot/jquery.jqplot.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resource/jqplot/jqplot.logAxisRenderer.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resource/jqplot/jqplot.canvasTextRenderer.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resource/jqplot/jqplot.canvasAxisLabelRenderer.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resource/jqplot/jqplot.canvasAxisTickRenderer.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resource/jqplot/jqplot.dateAxisRenderer.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resource/jqplot/jqplot.categoryAxisRenderer.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resource/jqplot/jqplot.barRenderer.js" />"></script>


<link rel="stylesheet" type="text/css" href="<spring:url value="/resource/jqplot/jquery.jqplot.css" />" />

<script type="text/javascript">
$(document).ready(function(){
	 
    var line = [['Cup Holder Pinion Bob', 7], ['Generic Fog Lamp', 9], ['HDTV Receiver', 15], 
    ['8 Track Control Module', 12], [' Sludge Pump Fourier Modulator', 3], 
    ['Transcender/Spice Rack', 6], ['Hair Spray Danger Indicator', 18]];
 
    var line2 = [['Nickle', 28], ['Aluminum', 13], ['Xenon', 54], ['Silver', 47], 
    ['Sulfer', 16], ['Silicon', 14], ['Vanadium', 23]];
 
    var plot4 = $.jqplot('chart4', [line, line2], {
        title: 'Concern vs. Occurrance',
        series:[{renderer:$.jqplot.BarRenderer}, {xaxis:'x2axis', yaxis:'y2axis'}],
        axes: {
            xaxis: {
                renderer: $.jqplot.CategoryAxisRenderer,
                label: 'Warranty Concern',
                labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                tickOptions: {
                    angle: 30
                }
            },
            x2axis: {
                renderer: $.jqplot.CategoryAxisRenderer,
                label: 'Metal',
                labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                tickOptions: {
                    angle: 30
                }
            },
            yaxis: {
                autoscale:true,
                label: 'Occurance',
                labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                tickOptions: {
                    angle: 30
                }
            },
            y2axis: {
                autoscale:true,
                label: 'Number',
                labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                tickOptions: {
                    angle: 30
                }
            }
        }
    });
});
</script>