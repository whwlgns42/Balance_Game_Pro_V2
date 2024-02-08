// 필터링 함수
function filterByAmount(minAmount, maxAmount, supportList) {
    return supportList.filter(supporter => supporter.total >= minAmount && supporter.total <= maxAmount);
    console.log("filter 로그: "+supportList.filter(supporter => supporter.total >= minAmount && supporter.total <= maxAmount));
}



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
					 const supportList = datas;

                    console.log("[로그] 서버에서 받아온 후원자 데이터: ", supportList);

                    var inputVal = document.querySelector('#range_1');
                    const minAmount = parseFloat(inputVal.value.split(";")[0]);
                    const maxAmount = parseFloat(inputVal.value.split(";")[1]);

                    // 필터링 함수 호출
                    const filteredData = filterByAmount(minAmount, maxAmount, supportList);

                    console.log("로그[ ", filteredData, " ]");
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