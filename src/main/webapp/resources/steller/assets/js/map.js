$(function () {
	// 중요
	
	// session 값 업데이트 
	var user_id = sessionStorage.getItem('user_id');
	console.log(user_id);
	
    var contextPath = "${pageContext.request.contextPath}";
    var local = new kakao.maps.Coords(5000.0, 100000.0);

    var mapcontainer = document.getElementById('map');
    var maplocation = {
        // center: local.toLatLng(), // 지도의 중심좌표
        center: new kakao.maps.LatLng(37.484632786467756, 127.03046010240786),
        level: 3 // 지도의 확대 레벨
    };
    var map = new kakao.maps.Map(mapcontainer, maplocation);	// 실질적으로 연계되어
    // 그리는 객체.
    var Makers = [];
    var BlueMarker = "";
    var RedMarker = "";
    var GreenMarker = "";


    // --- 1. 현재 위치 지정 및 옵션 지정.
    set_option();
    // === 1. end.

    // --- 2. 현재 위치에서 반경 1km이내에 주유소 가격 가져옴.
    // avgAllPrice();
    aroundAll();	// -- Maker 까지 제작됨.
    // === 2. end

    function set_option() {
        // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
        var mapTypeControl = new kakao.maps.MapTypeControl();
        map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

        // 지도 확대 축소를 제어할 수 있는 줌 컨트롤을 생성합니다
        var zoomControl = new kakao.maps.ZoomControl();
        map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

        imageSize = new kakao.maps.Size(48, 48) // 마커이미지의 크기입니다
        imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다.
																// 마커의 좌표와 일치시킬
																// 이미지 안에서의 좌표를
																// 설정합니다.

        BlueMarker = new kakao.maps.MarkerImage('/middle/resources/image/icon_MarkerBlue.png', imageSize, imageOption)
        RedMarker = new kakao.maps.MarkerImage('/middle/resources/image/icon_MarkerRed.png', imageSize, imageOption)
        GreenMarker = new kakao.maps.MarkerImage('/middle/resources/image/icon_MarkerGreen.png', imageSize, imageOption)
    }

    $('#find_button').click(function () {
        // 현재 위치 이동
        navigator.geolocation.getCurrentPosition(function (position) {
            // goToCenter(position.coords.latitude, position.coords.longitude);
            //
            // // marker 찍음.
            // aroundAll();
            
            // 현재 위치로 화면 전환 이후에 주변 주유소 서칭
            goToCenter2(position.coords.latitude, position.coords.longitude).then(function (value) {
                if(value == "success"){
                    aroundAll();
                }
            });
        });
    });

    // 좌표 변환 결과를 받아서 처리할 콜백함수 입니다.
    function transCoordCB(result, status) {

        // 정상적으로 검색이 완료됐으면
        if (status === kakao.maps.services.Status.OK) {

            // 마커를 변환된 위치에 표시합니다
            var marker = new kakao.maps.Marker({
                position: new kakao.maps.LatLng(result[0].y, result[0].x), // 마커를
                // 표시할
                // 위치입니다
                map: map // 마커를 표시할 지도객체입니다
            })
        }
    }

    // == == == 좌표계 변환

    function KTM_To_WGS84_XY(x, y) {
        var Xy_Number = new Array();

        $.ajax({
            type: "GET",
            url: "https://dapi.kakao.com/v2/local/geo/transcoord.json",
            headers: {
                "Authorization": "KakaoAK " + "732d43eaffe0e338eb5b1d0a7a9710fc"
            },
            async: false,
            dataType: "json",
            data: {
                "x": x,
                "y": y,
                "input_coord": "KTM",
                "output_coord": "WGS84"
            },
            success: function (data, status) {
                Xy_Number.push(Number(JSON.stringify(data.documents[0].x)));
                Xy_Number.push(Number(JSON.stringify(data.documents[0].y)));
            },
            error: function (xhr, err) {
                alert("연결 실패");
            }
        });

        return Xy_Number;
    }

    function WGS84_To_KTM_XY(x, y) {
        var Xy_Number = new Array();

        $.ajax({
            type: "GET",
            url: "https://dapi.kakao.com/v2/local/geo/transcoord.json",
            headers: {
                "Authorization": "KakaoAK " + "732d43eaffe0e338eb5b1d0a7a9710fc"
            },
            async: false,
            dataType: "json",
            data: {
                "x": x,
                "y": y,
                "input_coord": "WGS84",
                "output_coord": "KTM"
            },
            success: function (data, status) {
                Xy_Number.push(Number(JSON.stringify(data.documents[0].x)));
                Xy_Number.push(Number(JSON.stringify(data.documents[0].y)));
            },
            error: function (xhr, err) {
                alert("연결 실패");
            }
        });

        return Xy_Number;
    }

    // == == == 좌표계 변환 end

    function goToCenter(x, y) {
        // 이동할 위도 경도 위치를 생성합니다
        alert("움직일 위도 x : " + x + "움직일 경도 Y : " + y);
        var moveLatLon = new kakao.maps.LatLng(x, y);

        // 지도 중심을 부드럽게 이동시킵니다
        // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
        map.panTo(moveLatLon);
    }

    function goToCenter2(x, y) {
        return new Promise(function (resolve , reject) {
            try {
                // 이동할 위도 경도 위치를 생성합니다
                alert("움직일 위도 x : " + x + "움직일 경도 Y : " + y);
                var moveLatLon = new kakao.maps.LatLng(x, y);

                // 지도 중심을 부드럽게 이동시킵니다
                // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
                map.panTo(moveLatLon);

                resolve("success");
            }
            catch (e) {
				// TODO: handle exception
            	reject("error");
			}
        })
    }

    function makeOverListener(map, overlay, marker) {
        return function () {
        	var position = marker.getPosition();
        	map.panTo(position);
            overlay.setMap(map);
        };
    }

    function makeOutListener(overlay , marker) {
        return function () {
        	var position = marker.getPosition();
            overlay.setMap(null);
        };
    }

    // ### main 기능 - 반환받은 마커를
    // 순서대로 찍음.
    function set_marker(Position) {

        // 1. marker 초기화.
        if (Makers.length != 0) {
            // 기존 마커 초기화 ;
            for (var j = 0; j < Makers.length; j++) {
                Makers[j].setMap(null);
            }
            Makers = [];
        }

        // 2. marker 설정 및 overlay 설정.
        for (var i = 0; i < Position.length; i++) {
            // Postion 요소
            // UNI_ID 주유소코드
            // POLL_DIV_CD
            // 상표(SKE:SK에너지, GSC:GS칼텍스, HDO:현대오일뱅크, SOL:S-OIL, RTO:자영알뜰,
			// RTX:고속도로알뜰, NHO:농협알뜰, ETC:자가상표, E1G: E1, SKG:SK가스
            // OS_NM 상호
            // PRICE 판매가격
            // DISTANCE 기준 위치로부터의 거리 (단위 : m)
            // GIS_X_COOR GIS X좌표(KATEC) GIS_Y_COOR GIS Y좌표(KATEC)
            // 사용법 -> Position.content.PRICE

            var marker_set = RedMarker;
            if ( Position[i].priority <= (Position.length/3) ) {
                marker_set = BlueMarker;
            }
            else if ( Position[i].priority <= ( (Position.length*2)/3) ) {
                marker_set = GreenMarker;
            }
            else{
                marker_set = RedMarker;
            }

            // 2-1. 마커를 생성합니다
            var marker = new kakao.maps.Marker({
                map: map,
                position: Position[i].latlng , // 마커의 위치
                image : marker_set ,
                title : "marker" + i,
            });

            // overlay 생성
            var overlay = set_Overlay(Position[i]);

            // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
            // 이벤트 리스너로는 클로저를 만들어 등록합니다
            // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다

            kakao.maps.event.addListener(marker, 'click', makeOverListener(map, overlay,marker));
            // kakao.maps.event.addListener(marker, 'mouseover', function () {
            // overlay.setMap(map);
            // });
            // kakao.maps.event.addListener(marker, 'mouseout',
			// makeOutListener(overlay,marker));

            // 마커 저장.
            Makers.push(marker);
        }
    }

    function set_Overlay(data) {

        var overlay = new kakao.maps.CustomOverlay({
            // 1. 인포 윈도우 설정.
            position: data.latlng,
            yAnchor: 0.55,
            clickable: true,
            zIndex : 1
        });
        var review = [];
        var view = null;
        var review_part = null;

    	// 1. 별점 및 리뷰 가져오는 ajax
        
        // pointer 2
        review = setReview(data.content.UNI_ID);
//    	$.ajax({
//    		type : "GET" ,
//    		url : "/middle/review/index.do",
//    		async : false ,
//    		data: "uni_id=" + data.content.UNI_ID,
//    		dataType: "text",
//            success: function (data, status) {
//            	if(data.length > 0) {
//            		 json = JSON.parse(data);
//            		 for(var i = 0 ; i < json.length ; i++) {
//            			review.push(json[i]);
//            		 }
//            	}
//            },
//            error: function (xhr, err) {
//                alert("연결 실패");
//            }
//    	})

        // 2.custom over lay 제작 - 리뷰 값 3개 + 별점
    	// page custom view - 없을 경우 null 값을 가짐.
    	if(review.length > 0){
    	    // 리뷰가 한개라도 있는 경우.
    	    var review_part = setView(review);
    	}

    	view = document.createElement("div");
    	view.className = 'overlaybox';

    	var wrap = document.createElement('div');
    	wrap.className = 'titleWrapper';

    	var title = document.createElement('div');
    	title.className = 'boxtitle';
    	title.innerHTML = data.content.OS_NM;

    	var close_btn = document.createElement("button");
    	close_btn.className = 'close_btn' ;
    	close_btn.onclick = function () {
    	    overlay.setMap(null);
    	}
    	wrap.appendChild(title);
    	wrap.appendChild(close_btn);
    	view.appendChild(wrap);

    	var ul1 = document.createElement('ul');
    	var li1 = document.createElement('li');
    	var span1 = document.createElement('span');
    	span1.className = "title";
    	span1.innerHTML = "주유소 코드 : " + data.content.UNI_ID;
    	li1.appendChild(span1);

    	var li2 = document.createElement('li');
    	var span2 = document.createElement('span');
    	span2.className = "title";
    	span2.innerHTML = "가격 : " + data.content.PRICE;
    	li2.appendChild(span2);

    	var li3 = document.createElement('li');
    	var span3 = document.createElement('span');
    	span3.className = "title";
    	span3.innerHTML = "거리 : " + data.content.DISTANCE;
    	li3.appendChild(span3);
    	ul1.appendChild(li1); ul1.appendChild(li2); ul1.appendChild(li3);view.appendChild(ul1);
    	// ul1 완성

        if(review_part != null) {
            view.appendChild(review_part);
        }
        else {
            var ul2 = document.createElement('ul');
            ul2.id="review";
            var li4 = document.createElement('li');
            li4.className = 'up';

            var span4 = document.createElement('span');
            span4.className = 'title';
            span4.innerHTML = '리뷰가 존재하지 않습니다.';
            li4.appendChild(span4);
            ul2.appendChild(li4);
            view.appendChild(ul2);
        }

        var span5 = document.createElement('span');
        span5.innerHTML = '<리뷰 입력>';
        span5.setAttribute("isOpen" , "false");
        span5.style.color = "#aaabaf";
        span5.onclick = function() {
            // 로그인이 안되있음.
            if(span5.getAttribute("isOpen") === "false") {
                span5.setAttribute("isOpen" , "true");    // 열림 설정.

                if (sessionStorage.getItem('userData') == null) {

                    alert('로그인시 이용가능합니다.');
                    // 실험용 textarea
                    span5.innerHTML = "<리뷰 닫기>";
                    span5.style.color = "#aaabaf";

                    var textdiv = document.createElement('div');
                    textdiv.className = 'textdiv';
                    textdiv.id = 'textdiv';

                    var star = document.createElement("div");
                    star.id = "star";

                    for(var i=1 ; i < 6 ; i++) {
                        var in_star = document.createElement("a");
                        in_star.setAttribute("value" , i);
                        in_star.textContent = "★";
                        star.appendChild(in_star);
                    }

                    var rating = 0;
                    var star_list = star.childNodes;
                    star_list.forEach(function( currentValue, currentIndex) {

                        star_list[currentIndex].addEventListener('click' , function () {
                            $(this).parent().children("a").removeClass("on");
                            $(this).addClass("on").prevAll("a").addClass("on");
                            rating = $(this).attr("value");
                        })
                    });

                    textdiv.appendChild(star);

                    var textarea = document.createElement('textarea');
                    textarea.id = "textarea";
                    textarea.style.top = '20px';
                    textarea.style.backgroundColor = 'white';
                    textarea.cols = '100px';
                    textarea.rows = '100px';
                    textarea.style.resize = "none"; // 사이즈 고정
                    textdiv.appendChild(textarea);

                    var button = document.createElement('div');
                    button.textContent = "리뷰 등록";
                    button.style.color = "#aaabaf";
                    
                    button.addEventListener('click' , function () {
                        // 실험용
                        alert(textarea.value) ;
                        alert(sessionStorage.getItem("userData"));

                        $.ajax({
                            type : "POST" ,
                            url : "/middle/review/review_insert.do" ,
                            async : true ,
                            data : {
                                // userData : sessionStorage.getItem("userData")
								// ,
                                content : textarea.value ,
                                uni_id : data.content.UNI_ID ,
                                rating : rating
                            } 
                        }).done(function (data , textStatus ,xhr) {
                            alert('리뷰 등록 완료');
                            close_review(span5,view);
                            
                            // pointer
                            var reviewlist = setReview(data);	// 가져오기완료
                            var review_part = setView(reviewlist);
                            $("#review").replaceWith(review_part);
//                            view.appendChild(review_part);
                        })
                    })
                    textdiv.appendChild(button);

                    view.appendChild(textdiv);
                } else {   // userdata 존재.
                    var textarea = document.createElement('textarea');
                    textarea.cols = '100px';
                    textarea.rows = '100px';
                }
            }
            else {
                close_review(span5,view);
            }
        }

        view.appendChild(span5);
    	overlay.setContent(view);
            /* 주의 : map 설정을 할 경우 자동으로 바로 생성되니 조심 */
       return overlay;
    }
    function close_review(span5,view) {
        span5.setAttribute("isOpen" , 'false');    // 열림 설정.
        span5.innerHTML = '<리뷰 입력>';
        view.lastChild.outerHTML =  "";
    }
    
    function setView(review) {
    // int review_no; //게시글 번호
    // String uni_id; //주유소 아이디
    // String user_id; //회원 아이디
    // int rating; // 평점을 위한 필드추가
    // String content; //리뷰 내용
        console.log(review);
    	var ul = document.createElement('ul');
    	ul.id = "review";

    	for(var i = 0 ; i < review.length ; i++) {
            var li = document.createElement('li');

            var span = document.createElement('span');
            span.className = 'number';
            span.innerHTML = review[i].user_id;
            li.appendChild(span);

            var span = document.createElement('span');
            span.className = 'title';
            span.innerHTML = review[i].content;
            li.appendChild(span);

            var star = document.createElement("div");
            star.id = "star";

            for(var j = 1 ; j < 6 ; j++)
            {
                var in_star = document.createElement("a");
                in_star.setAttribute("value" , j);

                if(parseInt(review[i].rating) >= j) {
                    in_star.className = "on";
                }

                in_star.textContent = "★";
                star.appendChild(in_star);
            }
            li.appendChild(star);
            ul.appendChild(li);
    	}

    	return ul;
    }
    
    function setReview(uni_id){
    	var review = [];
    	
		$.ajax({
			type : "GET" ,
			url : "/middle/review/index.do",
			async : false ,
			data: "uni_id=" + uni_id,
			dataType: "text",
	        success: function (data, status) {
	        	if(data.length > 0) {
	        		 json = JSON.parse(data);
	        		 for(var i = 0 ; i < json.length ; i++) {
	        			review.push(json[i]);
	        		 }
	        	}
	        },
	        error: function (xhr, err) {
	            alert("연결 실패");
	        }
		})
		
		return review
    }

    function avgAllPrice() {
        $.ajax({
            type: "POST",
            url: "/middle/steller/avgAllPrice.do",
            dataType: "text",
            success: function (data) {
                var json = JSON.parse(data);
            },
            error: function (request, status, error) {
                alert("code = " + request.status + " message = " + request.responseText + " error = " + error); // 실패 시
                // 처리
            }
        });
    }

    function aroundAll() {
        var sc = WGS84_To_KTM_XY(map.getCenter().getLng(), map.getCenter().getLat());
        var product = $('#product').val();
        var center = {"product" : product , "x": sc[0], "y": sc[1]};
        // center 값 확인완료.
        console.log(center);

        var Position = [];
        var Review_Info = [];

        $.ajax({
            type: "POST",
            contentType: 'application/json',
            // 보내는 stringify의 형태지정.
            // 지정 안할시 오류
            async: false,
            url: "/middle/steller/aroundAll.do",
            dataType: "text",
            data: JSON.stringify(center),
            success: function (data) {
                // 데이터가 반환.
                if(data.length > 0) {
                    var json = JSON.parse(data);
                    console.log(json);
                    for (var i = 0; i < json.length; i++) {
                        var trans = KTM_To_WGS84_XY(json[i].GIS_X_COOR, json[i].GIS_Y_COOR);
                        var obj = {
                            content: json[i],
                            latlng: new kakao.maps.LatLng(trans[1], trans[0])
                        }
                        Position.push(obj);
                    }
                }
                else {
                    alert("주변에 주유소가 존재하지 않습니다");
                }
            },
            error: function (request, status, error) {
                alert("code = " + request.status + " message = " + request.responseText + " error = " + error); // 실패 시
                // 처리
            }
        });

        // Priority 요소가 추가됨. + content , latlng
        Position = Marker_Priority(Position);
        set_marker(Position);
    }

    function Marker_Priority(Position) {
        // Postion 요소
        // UNI_ID 주유소코드
        // POLL_DIV_CD
        // 상표(SKE:SK에너지, GSC:GS칼텍스, HDO:현대오일뱅크, SOL:S-OIL, RTO:자영알뜰, RTX:고속도로알뜰,
		// NHO:농협알뜰, ETC:자가상표, E1G: E1, SKG:SK가스
        // OS_NM 상호
        // PRICE 판매가격
        // DISTANCE 기준 위치로부터의 거리 (단위 : m)
        // GIS_X_COOR GIS X좌표(KATEC) GIS_Y_COOR GIS Y좌표(KATEC)
        // 사용법 -> Position.content.PRICE
        var After_Position = Position;

        // ex ) 소나타연비 주입
        var testoil = 11;               // 1L = 1204원 = 11km (testoil) = 11000m
        var meter_price;                // 10m 당 기름 소비량
        								// content.PRICE / testoil * 0.01 -> 10m
										// 당 가격.

        // 가격으로 오름 차순 정렬
        After_Position.sort (function (a , b) {
        	// 미터당 기름값 사용 해당 거리 가득 넣었을때 가격
        	var a_priority = (( a.content.PRICE / testoil * 0.001 ) * a.content.DISTANCE * 2) + a.content.PRICE * 40 ;	// 왔다
																														// 갓다한
																														// 기름값
																														// + 풀로
																														// 넣을때
																														// 기름값.
        	var b_priority = (( b.content.PRICE / testoil * 0.001 ) * b.content.DISTANCE * 2) + b.content.PRICE * 40;
        	
            return a_priority < b_priority ? -1 : a_priority > b_priority ? 1 : 0;
        })

        // 정렬후 순번 부여 .
        for(var i = 0 ; i < After_Position.length ; i++) {
            After_Position[i].priority = i ;
        }
        return After_Position;
    }
    function closeOverlay(e) {
        e.overlay.setMap(null);
    }
});