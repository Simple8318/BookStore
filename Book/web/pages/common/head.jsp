<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--动态获取地址信息--%>
<%
    String basePath=request.getScheme()
            +"://"
            +request.getServerName()
            +":"
            +request.getServerPort()
            +request.getContextPath()
            +"/";
    // 将获取的动态地址放入数据域中，以便后续动态获取
    pageContext.setAttribute("basePath",basePath);
%>
<!--通过base标签，固定相对路径跳转的结果-->
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
