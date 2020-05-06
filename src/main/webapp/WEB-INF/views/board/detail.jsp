<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<<<<<<< HEAD
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/resources/steller/assets/css/main.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/resources/steller/assets/css/map.css" rel="stylesheet" />
<title>Insert title here</title>
</head>
<body> 
	<div>
		<div>
			<h4 class="reset">제목 : ${detail.notice_title }</h4>
		</div>
		<div>
			<span>등록일 : ${detail.notice_date }</span> 
			<%-- <span><a href="/servletBM/notice/noticedownload.do?ofname=${data.notice.original_filepath }
				&rfname=${data.notice.rename_filepath}">첨부파일 : ${data.original_filepath }</a>
			</span> --%>
		</div>
		<div>내용 : ${detail.notice_content }</div>
		<div>
			<label>작성자 : ${detail.notice_id }  |  작성일 : ${detail.notice_date }</label>
			<label>조회 ${detail.notice_count } | 추천 ${detail.notice_recommend }</label>
		</div>
		<div>
			<a href="<%= request.getContextPath()%>/board/index.do?pageNum=1"><span>목록</span></a>
		</div>
		
	<%-- <div>
		<label><strong>제목 : ${detail.notice_title }</strong></label>
		<label>작성자 : ${detail.notice_id }  |  ${detail.notice_date }</label>
		<label>조회 ${detail.notice_count } | 추천 ${detail.notice_recommend }</label>
		<label>내용 : ${detail.notice_content }</label>
	</div> --%>
=======

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="header.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/board/detail.css"
	rel="stylesheet" />
<title>Insert title here</title>

