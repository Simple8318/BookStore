<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
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
		<span class="wel_word">我的订单</span>
		<%--使用静态包含，添加/pages/common/login_success_menu.jsp下的相同内容--%>
		<%@ include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">

		<table>
			<c:if test="${empty sessionScope.ordersOfMy}">
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<td colspan="5" style="font-size: 25px;margin: 5px auto">您暂时并未购买商品!!</td>
				<br/>
				<br/>
				<tr>
					<td colspan="5" style="text-align: center;font-size: 30px;margin: 10px auto">
						请前往<a href="index.jsp" style="color: red;font-size: 35px">首页</a>进行商品选购吧！
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty sessionScope.ordersOfMy}">
				<tr>
					<td>日期</td>
					<td>金额</td>
					<td>状态</td>
					<td>确认</td>
				</tr>
				<c:forEach items="${sessionScope.ordersOfMy}" var="order">
					<tr>
						<td>${order.createTime}</td>
						<td>${order.price}</td>
						<td>
							<c:if test="${order.status==0}">进行中</c:if>
							<c:if test="${order.status==1}">已发货</c:if>
						</td>
						<td><a href="index.jsp">返回首页</a></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		
	
	</div>

	<%--使用静态包含，引用相同的页脚内容--%>
	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>