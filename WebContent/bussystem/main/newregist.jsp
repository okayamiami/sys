<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>

<c:import url="/common/header.jsp" />

<div class="main">
<c:import url="/common/navi.jsp" />

<div class ="con">

<h2>新規情報登録</h2>

	<form action = "NewRegistExecute.action" method="post">
		<label>新規ユーザー区分</label>
		<select name="user_status">
			<option value="0">--------</option>
			<c:forEach var="user_status" items="${user_status}">
				<%-- 現在のyearと選択されていたent_yearが一致していた場合selectedを追記 --%>
				<option value="${user_status}">${user_status}</option>
			</c:forEach>
		</select>
		<div>${errors.get("user_status")}</div>

		<input type="submit" value="登録して終了">
	</form>

	<%--<a href="StudentList.action">戻る</a>--%>
</div>
</div>
</body>
<c:import url="/common/footer.jsp" />
</html>