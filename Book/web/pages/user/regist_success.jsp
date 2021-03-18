<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会员注册成功页面</title>
	<%--静态包含引用base标签、css样式、jQuery文件--%>
	<%@ include file="/pages/common/head.jsp"%>
	<style type="text/css">
		h1 {
			text-align: center;
			margin-top: 200px;
		}
	</style>
</head>
<body>
		<div id="header">
			<img class="logo_img" src="static/img/logo.png" style="width: 200px;height: 80px" >
			<span class="wel_word"></span>

			<%--使用静态包含，添加/pages/common/login_success_menu.jsp下的相同内容--%>
			<%@ include file="/pages/common/login_success_menu.jsp"%>
		</div>
		
		<div id="main">
			<h1>注册成功! <a href="index.jsp" >转到主页</a></h1>
		</div>

		<%--使用静态包含，引用相同的页脚内容--%>
		<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>