<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >

<html>
<body>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='styleshhet' href=''>

<title>登園・バス管理システム</title>
</head>
<div class="header">
<div>
	<h1>登園・バス管理システム</h1>

	<h2>ログイン</h2>

	<div>${errors.get("null")}</div>
</div>
</div>

<form action = "LoginExecute.action" method="post">

	<div class="log">
		<label>ID</label>
		<%-- value=T0000001 --%>
		<%-- value=P2400001 --%>
		<input type="text" name="user_id" maxlength="20" value="P2400001"
		required
		><br>
	</div>

	<div class="pas">

		<label>パスワード</label>
		<%-- value=kanasen01 --%>
		<%-- value=nohara111 --%>
		<input type="password" id="user_pass" name="user_pass" maxlength="20" value="nohara111" required/><br>
		<input type="checkbox" id="showPassword" onchange="togglePasswordVisibility()" />
		<label for="showPassword">パスワードを表示</label>

		<script>
			function togglePasswordVisibility() {
				let passwordInput = document.getElementById("password");
				let showPasswordCheckbox = document.getElementById("showPassword");

				if (showPasswordCheckbox.checked) {
					passwordInput.type = "text";
				} else {
					passwordInput.type = "password";
				}
			}
		</script><br>
	</div>

		<label>facility_ID</label>

		<input type="text" name="facility_id" maxlength="20" value="KK000"required><br>


		<input type="submit" name="login" value="ログイン"/>


</form>

</body>
</html>