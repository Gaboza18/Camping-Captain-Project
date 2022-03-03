<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>주소로 장소 표시하기</title>
</head>
<body>
	<h1>위치정보</h1>
	<div style="float:right; padding-right:80px; padding-top:30px;" align="left" >
		<h3>강원도 지점 - 강원도 동해시 촛대바위길 6</h3>
		<h3>경기도 지점 - 경기도 의왕시 월암동 543-3</h3>
		<h3>충청도 지점 - 충청북도 괴산군 불정면 세평리</h3>
		<h3>경상도 지점 - 강동면 호명리 18번지 경주시 경상북도 KR</h3>
		<h3>전라도 지점 - 전라남도 무안군 현경면 해운리 910-5</h3>
		<h3>제주도 지점 - 제주특별자치도 제주시 애월읍 소길리</h3>
	</div>
	<div id="map" style="width:400px; height:300px; margin:0px 0px 0px 100px;"></div><br>

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8c5e7f9c5315c5a75571e95627a0686e&libraries=services">
	</script>

	<script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    	mapOption = {
        	center: new kakao.maps.LatLng(36.7683, 127.6358), // 지도의 중심좌표
        	level: 13 // 지도의 확대 레벨
    	};  
		
		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		
		// 마커를 표시할 위치와 title 객체 배열
		var positions = [
			{
				title:'캠핑족장-강원도점',
				latlng:new kakao.maps.LatLng(37.47835863527382, 129.15902028409388)
			},
			{
				title:'캠핑족장-경기도점',
				latlng:new kakao.maps.LatLng(37.30785609648196, 126.95306226931585)
			},
			{
				title:'캠핑족장-충청도점',
				latlng:new kakao.maps.LatLng(36.857936877263825, 127.82021316931585)
			},
			{
				title:'캠핑족장-경상도점',
				latlng:new kakao.maps.LatLng(35.969468893245896, 129.25261898465791)
			},
			{
				title:'캠핑족장-전라도점',
				latlng:new kakao.maps.LatLng(35.065789252322155, 126.45070459205249)
			},
			{
				title:'캠핑족장-제주도점',
				latlng:new kakao.maps.LatLng(33.43144219013792, 126.37859954657921)
			}
		];
		
		// 마커 이미지의 이미지 주소
		var imageSrc="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
		
		for(var i =0; i<positions.length; i++){
			
			// 마커 이미지의 이미지 크기 
			var imageSize = new kakao.maps.Size(24,35);
			
			// 마커 이미지를 생성
			var markerImage = new kakao.maps.MarkerImage(imageSrc,imageSize);
			
			// 마커 이미지를 생성
			var marker = new kakao.maps.Marker({
				map: map, // 마커를 표시할 지도
				position:positions[i].latlng, // 마커 표시할 위치
				title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시
				image:markerImage // 마커 이미지
			});
		}
	</script>
</body>
</html>