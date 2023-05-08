<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        table, tr, td {
            border: 1px solid black;
            border-collapse: collapse;
        }

        .table_nav {
            background: darkcyan;
            text-align: center;
            color: white;
        }

        .table_view {

        }
    </style>
    <script>
        // function wifi_Load() {
        //     fetch('http://openapi.seoul.go.kr:8088/76697a5052706f73363669456d7865/json/TbPublicWifiInfo/1/5/')
        //         .then((response) => response.json())
        //         .then((data) => console.log(data));
        // }
    </script>

</head>
<body>
<section>
    <div>
        <h1>와이파이 정보 구하기</h1>
    </div>
    <div>
        <form action="save_db" method="post">
            <a href="/main.jsp">홈</a>
            <span>|</span>
            <a href="">위치 히스토리 목록</a>
            <span>|</span>
            <input type="submit" value="open API 와이파이 정보 가져오기">
        </form>
    </div>
    <br>
</section>

<section>
    <form action="myLocate">
        <label for="LAT">LAT: </label>
        <input type="text" name="LAT" id="LAT">
        <label for="LNT">LNT: </label>
        <input type="text" name="LNT" id="LNT" value="">
        <input type="button" onclick="mylocate()" value="내 위치 가져오기">
        <input type="submit" value="근처 wipi">
    </form>
    <table>
        <tr class="table_nav">
            <td>거리(Km)</td>
            <td>관리번호</td>
            <td>자치구</td>
            <td>와이파이명</td>
            <td>도로명 주소</td>
            <td>상세 주소</td>
            <td>설치위치(층)</td>
            <td>설치유형</td>
            <td>설치기관</td>
            <td>서비스 구분</td>
            <td>망 종류</td>
            <td>설치년도</td>
            <td>실내외 구분</td>
            <td>WIFI접속 환경</td>
            <td>X좌표</td>
            <td>Y좌표</td>
            <td>작업일자</td>
        </tr>
        <br>
        <tr class="table_view">
            <td>위치 정보를 입력한 후에 조회해 주세요</td>
        </tr>

    </table>


</section>

</body>
<script>
function mylocate(){
    navigator.geolocation.getCurrentPosition(geoSuccess,getError);
    function geoSuccess(position){
        //위도
        const lat = position.coords.latitude;
        //경도
        const lnt = position.coords.longitude;

    }
    function getError(){
        alert('현재 위치 가져오기 실패');
    }

}

</script>
</html>