$(function() {
	
	// 중요 
	// 해당 객체들을 통해서 html이 움직여짐.
	
	var local = new kakao.maps.Coords(5000.0 , 100000.0);
	
	var mapcontainer = document.getElementById('map');
	var maplocation = { 
						// center: local.toLatLng(), // 지도의 중심좌표
						center: new kakao.maps.LatLng(37.484632786467756, 127.03046010240786) ,
						level: 3 // 지도의 확대 레벨
					  };
	var map	= new kakao.maps.Map(mapcontainer, maplocation);	// 실질적으로 연계되어 그리는 객체.
	var Maker_Position = [];
	
	// 2. 추가 컨트롤러.
	set_option();
	
	// === 2 end.
	
	// 3. 마커 등록
//	set_marker();
	
	// === 3 end 
	
	
	// test 칸	========
//	avgAllPrice();
	aroundAll();
	
	// --- 4. 서비스 검색.
//	var places = new kakao.maps.services.Places();
//	places.setMap(map);
//	
//	var callback = function(result, status) {
//	    if (status === kakao.maps.services.Status.OK) {
//	        alert(result);
//	    }
//	};
//
//	// 공공기관 코드 검색
//	places.categorySearch('OL7', callback, {
//	    // Map 객체를 지정하지 않았으므로 좌표객체를 생성하여 넘겨준다.
//		useMapCenter:true ,
//		useMapBounds:true ,
//	    radius: 1000
//	});
//	
//	alert(callback.result);
	
	
	// === 4 end
	
	function set_option() {
		// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
		var mapTypeControl = new kakao.maps.MapTypeControl();
		map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
		
		// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
		var zoomControl = new kakao.maps.ZoomControl();
		map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

	}
	
	$('#find_button').click(function(){
		 $.ajax ({
			 type: "GET" ,
			 url: "https://dapi.kakao.com/v2/local/geo/transcoord.json" ,
		     async:false ,
		     headers: {
		    	 "Authorization" : "KakaoAK " + "732d43eaffe0e338eb5b1d0a7a9710fc"
		     } ,
		     dataType: "json" ,
		     data: {
		    	 "x": 315168 ,
		    	 "y": 541278 ,
		    	 "input_coord": "KTM" ,
		    	 "output_coord": "WGS84"
		     } ,
		     success:function(data , status){
				 alert("ajax 성공");	// 성공완료
				 alert( JSON.stringify(data.documents[0]) );
				 
				 var x = Number( JSON.stringify(data.documents[0].x) );
				 var y = Number( JSON.stringify(data.documents[0].y) );
				 
				 goToCenter(x , y);
				 aroundAll();
			 } ,
		     error:function(xhr , err){
		    	 alert("연결 실패");
		     }
		 });
//		 .done(function(json) {
//			 alert("통신 성공");	// 통신 성공완료
//		 })
//		 
//		 .fail(function() { 
//			 alert("실패"); 		// 실패시
//		 }) 
//		 .always(function() { 
//			 alert("완료"); 	// 항상 나오는 구문.
//		 });

		
	});
	
	// 좌표 변환 결과를 받아서 처리할 콜백함수 입니다.
	function transCoordCB(result, status) {

	    // 정상적으로 검색이 완료됐으면 
	    if (status === kakao.maps.services.Status.OK) {

	        // 마커를 변환된 위치에 표시합니다
	        var marker = new kakao.maps.Marker({
	            position: new kakao.maps.LatLng(result[0].y, result[0].x), // 마커를 표시할 위치입니다
	            map: map // 마커를 표시할 지도객체입니다
	        })
	    }
	}
	
	// == == == 좌표계 변환 
	
	function KTM_To_WGS84_XY(x , y) {
		var Xy_Number = new Array();
		
		$.ajax ({
			 type: "GET" ,
			 url: "https://dapi.kakao.com/v2/local/geo/transcoord.json" ,
		     headers: {
		    	 "Authorization" : "KakaoAK " + "732d43eaffe0e338eb5b1d0a7a9710fc"
		     } ,
		     async:false ,
		     dataType: "json" ,
		     data: {
		    	 "x": x ,
		    	 "y": y ,
		    	 "input_coord": "KTM" ,
		    	 "output_coord": "WGS84"
		     } ,
		     success:function(data , status){
				 Xy_Number.push( Number(JSON.stringify(data.documents[0].x)) );
				 Xy_Number.push( Number(JSON.stringify(data.documents[0].y)) );
			 } ,
		     error:function(xhr , err){
		    	 alert("연결 실패");
		     }
		 });
		
		return Xy_Number;
	}
	
	function WGS84_To_KTM_XY(x , y) {
		var Xy_Number = new Array();
		
		$.ajax ({
			 type: "GET" ,
			 url: "https://dapi.kakao.com/v2/local/geo/transcoord.json" ,
		     headers: {
		    	 "Authorization" : "KakaoAK " + "732d43eaffe0e338eb5b1d0a7a9710fc"
		     } ,
		     async:false ,
		     dataType: "json" ,
		     data: {
		    	 "x": x ,
		    	 "y": y ,
		    	 "input_coord": "WGS84" ,
		    	 "output_coord": "KTM"
		     } ,
		     success:function(data , status){
				 Xy_Number.push( Number(JSON.stringify(data.documents[0].x)) );
				 Xy_Number.push( Number(JSON.stringify(data.documents[0].y)) );
		     } ,
		     error:function(xhr , err){
		    	 alert("연결 실패");
		     }
		 });
		
		return Xy_Number;
	}
	
	// == == == 좌표계 변환 end 
	
	function goToCenter(x , y) {
	    // 이동할 위도 경도 위치를 생성합니다 
	    var moveLatLon = new kakao.maps.LatLng(y , x);
	    
	    // 지도 중심을 부드럽게 이동시킵니다
	    // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
	    map.panTo(moveLatLon);            
	}      
	
	// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
	function makeOverListener(map, marker, infowindow) {
	    return function() {
	        infowindow.open(map, marker);
	    };
	}

	// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
	function makeOutListener(infowindow) {
	    return function() {
	        infowindow.close();
	    };
	}
	
	function set_marker() {
//		var positions = 			
//		[
//		    {
//		        content: '<div>카카오</div>', 
//		        latlng: new kakao.maps.LatLng(33.450705, 126.570677)
//		    },
//		    {
//		        content: '<div>생태연못</div>', 
//		        latlng: new kakao.maps.LatLng(33.450936, 126.569477)
//		    },
//		    {
//		        content: '<div>텃밭</div>', 
//		        latlng: new kakao.maps.LatLng(33.450879, 126.569940)
//		    },
//		    {
//		        content: '<div>근린공원</div>',
//		        latlng: new kakao.maps.LatLng(33.451393, 126.570738)
//		    }
//		];
		
		for (var i = 0; i < Maker_Position.length; i ++) {
			// 정상 진입. 
			alert(i + "번 : " + Maker_Position[i].latlng + " / " + Maker_Position[i].content);
			
		    // 마커를 생성합니다
		    var marker = new kakao.maps.Marker({
		        map: map, // 마커를 표시할 지도
		        position: Maker_Position[i].latlng // 마커의 위치
		    });

		    // 마커에 표시할 인포윈도우를 생성합니다 
		    var infowindow = new kakao.maps.InfoWindow({
		        content: Maker_Position[i].content // 인포윈도우에 표시할 내용
		    });

		    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
		    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
		    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
		    kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
		    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
		}
	}
	
	function avgAllPrice() {
		// cors 해결 x 
//		$.ajax ({
//			 type: "POST" ,
//			 url: "http://www.opinet.co.kr/api/avgAllPrice.do?out=json&code=F691200220" ,
//			 dataType:'jsonp',
//			 crossDomain : true,
//			 xhrFields: {
//			     withCredentials: true
//			 }, 
//		     beforeSend: function() {
//		    	 alert("접근전");
//		     },
//			 success:function(data){
//				 alert("test 성공");	// 성공완료
//				 alert(data);
//			 } ,
//		     error:function(request , status ,error){
//		    	 alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
//		     }
//		 });
		
		$.ajax ({
			type: "POST" ,
			url: "http://localhost:8070/middle/steller/avgAllPrice.do",
			dataType : "text",
			success:function(data){
				 var json = JSON.parse(data);

				 for(var i = 0 ; i < json.length; i++) {
					 alert(i +"번 데이터 : " +json[i].TRADE_DT);
				 }
			} ,
		    error:function(request , status ,error){
		    	 alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
		    }
		});
	}
	
	function aroundAll() {
		var sc = WGS84_To_KTM_XY( map.getCenter
				().getLng() , map.getCenter().getLat());
		var center = { "x" : sc[0] , "y" : sc[1] };
		// center 값 확인완료.
		
		$.ajax({
			type : "POST" ,
			contentType: 'application/json',	
			//  보내는 stringify의 형태지정.
			// 지정 안할시 오류
		    async:false ,
			url : "http://localhost:8070/middle/steller/aroundAll.do",
			dataType : "text" ,
			data : JSON.stringify(center),
			success : function(data){
				var json = JSON.parse(data);
				Maker_Position = [];
				
				for(var i = 0 ; i < json.length ; i++){
					var trans = KTM_To_WGS84_XY(json[i].GIS_X_COOR , json[i].GIS_Y_COOR);
					var obj = { content : '<div>' + json[i].OS_NM + ' / ' + json[i].PRICE +'</div>' ,
						     	latlng: new kakao.maps.LatLng(trans[1], trans[0])};
					Maker_Position.push(obj);
				}
			} ,
		    error:function(request , status ,error){
		    	 alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
		    }
		});
		set_marker();
	}
	

});