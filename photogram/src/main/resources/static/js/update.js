// (1) 회원정보 수정
function update(userId, event) {
    let data = $("#profileUpdate").serialize(); 
    console.log(data);
    // 데이터를 응답해줘야한다.
    $.ajax({
        type: "put",
        url: "/api/user/" + userId,
        data: data,
        contentType: "application/x-www-form-urlencoded; charset=utf-8", // contentType의 오타 수정
        dataType: "json"
    }).done(res => {
        console.log("update 성공");
       	location.href ="/user/"+ userId;
    }).fail(error => {
        console.log("update 실패");
    });
}
