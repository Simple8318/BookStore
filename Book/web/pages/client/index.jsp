<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
	<%--静态包含引用base标签、css样式、jQuery文件--%>
	<%@ include file="/pages/common/head.jsp"%>
	<script type="text/javascript">

		$(function () {
			// 跳到指定的页码
			$("#searchPageBtn").click(function () {

				var pageNo = $("#pn_input").val();

				<%--var pageTotal = ${requestScope.page.pageTotal};--%>
				<%--alert(pageTotal);--%>

				// javaScript语言中提供了一个location地址栏对象
				// 它有一个属性叫href.它可以获取浏览器地址栏中的地址
				// href属性可读，可写
				location.href = "${pageScope.basePath}${ requestScope.page.url }&pageNo=" + pageNo;

			});

			// 给加入购物车按钮绑定单击事件
			$("button.addToCart").click(function () {
				// 获取要添加至购物车的id
				var bookId=$(this).attr("bookId");
				// 将请求地址修改为需要的Servlet程序地址
				location.href="${sessionScope.babasePath}cartServlet?action=addItem&id="+bookId;
			});
		});
	</script>
</head>
<body>
	
	<div id="header">
		<img class="logo_img" src="static/img/logo.png" style="width: 200px;height: 80px">
		<span class="wel_word">网上书城</span>

		<div>
			<%--如果用户还未登录，则显示登录和注册菜单--%>
			<c:if test="${empty sessionScope.user}">
				<a href="pages/user/login.jsp">登录 | </a>
				<a href="pages/user/regist.jsp">注册 | </a>
			</c:if>
			<%--如果用户已经登录，则显示用户登录信息--%>
			<c:if test="${not empty sessionScope.user}">
				<span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临在线书城</span>
				<a href="orderServlet?action=showMyOrders">我的订单 | </a>
				<a href="userServlet?action=loginOut">退出 | </a>&nbsp;
			</c:if>
				<a href="pages/cart/cart.jsp">购物车 | </a>
				<a href="pages/manager/manager.jsp">后台管理</a>
		</div>
	</div>
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="client/bookServlet" method="get">
					<%--使用隐藏域，将调用方法名作为参数发送到Servlet程序--%>
					<input type="hidden" name="action" value="pageByPrice">
					价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
						<input id="max" type="text" name="max" value="${param.max}"> 元
						<input type="submit" value="查询" />
				</form>
			</div>
			<div style="text-align: center">
				<%--当购物车为空时--%>
				<c:if test="${empty sessionScope.cart.items}">
					<span style="color: red;font-size: 20px">当前购物车为空！</span>
					<div>
						<span>将您喜欢的商品加入购物车吧！</span>
					</div>
				</c:if>
				<%--当购物车不为空时--%>
				<c:if test="${not empty sessionScope.cart.items}">
					<span>您的购物车中有<span style="color: red;font-size: 20px">${sessionScope.cart.totalCount}</span>件商品</span>
					<div>
						您刚刚将<span style="color: red">${sessionScope.lastName}</span>加入到了购物车中
					</div>
				</c:if>
			</div>
			<%--遍历输出ClientBookServlet程序查询到的每个图书的信息--%>
			<c:forEach items="${requestScope.page.items}" var="book">
				<div class="b_list">
					<div class="img_div">
						<img class="book_img" alt="" src="${book.imgPath}" />
					</div>
					<div class="book_info">
						<div class="book_name">
							<span class="sp1">书名:</span>
							<span class="sp2">${book.name}</span>
						</div>
						<div class="book_author">
							<span class="sp1">作者:</span>
							<span class="sp2">${book.author}</span>
						</div>
						<div class="book_price">
							<span class="sp1">价格:</span>
							<span class="sp2">￥${book.price}</span>
						</div>
						<div class="book_sales">
							<span class="sp1">销量:</span>
							<span class="sp2">${book.sales}</span>
						</div>
						<div class="book_amount">
							<span class="sp1">库存:</span>
							<span class="sp2">${book.stock}</span>
						</div>
						<div class="book_add">
							<button class="addToCart" bookId="${book.id}">加入购物车</button>
						</div>
					</div>
				</div>
			</c:forEach>

		</div>

		<%--分页--%>
		<div id="page_nav">
			<%--当前页不为首页时，才显示首页、上一页标签--%>
			<c:if test="${requestScope.page.pageNo>1}">
				<a href="${requestScope.page.url}&pageNo=1">首页</a>
				<a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
			</c:if>
			<%--页码输出的开始--%>
			<c:choose>
				<%--1、总页码数<=5时，页码的范围是：1-总页码--%>
				<c:when test="${requestScope.page.pageTotal<=5}">
					<c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
						<%--如果i为当前页的页码，则重点显示--%>
						<c:if test="${i== requestScope.page.pageNo}">
							【${i}】
						</c:if>
						<%--如果i不为当前页的页码，则链接显示--%>
						<c:if test="${i!= requestScope.page.pageNo}">
							<a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
						</c:if>
					</c:forEach>
				</c:when>
				<%--2、当总页码数>5时--%>
				<c:when test="${requestScope.page.pageTotal>5}">
					<c:choose>
						<%--2.1、当前页码为前3页时，页码范围是：1-5--%>
						<c:when test="${requestScope.page.pageNo<=3}">
							<c:forEach begin="1" end="5" var="i">
								<%--如果i为当前页的页码，则重点显示--%>
								<c:if test="${i== requestScope.page.pageNo}">
									【${i}】
								</c:if>
								<%--如果i不为当前页的页码，则链接显示--%>
								<c:if test="${i!= requestScope.page.pageNo}">
									<a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
								</c:if>
							</c:forEach>
						</c:when>
						<%--2.2、当前页码为后3页时，页码范围是：总页码数-4 - 总页码数--%>
						<c:when test="${requestScope.page.pageNo> requestScope.page.pageNo-3}">
							<c:forEach begin="${requestScope.page.pageTotal-4}" end="${requestScope.page.pageTotal}" var="i">
								<%--如果i为当前页的页码，则重点显示--%>
								<c:if test="${i== requestScope.page.pageNo}">
									【${i}】
								</c:if>
								<%--如果i不为当前页的页码，则链接显示--%>
								<c:if test="${i!= requestScope.page.pageNo}">
									<a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
								</c:if>
							</c:forEach>
						</c:when>

						<%--2.3、当前页码不为全部页码的前3页和后3页时，页码的输出范围是：当前页码-2--当前页码+2--%>
						<c:otherwise>
							<c:forEach begin="${requestScope.page.pageNo-2}" end="${requestScope.page.pageNo+2}" var="i">
								<%--如果i为当前页的页码，则重点显示--%>
								<c:if test="${i== requestScope.page.pageNo}">
									【${i}】
								</c:if>
								<%--如果i不为当前页的页码，则链接显示--%>
								<c:if test="${i!= requestScope.page.pageNo}">
									<a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
								</c:if>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</c:when>
			</c:choose>
			<%--页码输出的结束--%>
			<%--如果当前页为末页，则不显示下一页、末页标签--%>
			<c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
				<a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
				<a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
			</c:if>
			共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
			<%--在输入框中显示当前页码--%>
			到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页
			<input id="searchPageBtn" type="button" value="确定">
		</div>
	
	</div>

	<%--使用静态包含，引用相同的页脚内容--%>
	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>