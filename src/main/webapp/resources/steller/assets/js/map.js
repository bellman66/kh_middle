$(function () {

    // 중요
    // 해당 객체들을 통해서 html이 움직여짐.
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
        imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

        BlueMarker = new kakao.maps.MarkerImage('/middle/resources/image/icon_MarkerBlue.png', imageSize, imageOption)
        RedMarker = new kakao.maps.MarkerImage('/middle/resources/image/icon_MarkerRed.png', imageSize, imageOption)
        GreenMarker = new kakao.maps.MarkerImage('/middle/resources/image/icon_MarkerGreen.png', imageSize, imageOption)
    }

    $('#find_button').click(function () {
        // 현재 위치 이동
        navigator.geolocation.getCurrentPosition(function (position) {
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

    function makeOverListener(map, overlay) {
        return function () {
            overlay.setMap(map);
        };
    }

    function makeOutListener(overlay) {
        return function () {
            overlay.setMap(null);
        };
    }

    // ### main 기능 - 반환받은 마커를
    // 순서대로 찍음.
    function set_marker(Position) {

        //  1. marker 초기화.
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
            // 상표(SKE:SK에너지, GSC:GS칼텍스, HDO:현대오일뱅크, SOL:S-OIL, RTO:자영알뜰, RTX:고속도로알뜰, NHO:농협알뜰, ETC:자가상표, E1G: E1,  SKG:SK가스
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

            // 마커에 표시할 인포윈도우를 생성합니다
            // var infowindow = new kakao.maps.InfoWindow({
            // // 1. 인포 윈도우 설정.
            // content: Position[i].content
            // });

            var overlay = set_Overlay(Position[i]);

            // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
            // 이벤트 리스너로는 클로저를 만들어 등록합니다
            // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
//		    kakao.maps.event.addListener(marker, 'click', makeOverListener(map,overlay));
//		    kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, overlay));
//		    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(overlay));

            kakao.maps.event.addListener(marker, 'click', makeOverListener(map, overlay));
            // kakao.maps.event.addListener(marker, 'mouseover', function () {
            //     overlay.setMap(map);
            // });
            kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(overlay));

            // 마커 저장.
            Makers.push(marker);
        }
    }

    function set_Overlay(data) {
        // data.content.OS_NM
        // 리뷰가 없을때는 -> 새로 생성 <c:if >
        // 별점도 없을때 -> 새로 생성. <c:if>

        // 주요 정보 : PRICE 판매가격
        // DISTANCE 기준 위치로부터의 거리 (단위 : m)
        var view = '<div class="overlaybox">' +
            '<div class="boxtitle">' + data.content.OS_NM + '</div>' +
            '   <ul>' +
            '       <li>' +
            '           <span class="title">' + 'os_NM : ' + data.content.UNI_ID + '</span>' +
            '       </li>' +
            '       <li>' +
            '           <span class="title">' + '가격 : ' + data.content.PRICE + '</span>' +
            '       </li>' +
            '       <li>' +
            '           <span class="title">' + '거리 : ' + data.content.DISTANCE + '</span>' +
            '       </li>' +
            '   </ul>'
            /* main 이미지 서식 가능 .*/
            // '    <div class="first">' +
            // '        <div class="triangle text">1</div>' +
            // '        <div class="movietitle text">드래곤 길들이기2</div>' +
            // '    </div>' +

            /* 밑 서식 - list 처럼 사용가능 */
            // '    <ul>' +
            // '        <li class="up">' +
            // '            <span class="number">2</span>' +
            // '            <span class="title">명량</span>' +
            // '            <span class="arrow up"></span>' +
            // '            <span class="count">2</span>' +
            // '        </li>' +
            // '    </ul>' ;
            '</div>';

            /* 주의 : map 설정을 할 경우 자동으로 바로 생성되니 조심 */
        return new kakao.maps.CustomOverlay({
            // 1. 인포 윈도우 설정.
            content: view,
            position: data.latlng,
            yAnchor: 1.5,
            clickable: true,
            zIndex : 1
        });
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
        var sc = WGS84_To_KTM_XY(map.getCenter
        ().getLng(), map.getCenter().getLat());
        var center = {"x": sc[0], "y": sc[1]};
        // center 값 확인완료.
        var Position = [];

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
                var json = JSON.parse(data);

                for (var i = 0; i < json.length; i++) {
                    var trans = KTM_To_WGS84_XY(json[i].GIS_X_COOR, json[i].GIS_Y_COOR);
                    var obj = {
                        content: json[i],
                        latlng: new kakao.maps.LatLng(trans[1], trans[0])
                    }
                    Position.push(obj);
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
        // 상표(SKE:SK에너지, GSC:GS칼텍스, HDO:현대오일뱅크, SOL:S-OIL, RTO:자영알뜰, RTX:고속도로알뜰, NHO:농협알뜰, ETC:자가상표, E1G: E1,  SKG:SK가스
        // OS_NM 상호
        // PRICE 판매가격
        // DISTANCE 기준 위치로부터의 거리 (단위 : m)
        // GIS_X_COOR GIS X좌표(KATEC) GIS_Y_COOR GIS Y좌표(KATEC)
        // 사용법 -> Position.content.PRICE
        var After_Position = Position;

        // ex ) 소나타연비 주입
        var testoil = 11;               // 1L 당 1204 ,  11km = 11000m
        var meter_price =               // 10m 당 기름 소비량

        // 가격으로 오름 차순 정렬
        After_Position.sort (function (a , b) {
            return a.content.PRICE < b.content.PRICE ? -1 : a.content.PRICE > b.content.PRICE ? 1 : 0;
        })

        // 정렬후 순번 부여 .
        for(var i = 0 ; i < After_Position.length ; i++) {
            After_Position[i].priority = i ;
        }
        return After_Position;
    }
});