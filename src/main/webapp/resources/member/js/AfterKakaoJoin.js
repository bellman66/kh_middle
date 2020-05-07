function validate(){
		console.log($("#submitNick").val());
		if($("#submitNick").val() == ""){
			alert("닉네임 중복검사를 진행해주세요");
			return false;
		}
		return true;
	};

	function nickNameCheck(){
		
		$.ajax({
		
			url:"/middle/member/nicknamecheck.do",
			type:"get",
			data:$("#nickName").serialize(),
			success:function(data){
				if(data == 'true'){
					$("#nickCheck").html("");
					$("#nickCheck").html("<strong>사용가능한 닉네임입니다</strong>");
					$("#submitNick").val($("#nickName").val());
				}else{
					$("#submitNick").val("");
					$("#nickCheck").html("");
					$("#nickCheck").html("<strong>사용불가능한 닉네임입니다</strong>");
				}
			}
		});
	};
