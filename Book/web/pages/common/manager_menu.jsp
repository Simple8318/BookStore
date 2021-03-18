<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">
    a:link{
        text-decoration: none;
    }
    a:visited{
        color: lightsteelblue;
    }
    a:hover{
        color: red;
        font-size: 130%;
    }
</style>
<div>
    <%--直接将Servlet的请求方法作为参数传递--%>
    <a href="manager/bookServlet?action=page">| 图书管理 | </a>
    <a href="orderServlet?action=showAllOrders">订单管理 | </a>
    <a href="index.jsp">返回首页</a>
</div>
