<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%--静态包含引用base标签、css样式、jQuery文件--%>
	<%@ include file="/pages/common/head.jsp"%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.png" style="width: 200px;height: 80px" >
			<span class="wel_word">订单管理系统</span>
		<%--静态包含后台管理模块的相同菜单--%>
		<%@ include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>单号</td>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>客户ID</td>
			</tr>
			<%--通过JSTL表达式获取Request域中保存的全部订单信息--%>
			<c:forEach items="${sessionScope.orders}" var="order">
				<tr>
					<td>${order.orderId}</td>
					<td>${order.createTime}</td>
					<td>${order.price}</td>
					<td>
						<c:if test="${order.status==0}">进行中</c:if>
						<c:if test="${order.status==1}">已发货</c:if>
					</td>
					<td>${order.userId}</td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="index.jsp">返回首页</a></td>
			</tr>
		</table>
	</div>

	<%--使用静态包含，引用相同的页脚内容--%>
	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>