<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑图书</title>
	<%--静态包含引用base标签、css样式、jQuery文件--%>
	<%@ include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
			<img class="logo_img" src="static/img/logo.png" style="width: 200px;height: 80px" >
			<span class="wel_word">编辑图书</span>
			<%--静态包含后台管理模块的相同菜单--%>
			<%@ include file="/pages/common/manager_menu.jsp"%>
		</div>
		
		<div id="main">
			<form action="manager/bookServlet" method="post">
					<%--使用隐藏域，获取图书添加按钮发送的页码最后一页页码--%>
					<input type="hidden" name="pageNo" value="${param.pageNo}">
					<%--使用隐藏域，传递调用的方法名(动态获取)--%>
					<input type="hidden" name="action" value="${param.method}"/>
					<%--同时传递需要修改的图书的id值，以便数据库更新操作--%>
					<input type="hidden" name="id" value="${requestScope.book.id}">
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>
                    <%--动态获取要修改的图书的信息，并显示在页面上--%>
					<tr>
						<td><input name="name" type="text" value="${requestScope.book.name}"/></td>
						<td><input name="price" type="text" value="${requestScope.book.price}"/></td>
						<td><input name="author" type="text" value="${requestScope.book.author}"/></td>
						<td><input name="sales" type="text" value="${requestScope.book.sales}"/></td>
						<td><input name="stock" type="text" value="${requestScope.book.stock}"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>

		<%--使用静态包含，引用相同的页脚内容--%>
		<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>