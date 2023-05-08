<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" type="text/css" href="../css/mystyle.css">
</head>
<body>
<section>
    <div>
        <h1>와이파이 정보 구하기</h1>
    </div>
    <div>
        <a href="/">홈</a>
        <span>|</span>
        <a href="history">위치 히스토리 목록</a>
        <span>|</span>
        <a href="apiDownload">open API 와이파이 정보 가져오기</a>
        <span>|</span>
        <a href="showBookmark">북마크 보기</a>
        <span>|</span>
        <a href="manageBookmark">북마크 그룹 관리</a>

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
    <br>
    <table class="table_view">
        <thead>
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
        </thead>

        <tbody>


        <c:choose>
            <c:when test="${not empty wifilist}">
                <tr style="display: none;">
                    <td colspan="17" class="table_default">위치정보를 먼저 조회해주세요</td>
                </tr>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="17" class="table_default">위치정보를 먼저 조회해주세요</td>
                </tr>
            </c:otherwise>
        </c:choose>


        <c:forEach var="list" items="${wifilist}">

            <tr class="row">
                <td>${list.distance}</td>
                <td>${list.x_SWIFI_MGR_NO}</td>
                <td>${list.x_SWIFI_WRDOFC}</td>
                <td><a href="wifiDetail?wId=${list.x_SWIFI_MGR_NO}">${list.x_SWIFI_MAIN_NM}</a></td>
                <td>${list.x_SWIFI_ADRES1}</td>
                <td>${list.x_SWIFI_ADRES2}</td>
                <td>${list.x_SWIFI_INSTL_FLOOR}</td>
                <td>${list.x_SWIFI_INSTL_TY}</td>
                <td>${list.x_SWIFI_INSTL_MBY}</td>
                <td>${list.x_SWIFI_SVC_SE}</td>
                <td>${list.x_SWIFI_CMCWR}</td>
                <td>${list.x_SWIFI_CNSTC_YEAR}</td>
                <td>${list.x_SWIFI_INOUT_DOOR}</td>
                <td>${list.x_SWIFI_REMARS3}</td>
                <td>${list.lat}</td>
                <td>${list.lnt}</td>
                <td>${list.work_DTTM}</td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
</section>

</body>
</html>

<script>
    function mylocate() {
        navigator.geolocation.getCurrentPosition(geoSuccess, getError);

        function geoSuccess(position) {
            //위도
            document.getElementById("LAT").value = position.coords.latitude;
            console.log(position.coords.latitude);
            //경도
            document.getElementById("LNT").value = position.coords.longitude;

        }

        function getError() {
            alert('현재 위치 가져오기 실패');
        }

    }

</script>