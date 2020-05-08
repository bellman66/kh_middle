<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>

<html>
<head>
    <title> Hello I'M  Oil Finder </title>

    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no"/>

    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">

<link href="${pageContext.request.contextPath}/resources/steller/assets/css/main.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/resources/steller/assets/css/map.css" rel="stylesheet"/>


<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript">
	if(${sessionScope.userData!=null}) {
		sessionStorage.setItem("user_id","${sessionScope.userData.user_id}");
		sessionStorage.setItem("kind_oil","${sessionScope.userData.kind_oil}");
		sessionStorage.setItem("nick_name","${sessionScope.userData.nick_name}");
	}
</script>

<style>
    .overlaybox {
        position: relative;
        width: auto;
        height: auto;
        /* background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/box_movie.png') no-repeat; */
        background-color : #48494B;
        padding: 15px 10px;
        border: 1px solid red;
        border-radius: 10px;
    }

    .overlaybox div, ul {
        overflow: hidden;
        margin: 0;
        padding: 0;
    }

    .overlaybox li {
        list-style: none;
    }

    .overlaybox .boxtitle {
    	width : 60%;
        color: #fff;
        font-size: 16px;
        font-weight: bold;
        margin-bottom: 8px;
        float: left;
        white-space: pre-line;
    }

    .overlaybox .first {
        position: relative;
        width: 247px;
        height: 136px;
        background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/thumb.png') no-repeat;
        margin-bottom: 8px;
    }


    .first .text {
        color: #fff;
        font-weight: bold;
    }

    .first .triangle {
        position: absolute;
        width: 48px;
        height: 48px;
        top: 0;
        left: 0;
        background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/triangle.png') no-repeat;
        padding: 6px;
        font-size: 18px;
    }

    .first .movietitle {
        position: absolute;
        width: 100%;
        bottom: 0;
        background: rgba(0, 0, 0, 0.4);
        padding: 7px 15px;
        font-size: 14px;
    }

    .overlaybox ul {
        width: 247px;
        max-width: 247px;
    }

    .overlaybox li {
        position: relative;
        margin-bottom: 2px;
        background: #2b2d36;
        padding: 5px 10px;
        color: #aaabaf;
        line-height: 1;
    }

    .overlaybox li span {
        display: inline-block; 
    }

    .overlaybox li .number {
        font-size: 14px;
        font-weight: bold;
        left: 2px;
    }

    .overlaybox li .title {
        font-size: 13px;
        left: 20px;
    }

    .overlaybox ul .arrow {
        position: absolute;
        margin-top: 8px;
        right: 25px;
        width: 5px;
        height: 3px;
        background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/updown.png') no-repeat;
    }

    .overlaybox li .up {
        background-position: 0 -40px;
    }

    .overlaybox li .down {
        background-position: 0 -60px;
    }

    .overlaybox li .count {
        position: absolute;
        margin-top: 5px;
        right: 15px;
        font-size: 10px;
    }

    .overlaybox li:hover {
        color: #fff;
        background: #d24545;
    }

    .overlaybox li:hover .up {
        background-position: 0 0px;
    }

    .overlaybox li:hover .down {
        background-position: 0 -20px;
    }
    .titleWrapper {
        width: auto;
        height: auto;
    }

    .close_btn {
        position: absolute;
        background-color : white !important;
        border: 1px solid red;
        float: left;
        top: 10px;
        right: 7px;
        color: gray;
        font-size : 6px;
        height: 19px ;
        background-size: 10px;
        background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png') no-repeat; background-position: center;
        
    }

    .close_btn:hover {
        cursor: pointer;
    }
    
    #star_avg {
    	position: relative;
    	top: 17px;
    }
    
    #star_avg a{ 
    	text-decoration: none; 
    	color: gray; 
    	}
    #star_avg a.on{ 
    	color: red; 
    	}
    
    
    #star a{ 
    	text-decoration: none; 
    	color: gray; 
    	}
    #star a.on{ 
    	color: red; 
    	}
    
    
    .buttonWrapper {
    	top:50%;
    	left:50%;
    	
   		width:200px; height:200px;
    	/* width : auto ;
    	height : auto; */
    	float : left; 
    	margin-top : 1vw;
    	text-align: center;
    	
    } 
    
    .buttonWrapper {
    	width : 100% ;
    	height : auto;
    	
    	
    }
    .product {
    	position : relative;
    	margin-top : 3px;
    	width : 20% ;
    	height : auto;
    	left :38%;
    }
    
    .fs {
    	margin-top : 4px;
   		position : relative;
    	width : 40% ;
    	height : auto;
    	left :28%;
    }

    .button_wrap {
  	    margin-top : 6px;
    	position : relative;
    	width : 40%;
    	height : auto;
    	left : 28%;
    }
    
    
    


</style>

