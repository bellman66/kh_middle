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
	var Makers = [] ;
	
	// --- 1. 현재 위치 지정 및 옵션 지정.
	set_option();
	
	// === 1. end.
	
	// --- 2. 현재 위치에서 반경 1km이내에 주유소 가격 가져옴.
//	avgAllPrice();
	aroundAll();	// -- Maker 까지 제작됨.

	// === 2. end


	
	function set_option() {
		// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
		var mapTypeControl = new kakao.maps.MapTypeControl();
		map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
		
		// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
		var zoomControl = new kakao.maps.ZoomControl();
		map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

	}
	
	$('#find_button').click(function(){
		// 현재 위치 이동
		navigator.geolocation.getCurrentPosition(function(position) {
  			goToCenter(position.coords.latitude, position.coords.longitude);
		});

		// marker 찍음.
		aroundAll();

		
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
	    var moveLatLon = new kakao.maps.LatLng(x, y);
	    
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
	
	function set_marker(Position) {

		if(Makers.length != 0) {
			// 기존 마커 초기화 ;
			for (var j = 0; j < Makers.length ; j++){
				Makers[j].setMap(null);
			}

			Makers = [];
		}

		for (var i=0; i<Position.length; i++) {
			// 정상 진입. 

		    // 마커를 생성합니다
		    var marker = new kakao.maps.Marker({
				map: map ,
		        position: Position[i].latlng // 마커의 위치
		    });

		    // 마커에 표시할 인포윈도우를 생성합니다 
		    var infowindow = new kakao.maps.InfoWindow({
		        content: Position[i].content // 인포윈도우에 표시할 내용
			});
			
		    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
		    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
		    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
		    kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
		    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
			
			// 마커 저장.
			Makers.push(marker);
		}
	}
	
	function avgAllPrice() {
		$.ajax ({
			type: "POST" ,
			url: "http://localhost:8070/middle/steller/avgAllPrice.do",
			dataType : "text",
			success:function(data){
				 var json = JSON.parse(data);
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
		var Position = [];

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
				
				for(var i = 0 ; i < json.length ; i++){
					var trans = KTM_To_WGS84_XY(json[i].GIS_X_COOR , json[i].GIS_Y_COOR);
					var obj = { content : '<div>' + json[i].OS_NM + ' / ' + json[i].PRICE +'</div>' ,
						     	latlng: new kakao.maps.LatLng(trans[1], trans[0])};
					Position.push(obj);
				}
			} ,
		    error:function(request , status ,error){
		    	 alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
		    }
		});

		set_marker(Position);
	}
	

});