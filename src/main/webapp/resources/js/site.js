function ToogleElementView(elmId) {

	var elm = document.getElementById(elmId).style;

	if (elm.visibility == "visible") {
		elm.visibility = "hidden";
		elm.display = "none";
	} else if (elm.visibility == "hidden") {
		elm.visibility = "visible";
		elm.display = "block";
	}

}

function ToogleElementClass(elmId, class1, class2) {

	var elmClass = document.getElementById(elmId);

	if (elmClass.className == class1) {

		elmClass.className = class2;

	} else if (elmClass.className == class2) {
		elmClass.className = class1;

	}

	return true;

}

$(document).ready(function(){
	
	$("[data-action='delete-toggle']").on("click",function(){
		if(!confirm("Are you sure to delete record?")) return false;
		var target = $(this).attr("data-target");
		$.ajax({
			
			url: $(this).attr("href"),
			type:"DELETE",
			dataType:"json",
			async: true,
			success:function(result)
			{
				if(result.success == true)
				{
					alert(result.message);
					$("#"+target).css("background-color", "red").fadeOut("slow").remove();
				}
			},
			error:function(result)
			{
				alert(result)
			}
		});
		
	});
	
});
function searchMember(thisForm)
{
	$.ajax({
		url:"/resourcemanagement/member/search/",
		method: "GET",
		dataType: "JSON",
		data: $("#frmSearch").serialize(),
		success: function(response){
			$.each(response, function(index, room){
				$("#membertablelist").append($('<td></tr>').attr("value", room.key).text(room.value));
			});
		},
		error: function(response){
			alert('Error encountered');
		}
	});

}

function getRoomsForBuilding(buildingId, roomId){		
	var id = $(buildingId).val();
	$.ajax({
		url:"../room/forbuilding/"+id,
		method: "GET",
		dataType: "JSON",		
		success: function(response){
			$(roomId).empty();
			$.each(response, function(index, room){
				$(roomId).append($('<option></option>').attr("value", room.key).text(room.value));
			});
		},
		error: function(response){
			alert('Error encountered');
			$(roomId).empty();
		}
	});
}