</head>
<body class="is-preload">
<!-- Wrapper -->
<div id="wrapper">

    <!-- Header -->
    <header id="header" class="alt">
        <span class="logo"><img src="${pageContext.request.contextPath}/resources/steller/images/logo.svg"
                                alt=""/></span>
        <h1> 나만의 주유소</h1>

        <div>
            <a class="login_form" href="login"> login </a>
        </div>
    </header>

    <!-- Nav -->
    <nav id="nav">
        <ul>
            <li><a href="#intro" class="active"> 오늘자 가격 </a></li>
            <li><a href="#first"> Oil Finder </a></li>
            <li><a href="#second">개발 페이지</a></li>
            <li><a href="#cta">Get Started</a></li>
        </ul>
    </nav>

    <!-- Main -->
    <div id="main">

        <!-- Introduction -->
        <section id="intro" class="main">
            <div class="spotlight">
                <div class="content">
                    <header class="major">
                        <h2>오늘자 평균 주유 가격</h2>
                    </header>
                    
                    <table class="table-bordered">
                       	<thead class="thead-dark">
                        <tr>
                            <th scope="col">제품명</th>
                            <th scope="col">평균가격</th>
                            <th scope="col">등락값</th>
                        </tr>
                        </thead>
                        <tbody>
	                    <c:forEach var="vo" items="${avgAllPrice}"> 
	                    	<c:if test="${vo.PRODCD ne 'C004'}">
	                    	<tr>
	                    		<th scope="row"> ${vo.PRODNM} </th>
                                <td>${vo.PRICE}</td>
                                <td>${vo.DIFF}</td>
	                    	</tr>
	                    	</c:if>
	                    </c:forEach>
						</tbody>
					</table>
				
                </div>
                <span class="image"><img src="${pageContext.request.contextPath}/resources/steller/images/fuel.png"
                                         alt=""/></span>
            </div>
        </section>

        <!-- First Section -->
        <section id="first" class="main special">
            <header class="major">
                <h2>지역 찾기</h2>
            </header>

            <!-- kakao map.  -->
            <div class="map_wrap">
                <div id="map" style="width: 100%; height: 500px;"></div>
            </div>

            <div class="buttonWrapper">
                <div class="product">
                <select id="product" name="product">
                    <option value="B027" selected="selected"> 휘발유</option>
                    <option value="D047"> 경유</option>
                    <option value="K015"> LPG</option>
                    <option value="B034"> 고급휘발유</option>
                </select>
                </div>
				<div class="fs">
                <input type="text" name="fs" id="fs" placeholder="연비 입력">
				</div>
                <div class="button_wrap">
                    <button id="find_button"> 추천 주유소 검색</button>
                </div>
            </div>
        </section>

<!--         Second Section
        <section id="second" class="main special">
            <header class="major">
                <h2> 개발 페이지 </h2>
            </header>

            <div class="page-header">
                <h3>Font Awesome Star LTR</h3>
            </div>
            <input type="text" class="kv-fa rating-loading" value="3.75" data-size="xl" title="">
            <br>
            <input type="text" class="kv-fa rating-loading" value="2.5" data-size="lg" title="">
            <br>
            <input type="text" class="kv-fa rating-loading" value="1.75" data-size="md" title="">
            <br>
            <input type="text" class="kv-fa rating-loading" value="4" data-size="sm" title="">
            <br>
            <input type="text" class="kv-fa rating-loading" value="2" data-size="xs" title="">
            <br>

        </section> -->

        <!-- Get Started -->
     <!--   <section id="cta" class="main special">
             <header class="major">
                 <h2>Congue imperdiet</h2>
                <p>Donec imperdiet consequat consequat. Suspendisse feugiat congue<br/>
                    posuere. Nulla massa urna, fermentum eget quam aliquet.</p>
            </header>
            <footer class="major">
                <ul class="actions special">
                    <li><a href="generic.html" class="button primary">Get Started</a></li>
                    <li><a href="generic.html" class="button">Learn More</a></li>
                </ul> 
            </footer> 
        </section>  -->
    </div>

    <!-- Footer -->
     <footer id="footer">
        <!-- <section>
            <h2>Aliquam sed mauris</h2>
            <p>Sed lorem ipsum dolor sit amet et nullam consequat feugiat consequat magna adipiscing tempus etiam dolore
                veroeros. eget dapibus mauris. Cras aliquet, nisl ut viverra sollicitudin, ligula erat egestas velit,
                vitae tincidunt odio.</p>
            <ul class="actions">
                <li><a href="generic.html" class="button">Learn More</a></li>
            </ul>
        </section>
        <section>
            <h2>Etiam feugiat</h2>
            <dl class="alt">
                <dt>Address</dt>
                <dd>1234 Somewhere Road &bull; Nashville, TN 00000 &bull; USA</dd>
                <dt>Phone</dt>
                <dd>(000) 000-0000 x 0000</dd>
                <dt>Email</dt>
                <dd><a href="#">information@untitled.tld</a></dd>
            </dl>
            <ul class="icons">
                <li><a href="#" class="icon brands fa-twitter alt"><span class="label">Twitter</span></a></li>
                <li><a href="#" class="icon brands fa-facebook-f alt"><span class="label">Facebook</span></a></li>
                <li><a href="#" class="icon brands fa-instagram alt"><span class="label">Instagram</span></a></li>
                <li><a href="#" class="icon brands fa-github alt"><span class="label">GitHub</span></a></li>
                <li><a href="#" class="icon brands fa-dribbble alt"><span class="label">Dribbble</span></a></li>
            </ul>
        </section>
        <p class="copyright">&copy; Untitled. Design: <a href="https://html5up.net">HTML5 UP</a>.</p> -->
    </footer>
</div>

<!-- Scripts -->

<script src="${pageContext.request.contextPath}/resources/steller/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/steller/assets/js/jquery.scrollex.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/steller/assets/js/jquery.scrolly.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/steller/assets/js/browser.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/steller/assets/js/breakpoints.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/steller/assets/js/util.js"></script>
<script src="${pageContext.request.contextPath}/resources/steller/assets/js/main.js"></script>

<script type="text/javascript"
        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=732d43eaffe0e338eb5b1d0a7a9710fc&libraries=services,clusterer,drawing"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/steller/assets/js/map.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/steller/assets/js/test.js"></script>
</body>
</html>