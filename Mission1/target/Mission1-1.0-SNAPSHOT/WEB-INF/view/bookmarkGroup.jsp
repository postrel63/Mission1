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
        <h1>북 마크 그룹</h1>
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
        <br>
        <button type="button" onclick="location.href='addBookmarkGroupPage'">북마크 그룹 이름 추가</button>
    </div>
</section>

<section>

    <table class="table_view">
        <thead>

        <tr class="table_nav">
            <td>ID</td>
            <td>북마크 이름</td>
            <td>순서</td>
            <td>등록 일자</td>
            <td>수정일자</td>
            <td> 비고</td>

        </tr>
        </thead>
        <br>
        <tbody>
        <c:forEach var="list" items="${list}">
            <tr class="row">
                <td>${list.id}</td>
                <td>${list.name}</td>
                <td>${list.sequence}</td>
                <td>${list.addDate}</td>
                <td>${list.modifyDate}</td>
                <td><a href="modifyBookmark?Nid=${list.id}">수정</a> <a href="deleteBookmarkGroup?Nid=${list.id}">삭제</a></td>
            </tr>
        </c:forEach>

        </tbody>

    </table>


</section>

</body>
</html>
