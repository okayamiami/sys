<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>メニュー</title>

</head>
<body>
		<form action = "Menu.action" method="post">
		<%
		session.setAttribute("userType1", session.getAttribute("m"));
		session.setAttribute("userType2", session.getAttribute("t"));
		session.setAttribute("userType3", session.getAttribute("p"));
		%>

			<c:choose>
				<c:when test="${userType1 == 'm'}">
					<h2>管理者</h2>
					<a href="NewRegist.action">新規登録</a>
					<a href="名簿情報一覧">名簿情報一覧</a>
					<a href="保護者情報">保護者情報</a>
					<a href="子供情報">子供情報</a>
					<a href="AbsenceReport.action">欠席報告</a>
					<a href="お知らせ機能">お知らせ機能</a>
					<a href="QR機能">QR機能</a>
				</c:when>
				<c:when test="${userType2 == 't'}">
					<h2>先生</h2>
					<a href="名簿情報一覧">名簿情報一覧</a>
					<a href="AbsenceSelect.action">欠席機能</a>
					<a href="お知らせ機能">お知らせ機能</a>
					<a href="QR機能">QR機能</a>
				</c:when>
				<c:when test="${userType3 == 'p'}">
					<h2>保護者</h2>
					<a href="保護者情報">保護者情報</a>
					<a href="子供情報">子供情報</a>
					<a href="AbsenceSelect.action">欠席機能</a>
					<a href="お知らせ機能">お知らせ機能</a>
				</c:when>
			</c:choose>

		</form>

</body>
</html>