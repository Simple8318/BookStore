<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%--静态包含引用base标签、css样式、jQuery文件--%>
	<%@ include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			// 给删除商品项标签绑定单击事件
			$("a.deleteItem").click(function () {
				return confirm("您确定沿删除【"+$(this).parent().parent().find("td:first").text()+"】吗?");
			});

			// 给清空购物车标签绑定单击事件
			$("#clearCart").click(function () {
				return confirm("您确定要清空购物车吗?");
			});

			// 给修改商品数量输入框绑定内容改变事件
			$(".updateCount").change(function () {
				// 获取要修改的商品的名称
				var name=$(this).parent().parent().find("td:first").text();
				// 获取商品的编号
				var id=$(this).attr("bookId");
				// 获取原来的数量
				var oldCount=this.defaultValue;
				// 获取修改后的数量
				var newCount=this.value;
				if (confirm("您确定要将【"+name+"】的数量从 "+oldCount+" 修改为 "+newCount+" 吗?")){
					// 用户确认修改商品数量
					// 发起请求到cartServlet，执行updateCount方法，并发送需要的参数
					location.href="${sessionScope.babasePath}cartServlet?action=updateCount&newCount="+newCount+"&id="+id;
				} else {
					// 用户取消修改数量
					this.value=this.defaultValue;
				}
			});
		});
	</script>
</head>
<body>
	
	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.png" style="width: 200px;height: 80px">
		<span class="wel_word">购物车</span>

		<%--使用静态包含，添加/pages/common/login_success_menu.jsp下的相同内容--%>
		<%@ include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<%--当购物车为空时，输出提示信息，并提供首页链接--%>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td colspan="5">当前购物车为空！请前往<a href="index.jsp" style="color: red;font-size: 24px">首页</a> 浏览商品吧！</td>
				</tr>
			</c:if>

			<%--当购物车非空时，输出商品项信息--%>
			<c:if test="${not empty sessionScope.cart.items}">
				<tr>
					<td>商品名称</td>
					<td>数量</td>
					<td>单价</td>
					<td>金额</td>
					<td>操作</td>
				</tr>
				<%--
					遍历输出保存在Session域中购物车中每个商品项的信息
					数据在Session域中以Map集合的键值对保存
				--%>
				<c:forEach items="${sessionScope.cart.items}" var="entry">
					<tr>
						<td>${entry.value.name}</td>
						<td>
							<input class="updateCount" style="width: 50px;color: crimson; text-align: center"
								   bookId="${entry.value.id}" type="text" value="${entry.value.count}">
						</td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<%--转发到cartServlet，去执行deleteItem方法，并发送参数商品编号--%>
						<td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>

		</table>

		<%--购物车中有商品时，输出综合信息--%>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a id="clearCart" href="cartServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>

	</div>

	<%--使用静态包含，引用相同的页脚内容--%>
	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>