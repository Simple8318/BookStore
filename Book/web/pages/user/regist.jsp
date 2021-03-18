<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会员注册页面</title>
	<%--静态包含引用base标签、css样式、jQuery文件--%>
	<%@ include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		//在页面加载完成之后执行
		$(function () {
			//给注册按钮绑定单击事件
			$("#sub_btn").click(function () {
				// 1.验证用户名：必须由字母、数字、下划线组成，且长度为5~12位
				// 2.验证密码：必须由字母、数组、下划线组成，且长度为5~12位
				// 3.验证确认密码：和密码相同
				// 4.验证邮箱：xxxx@xxx.com
				// 5.验证码：暂时不需要

				// 1.验证用户名：必须由字母、数字、下划线组成，且长度为5~12位
				// 1.1 获取用户输入框里的内容
				var usernameText = $("#username").val();
				// 1.2 创建正则表达式
				var usernamePatt=/^\w{5,12}$/;
				// 1.3 使用test方法验证
				if (!usernamePatt.test(usernameText)){
				// 1.4提示用户结果
					$("span.errorMsg").text("用户名不合法！");
					return false;
				}

				// 2.验证密码：必须由字母、数组、下划线组成，且长度为5~12位
				// 2.1 获取密码输入框里的内容
				var passwordText = $("#password").val();
				// 2.2 创建正则表达式
				var passwordPatt=/^\w{5,12}$/;
				// 2.3 使用test方法验证
				if (!passwordPatt.test(passwordText)){
				// 2.4提示用户结果
					$("span.errorMsg").text("密码不合法！");
					return false;
				}

				// 3.验证确认密码：和密码相同
				// 3.1 获取确认密码内容
				var repwdText=$("#repwd").val();
				// 3.2 与密码对比
				if (repwdText!=passwordText){
					// 3.3 提示用户错误信息
					$("span.errorMsg").text("密码不一致！");
					return  false;
				}

				// 4.验证邮箱：xxxx@xxx.com
				// 4.1 获取邮箱内容
				var emailText=$("#email").val();
				// 4.2 创建正则表达式
				var emailPatt=/^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
				// 4.3 使用test方法验证邮箱是否合法
				if (!emailPatt.test(emailText)){
					// 4.4 提示用户
					$("span.errorMsg").text("邮箱格式不合法！");
					return  false;
				}

				// 5.验证码：暂时只需要有输入即可
				// 5.1 获取用户输入验证码
				var codeText=$("#code").val();
				// 5.2 去掉验证码的前后空格
				codeText=$.trim(codeText);
				// 5.3 验证码是否为空
				if (codeText==null || codeText==""){
					// 5.4 提示用户
					$("span.errorMsg").text("验证码不能为空！");
					return  false;
				}

				// 1.5 用户名合法，清空错误提示框内容
				$("span.errorMsg").text("");
			});

			// 给验证码图片绑定单击刷新事件
			$("#code_img").click(function () {
				// 响应事件function中的this对象，即指代当前正在响应事件的dom对象(#code_img)
				// src为#code_img的路径，可写
				// 为#code_img的路径添加一个时间戳，避免IE、火狐等浏览器从缓存中获取资源的情况(无法刷新图片)
				this.src="${basePath}kaptcha.jpg?date="+new Date();
			});
		});
	</script>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" src="static/img/logo.png" style="width: 200px;height: 80px" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>用户注册</h1>
								<span class="errorMsg">
									<%--动态获取错误信息并显示--%>
									<%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>
								</span>
							</div>
							<div class="form">
								<%--使用userServlet，按照不同的value值，执行不同的doPost内容--%>
								<form action="userServlet" method="post">
									<%--注册表单的隐藏域--%>
									<input type="hidden" name="action" value="regist">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   <%--回显用户名--%>
										   value="<%=request.getAttribute("username")==null?"":request.getAttribute("username")%>"
										   autocomplete="off" tabindex="1" name="username" id="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
										   autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   <%--动态回显邮箱信息--%>
										   value="<%=request.getAttribute("email")==null?"":request.getAttribute("email")%>"
										   autocomplete="off" tabindex="1" name="email" id="email" />
									<br />
									<br />
									<label>验 证 码 ：</label>
									<input class="itxt" type="text" name="code"
										   placeholder="请输入对应验证码"
										   style="width: 125px;" id="code"/>
									<img alt="点击刷新" src="kaptcha.jpg" id="code_img"
										 style="float: right; margin-right: 50px; margin-top: 5px; width: 75px;height: 30px "><br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
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