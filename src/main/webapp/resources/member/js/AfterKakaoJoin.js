
console.log("${reJoinMember}");
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
	// 추가 
	function idcheck() {
		var regExpId = /^[a-z][a-zA-Z0-9]{3,9}$/;

		if ($("#user_id").val() == "") {
			$("#idcheck").html("<strong>아이디를 입력해주세요</strong>");
			return false;
		}

		if (!regExpId.test($("#user_id").val())) {
			$("#idcheck")
					.html(
							"<strong>아이디는 영문 숫자조합으로 4글자 이상 10글자 이하로 입력해주세요(특수문자 제외)</strong>");
			return false;
		}

		$.ajax({

			url : "/middle/member/idcheck.do",
			type : "get",
			data : $("#user_id").serialize(),
			success : function(data) {
				if (data == "true") {
					$("#idcheck").html("<strong>사용가능한 아이디 입니다</strong>");
					idFlag = true;
				} else {
					$("#idcheck").html("");
					$("#idcheck").html(
							"<strong>중복된 아이디가 존재하니 다른 아이디를 입력해주세요</strong>");
				}
			}
		});
	};