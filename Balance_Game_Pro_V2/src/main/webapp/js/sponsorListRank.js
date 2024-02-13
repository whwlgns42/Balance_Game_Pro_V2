console.log("[로그] sponsorListRank.js");
$(document).ready(function(){
    $("#btnRank").click(function(){
        $.ajax({
            type: "POST",
            url: "AdminSupportManageRankAsync",
            dataType: "json",
            success: function(datas){
                if(datas == "실패"){
                    console.log("실패")
                } else {
                    const userInfoObj = {
                        id: '후원순',
                        datas: datas
                    };
                    sessionStorage.setItem('userInfoObj', JSON.stringify(userInfoObj));
                    var i = 1;
                    var elem = "<tr>";
                    datas.forEach(supporter => {
                        elem += "<td>" + i + "</td>";
                        elem += "<td>" + supporter.loginId + "(" + supporter.name + ")" + "</td>";
                        elem += "<td>" + supporter.total + "</td>";
                        elem += "<td>" + supporter.date + "</td>";
                        elem += "</tr>";
                        i++;
                    });
                    $("table tbody").html(elem);
                }
            },
            error: function(error){
                console.log("error" + error);
            }
        });
    });
});
