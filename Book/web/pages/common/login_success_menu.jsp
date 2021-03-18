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
    a:active{
        color: coral;
    }
</style>
<div>
    <span>欢迎使用<span class="um_span">${sessionScope.user.username}</span>在线书城 </span>
    <a href="orderServlet?action=showMyOrders">| 我的订单 | </a>
    <a href="userServlet?action=loginOut">退出 | </a>&nbsp;&nbsp;
    <a href="index.jsp">首页</a>
</div>