</head>
<body>
	<div class="wrapper">
		<%-- <a href="<%=request.getContextPath()%>/board/index.do?pageNum=1"><span>목록</span></a> --%>
			<div id="write">
			<div class="title">
				<span id="h3">제목 : ${detail.notice_title }</span>
				<span id="notice_count">조회 ${detail.notice_count }</span>
				<span id="notice_date">등록일 : ${detail.notice_date }</span> 
			</div>
			
			<div class="title2">
			<c:if test="${detail.rename_filepath ne '' }">
				<span id="file">첨부파일 : 
					<a href="<%=request.getContextPath() %>/board/download.do?ofname=${detail.original_filepath }
						&rfname=${detail.rename_filepath}">
						${detail.original_filepath }</a> 
				</span>
			</c:if>
			<span id="id">작성자 : ${detail.notice_id }</span>
			</div> 
		
		
		<div class="contentDiv" id="contentDiv">${detail.notice_content }</div>
		
		
		<form id="recommend_form" action="<%= request.getContextPath()%>/board/ajax.do">
			<button value="${detail.notice_recommend }" name="recommend" id="recommend" type="button">
				추천수  <br> ${detail.notice_recommend}
			</button>
			<input type="hidden" value="${recommendnew }" id="recommendnew" />
		</form>
		
		<c:if test="${detail.notice_id eq '김지수'}">
		<form action="<%= request.getContextPath()%>/board/modifynotice.do" />
				<input type="hidden" name="noticeNum" value="${detail.notice_num}"/>
				<input type="hidden" name="rename_filepath" value="${detail.rename_filepath}"/>
				<input type="hidden" name="original_filepath" value="${detail.original_filepath}"/>
				<input type="hidden" name="notice_content" value="${detail.notice_content}"/>
				<input type="hidden" name="notice_title" value="${detail.notice_title}"/>
				<button id="modify" type="submit">수정하기</button>
		</form>
		<form action="<%= request.getContextPath()%>/board/noticedelete.do">
			<input id="noticeNum" name="noticeNum" id="noticeNum" type="hidden" value="${detail.notice_num }"/>
			<input type="hidden" name="rename_filepath" value="${detail.rename_filepath}"/>
			<button id="delete" type="submit" onclick="if(!confirm('삭제하시겠습니까?')){return false;}">삭제하기</button>
		</form>
		</c:if>
		
		<form action="<%= request.getContextPath()%>/board/comment.do" method="post" id="write_comment_form">
			<h4>댓글작성</h4>
			<input id="comment_txt" placeholder="댓글을 입력해 주세요" type="text" name="comment_content" />
			<input type="hidden" name="notice_num" value="${detail.notice_num }" />
			<button id="comment_submit_btn" type="submit">등록</button>
		</form>
		<hr>
		<div id="show_comment">
			<h4>댓글 보기(${comment.size() })</h4>
		<c:if test="${ comment != null}">
			<c:forEach var="cmt" items="${comment }">
			<div class="allDiv">
				<span id="comment_id${cmt.comment_num }">${cmt.comment_id } : </span>
				<span id="comment_content${cmt.comment_num }">${cmt.comment_content }</span>
			
				<c:if test="${cmt.comment_id eq '김지수' }">
				<form action="<%=request.getContextPath()%>/board/commentdelete.do">
					<input type="hidden" name="comment_num" value="${cmt.comment_num }"/>
					<input type="hidden" name="noticeNum" value="${cmt.notice_num }"/>
					<button id="deletebtn${cmt.comment_num }" onclick="if(!confirm('삭제하시겠습니까?')){return false;}" type="submit">삭제</button>
				</form>
				<form action="<%=request.getContextPath()%>/board/commentmodify.do">
					<input id="comment_num" type="hidden" name="comment_num" value="${cmt.comment_num }"/>
					<input type="hidden" name="noticeNum" value="${cmt.notice_num }"/>
					<input id="modifytxt${cmt.comment_num }" type="hidden" name="comment_content" />
					<input id="modifybtn${cmt.comment_num }" type="button" onclick="modify(this);" value="수정" />
					<input id="complete${cmt.comment_num }" type="hidden" value="작성완료" />
				</form>
				</c:if>
				<p class="clear"></p>
				</div>
			</c:forEach>
		</c:if>
		</div>
		
		<form action="<%=request.getContextPath()%>/board/search.do">
		<input type="hidden" id="search" name="search" />
		<button id="searchBtn" style="display:none" type="submit">검색</button>
		</form>
	</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.0.js" integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc=" crossorigin="anonymous"></script>
	
	<script>
	$(document).ready(function(){
		$('#recommend').click(function(){
			$.ajax({
				  method: "GET",
				  data : "noticeNum="+$('#noticeNum').val(),
				  url: "/middle/board/recommend.do",
				  success: function(msg){
				   	if(msg == 0){
						alert('추천은 하루에 한번만 가능');				   		
				   	}else{
					   	$('#recommend').html("추천완료<br>" + (parseInt($('#recommend').val(),10)+1));
				   	}
			 	  },
				  error:function(msg){
					  alert('error : 통신실패');
				  }
			});
		});
	});
	
	function modify(e){
		var modifybtn = 'modifybtn';
		var comment_num = e.id.substring(modifybtn.length);
		$('#modifytxt'+comment_num).val($('#comment_content'+comment_num).html().trim());
		$('#modifytxt'+comment_num).attr("type","text");
		
		$('#comment_content'+comment_num).remove();
		$('#comment_id'+comment_num).remove();
		
		$('#modifybtn'+comment_num).attr("type", "hidden");
		$('#complete'+comment_num).attr("type", "submit");
	}
	
	/* --------------검색기능--------- */
	var result = "";
	
	//드래그 된 영역의 글자를 리턴해주는 함수
	function selectObj(){
		var selected;	
		if(document.getSelection){
			selected = document.getSelection().toString();
		}
		return selected;
	}
	
	document.onmouseup = function(){
		//드래그된 글자를 리턴받아 input type="hidden"으로 값 넣어놓고
		//검색 버튼 클릭시 controller로 값을 보내준다
		result = selectObj();
		$("#search").val(result);
	}
	
	$('#contentDiv').on("mouseup",function(e){
		var divTop = e.clientY;//Y좌표
		var divLeft = e.clientX;//X좌표
		//드래그된 x좌표와 y좌표 위치에 버튼 띄움
		$('#searchBtn').css({
			"top":divTop,
			"left":divLeft,
			"position":"absolute"
		}).show();
	});
	
	$("html").mouseup(function(e){
		//content가 들어간 자리 외에서 클릭됐을 때는 검색 버튼 가림
		if(!$(e.target).hasClass("contentDiv")){
			$("#searchBtn").hide();
		}
	});
	
	</script>
>>>>>>> branch 'master' of https://github.com/rndso15/kh_middle.git
</body>
</html>