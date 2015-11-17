$(document).ready(function(){
	
	$("[data-action='delete-toggle']").on("click",function(){
		if(!confirm("Are you sure to delete record?")) return false;		
		
	});
	
});