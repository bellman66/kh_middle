<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head> 
<meta charset="UTF-8"> 
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/steller/assets/css/main.css" />
<link href="${pageContext.request.contextPath}/resources/css/board/modify.css"
	rel="stylesheet" />
</head>

<body class="is-preload">
  
		<!-- Wrapper -->
		<div id="wrapper">

			<!-- Header -->
			<header id="header">
				<h1>게시물 수정</h1><br>
			</header>
			
			<!-- Main -->
			<div id="main">
			<section id="content" class="main">
					<form action="<%=request.getContextPath()%>/board/modify.do" method="post"
					enctype="multipart/form-data">
							<input maxlength="30" id="title" id="demo-name" type="text" name="modify_title"
							 value="${modify_notice.notice_title }" /><br>
							<textarea onKeyup="len_chk();" name="modify_content" >${modify_notice.notice_content }</textarea>
							<br>
							<c:if test="${modify_notice.original_filepath ne ''}">
								<span id="inputfile">첨부파일 : ${modify_notice.original_filepath}</span>
								<button type="button" id="deletefile">삭제</button>
								<input id="modifyFile" name="noticeFile" type="file" style="display:none"/>
								<input type="hidden" name="state" value="historyYes" />
								<input type="hidden" id="stateDetail" name="stateDetail" value="" />
								<!-- 이미 업로드 했던 파일이 있는 상태 -->
								<!-- 파일삭제/new업로드, 파일삭제/no업로드////N/Y -->
							</c:if>
							<c:if test="${modify_notice.original_filepath eq ''}">
								<input id="modifyFile" name="noticeFile" type="file" />
								<input type="hidden" name="state" value="historyNo" />
								<input type="hidden" id="stateDetail" name="stateDetail" value="" />
								<!-- 이미 업로드 했던 파일이 없는 상태 -->
								<!-- 파일수정/no업로드, 파일수정/new업로드////N/Y -->
							</c:if>
							<input type="hidden" value="${modify_notice.notice_num }" name="noticeNum" />
							<input type="hidden" value="${modify_notice.original_filepath }" name="original_filepath" />
							<input type="hidden" value="${modify_notice.rename_filepath }" name="rename_filepath" />
							<button id="modify_complete" type="submit">수정완료</button>
					</form>
				</section>
			</div>
			
	
	<script src="https://code.jquery.com/jquery-3.5.0.js" integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			
			$('#deletefile').click(function(e){
				//$('#deletefile').hide();
				$('#deletefile').css("display","none"); //수정
				$('#modifyFile').css("display", "");
				$('#inputfile').html("");
			});
			
			var modifyFile = $("#modifyFile").val;
			
			if(!modifyFile){
				$('#stateDetail').val("noUpload");
			}else{
				$('#stateDetail').val("yesUpload");
			}
			
			$('#content').on('keyup', function() {
				if($(this).val().length > 700) {
					alert("글자수는 700자 이내로 제한됩니다.");
					$(this).val($(this).val().substring(0, 700));
				}
			});
			
			$('#modify_complete').click(function(){
				if($('#title').val().trim() == ''){
					alert('제목을 입력해주세요');
					return false;
				}
			});
			
		});
		
	</script>

</body>
</html>