<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>



<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>게시판</title>



</head>

<body>
	<div id="wrapper">
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">게시판</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>글번호</th>
											<th>제목</th>
											<th>글쓴이</th>
											<th>작성일</th>
											<th>조회</th>
											<th>추천</th>
										</tr>
									</thead>
									<tbody>
										<tr class="odd gradeX">
											<td>001</td>
											<td>안녕안녕</td>
											<td>김지수</td>
											<td>2020-04-21</td>
											<td>1200</td>
											<td>100</td>
										</tr>
									</tbody>
								</table>
							</div>
							<!-- /.table-responsive -->
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->


</body>

</html>
