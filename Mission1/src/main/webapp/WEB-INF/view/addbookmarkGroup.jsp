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
        <h1>위치 히스토리 구하기</h1>
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
        <button type="button" onclick="location.href='addBookmarkGroup'">북마크 그룹 이름 추가</button>
    </div>
</section>

<section>

    <table class="table_view">
        <thead>
        <form action="addBookmarkGroup">

            <tr>
                <td class="table_col">북마크 이름</td>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <td class="table_col">순서</td>
                <td><input type="text" name="sequence"></td>
            </tr>

            <tr>
                <td colspan="2">
                    <input type="submit" value="추가">
                </td>
            </tr>
        </form>

        </tr>
        </thead>
        <br>
        <tbody>

        </tbody>

    </table>


</section>

</body>
</html>
