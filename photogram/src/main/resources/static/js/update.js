// (1) 회원정보 수정
function update(userId, event) {
	event.preventDefault(); //폼태그 액션막기
    let data = $("#profileUpdate").serialize(); 
    console.log(data);
    // 데이터를 응답해줘야한다.
    $.ajax({
        type: "put",
        url: "/api/user/" + userId,
        data: data,
        contentType: "application/x-www-form-urlencoded; charset=utf-8", // contentType의 오타 수정
        dataType: "json"
    }).done(res => { //HtttpStatus 상태코드 200번대
        console.log("update 성공", res);
       	location.href ="/user/"+ userId;
    }).fail(error => { //HtttpStatus 상태코드 200번대가 아닐 때 
   		console.log(error); 
   		if(error.data==null){
			alert(error.responseJSON.message);
		} else {
			// 자바스크립트를 JSON으로 변경
    		alert(JSON.stringify(error.responseJSON.data));
		}
		  
    	
    });
}
