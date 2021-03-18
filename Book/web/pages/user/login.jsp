<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会员登录页面</title>
	<%--静态包含引用base标签、css样式、jQuery文件--%>
	<%@ include file="/pages/common/head.jsp"%>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" src="static/img/logo.png" style="width: 200px;height: 80px" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎使用</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>欢迎登录</h1>
								<a href="pages/user/regist.jsp">前往注册</a>
							</div>
							<div class="msg_cont">
								<b></b>
								<span class="errorMsg">
									<%--动态获取并显示错误信息--%>
									<%=request.getAttribute("msg")==null?"请输入用户名和密码":request.getAttribute("msg")%>
								</span>
							</div>
							<div class="form">
								<%--使用userServlet，按照不同的value值，执行不同的doPost内容--%>
								<form action="userServlet" method="post">
									<%--登录表单的隐藏域--%>
									<input type="hidden" name="action" value="login">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   autocomplete="off" tabindex="1" name="username"
										   <%--动态回显用户名--%>
										   value="<%=request.getAttribute("username")==null?"":request.getAttribute("username")%>"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<input type="submit" value="登录" id="sub_btn" />
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%--使用静态包含，引用相同的页脚内容--%>
		<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>