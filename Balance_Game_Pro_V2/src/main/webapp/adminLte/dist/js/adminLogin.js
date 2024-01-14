function loginCheck(){

    var admin_id     = $("#admin_id").val();
    var admin_pwd    = $("#admin_pwd").val();

    if (admin_id == ""){
        alert("아이디를 입력해 주세요.");
        return;
    }

    if (admin_pwd == ""){
        alert("비밀번호를 입력해 주세요.");
        return;
    }

    if(admin_id == "test" && admin_pwd == "1234"){
        location.href="index.html";
    }
}