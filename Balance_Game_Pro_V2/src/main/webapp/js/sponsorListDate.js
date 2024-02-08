console.log("[로그] sponsorList.js");
$(document).ready(function(){
	$("#btnDate").click(function(){
		console.log("[로그] sponsorList.js 진입");
		$.ajax({
			type:"POST",
			url: "AdminSupportManageDateAsync.do",
			dataType:"json",
			success:function(datas){
				if(datas == "실패"){
					console.log("실패");
				}else{
					console.log("[로그 datas]"+datas);
					var i=1;
					let elem = "<tr>";
					datas.forEach(supporter =>{
						elem += "<td>"+i+"</td>";
						elem += "<td>"+supporter.loginId+"("+supporter.name+")"+"</td>";
						elem += "<td>"+supporter.amount+"</td>";
						elem += "<td>"+supporter.date+"</td>";
						elem += "</tr>";
						i++;
					});
					
					$("table tbody").html(elem);
				}
			},
			error:function(error){
				console.log("error: "+error);
			}
		});
	});
});