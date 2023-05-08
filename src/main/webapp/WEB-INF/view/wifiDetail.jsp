<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" type="text/css" href = "../css/mystyle.css">
</head>
<body>
<section>
    <div>
        <h1>와이파이 상세보기</h1>
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
            <a href="manageBookmark">북마크 관리</a>

    </div>
    <br>
</section>

<section>
    <form action="addBookmark" method="get">
        <select name="bId">
            <c:forEach var="list" items="${bookmarkGroupList}">
                <option value="${list.id}">${list.name}</option>
            </c:forEach>
        </select>
        <input type="hidden" name="wName" value="${list.x_SWIFI_MAIN_NM}">
        <input type="submit" value="북마크 추가">
    </form>

    <c:if test="${addResult == 1}">
        <script>
            alert("북마크 추가 성공");
        </script>
    </c:if>

    <br>
    <table>
        <tr>
            <td class="table_col">거리(Km)</td>
            <td>${list.distance}</td>
        <tr>
        <tr>
            <td class="table_col">관리번호</td>
            <td>${list.x_SWIFI_MGR_NO}</td>

        </tr>
        <tr>
            <td class="table_col">자치구</td>
            <td>${list.x_SWIFI_MGR_NO}</td>
        </tr>
        <tr>
            <td class="table_col">와이파이명</td>
            <td>${list.x_SWIFI_MAIN_NM}</td>
        </tr>
        <tr>
            <td class="table_col">도로명 주소</td>
            <td>${list.x_SWIFI_ADRES1}</td>
        </tr>
        <tr>
            <td class="table_col">상세 주소</td>
            <td>${list.x_SWIFI_ADRES2}</td>
        </tr>
        <tr>
            <td class="table_col">설치위치(층)</td>
            <td>${list.x_SWIFI_INSTL_FLOOR}</td>
        </tr>
        <tr>
            <td class="table_col">설치유형</td>
            <td>${list.x_SWIFI_INSTL_TY}</td>
        </tr>
        <tr>
            <td class="table_col">설치기관</td>
            <td>${list.x_SWIFI_INSTL_MBY}</td>
        </tr>
        <tr>
            <td class="table_col">서비스 구분</td>
            <td>${list.x_SWIFI_SVC_SE}</td>
        </tr>
        <tr>
            <td class="table_col">망 종류</td>
            <td>${list.x_SWIFI_CMCWR}</td>
        </tr>
        <tr>
            <td class="table_col">설치년도</td>
            <td>${list.x_SWIFI_CNSTC_YEAR}</td>
        </tr>
        <tr>
            <td class="table_col">실내외 구분</td>
            <td>${list.x_SWIFI_INOUT_DOOR}</td>
        </tr>
        <tr>
            <td class="table_col">WIFI접속 환경</td>
            <td>${list.x_SWIFI_REMARS3}</td>
        </tr>
        <tr>
            <td class="table_col">X좌표</td>
            <td>${list.lat}</td>
        </tr>
        <tr>
            <td class="table_col">Y좌표</td>
            <td>${list.lnt}</td>
        </tr>
        <tr>
            <td class="table_col">작업일자</td>
            <td>${list.work_DTTM}</td>
        </tr>
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