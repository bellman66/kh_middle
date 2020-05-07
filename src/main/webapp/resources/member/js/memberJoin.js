/**
 * 	Member Join JS file
 */
		var idFlag = false;
		var pwFlag = false;
		var captcharFlag = false;
		var nickFlag = false;

		$(function() {

			$.ajax({

				url : "/middle/member/captcharkey.do",
				type : "get",
				data : $("#captcharImgSrc").val(),
				success : function(data) {
					console.log(data); 
					console.log(data.key);
					console.log(data.img);
					$("#key").val(data.key);
					$("#captcharImgSrc").val(data.img);
					$("#imgSrc").attr("src",
							"/middle/resources/member/captchar/" + data.img);
				}

			})

		});
		function sertiCaptchar() {

			var datas = {
				"key" : $("#key").val(),
				"answer" : $("#capctharRes").val(),
				"captcharImgSrc" : $("#captcharImgSrc").val()
			};
			console.log(datas);

			$.ajax({
				url : "/middle/member/captcharkeyserti.do",
				type : "get",
				data : datas,
				success : function(data) {
					console.log(data);
					if (data.res == 'false') {
						$("#key").val(data.key);
						$("#captcharImgSrc").val(data.img);
						$("#imgSrc")
								.attr(
										"src",
										"/middle/resources/member/captchar/"
												+ data.img);
						$("#capctharRes").val('');
						$("#captcharAnswer").html("<strong>인증 실패!</strong>");
					}else{
						captcharFlag = true;
						$("#captcharAnswer").html("<strong>인증 성공!</strong>");
					}
				}
				
			})

		};

		function reflashImg() {

			$.ajax({
				url : "/middle/member/reflagjimg.do",
				type : "get",
				data : $("#captcharImgSrc").serialize(),
				success : function(data) {

					console.log(data);
					console.log(data.key);
					console.log(data.img);
					$("#key").val(data.key);
					$("#captcharImgSrc").val(data.img);
					$("#imgSrc").attr("src",
							"/middle/resources/member/captchar/" + data.img);
				}
			})
		};

		function validate() {
			console.log(idFlag);
			if (!idFlag) {
				alert("아이디 중복검사를 진행해주세요!");
				return false;
			}

			if (!pwFlag) {
				alert("비밀번호를 재 확인해주세요");
				return false;
			}

			if (!captcharFlag) {
				alert("자동가입방지 문자 인증을 진행해주세요");
				return false;
			}

			if ($("#user_pw2").val() == '') {
				alert("비밀번호를 재 확인해주세요");
				return false;
			}
			if ($("#nickname").val() == '') {
				alert("닉네임을 입력 해주세요");
				return false;
			}

			if ($("#usertell").val() == '') {
				alert("전화번호를 입력해주세요");
				return false;
			}

			if ($("#email").val() == '') {
				alert("이메일을 입력해주세요");
				return false;
			}
			
			if(!nickFlag){
				alert("닉네임 중복검사를 진행해주세요");			
				return false;
			}

			return true;

		};
		//?= : 전방탐색자 
		//. : 모든 단일문자와 대응
		//\d : 숫자[0-9]
		//* :(숫자) : 0개 이상
		//{n,m}(숫자) : n개이상 n개이하

		function idcheck() {
			var regExpId = /^[a-z][a-zA-Z0-9]{3,9}$/;

			if ($("#user_id").val() == "") {
				$("#idcheck").html("<strong>아이디를 입력해주세요</strong>");
				return false;
			}

			if (!regExpId.test($("#user_id").val())) {
				$("#idcheck").html(
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
						$("#idcheck").html("<strong>중복된 아이디가 존재하니 다른 아이디를 입력해주세요</strong>");
					}
				}
			});
		};

		$("#user_pw2").keyup(function() {
			var pwFlag2 = false;
			var oriPw = $("#user_pw").val();
			var rePw = $("#user_pw2").val();

			if (oriPw == rePw) {
				$("#pwCheck2").html("<strong>비밀번호가 일치합니다.</strong>");
				pwFlag = true;
			} else {
				$("#pwCheck2").html("<strong>비밀번호가 다릅니다.</strong>");
			}

			return pwFlag2;
		});

		$("#user_pw")
				.keyup(
						function() {
							//비밀번호 8 - 20사이 영문,숫자,특수문자 포함
							var regExpPw = /^(?=.*\d{1,20})(?=.*[~!@#$%\^&*()_+=]){1,20}(?=.*[a-zA-Z]{1,20}).{8,20}$/;

							var oriPw = $("#user_pw").val();
							var pw = regExpPw.test(oriPw)
							if (pw) {
								$("#pwCheck").html("<strong>사용할 수 있는 비밀번호입니다.</strong>");
							} else {
								$("#pwCheck")
										.html(
												"<strong>비밀번호는 영문, 숫자, 특수문자 포함하여 8자리 이상 20자리 이하로 기입해주세요</strong>");
							}
						});

		function nickNameCheck() {

			$.ajax({

				url : "/middle/member/nicknamecheck.do",
				type : "get",
				data : $("#nickname").serialize(),
				success : function(data) {
					console.log(data);
					console.log($("#nickname").val());
					if (data == 'true') {
						$("#nickCheck").html("");
						$("#nickCheck").html("<strong>사용가능한 닉네임입니다</strong>");
						nickFlag = true;
					} else {
						$("#nickCheck").html("");
						$("#nickCheck").html("<strong>사용불가능한 닉네임입니다</strong>");
						nickFlag = false;
					}

				}

			});

		};
