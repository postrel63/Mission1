<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href = "../css/mystyle.css">
</head>
<body>
<section>
    <div>
        <h1>히스토리</h1>
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
</section>

<section>

    <table class="table_view">
        <thead>

        <tr class="table_nav">
            <td>ID</td>
            <td>x좌표</td>
            <td>y좌표</td>
            <td>조회 시각</td>
            <td>비고</td>

        </tr>
        </thead>
        <br>
        <tbody>
        <c:forEach var="n" items="${list}">

            <tr class="row">
                <td>${n.id}</td>
                <td>${n.x}</td>
                <td>${n.y}</td>
                <td>${n.date}</td>
                <td>
                    <button value="삭제" onclick="location.href='deleteHistory?dNum=${n.id}'">삭제</button>
                </td>

            </tr>
        </c:forEach>
        </tbody>

    </table>


</section>

</body>
</html>
