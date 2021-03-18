<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>500错误</title>
    <%--静态包含引用base标签、css样式、jQuery文件--%>
    <%@ include file="/pages/common/head.jsp"%>
    <style>
        body{
            margin: 50px auto;      /*设置外边距*/
            text-align: center;     /*设置文字居中*/
        }
    </style>
</head>
<body>
<span style="color: red;font-size: 40px">网站正在建设中...</span>
<br/>
<span style="font-size: 25px;">请点击<a href="index.jsp" style="color: lawngreen;font-size: 40px" >首页</a>进入商城吧！</span>
<br/>
<br/>
<img src="static\errorImages\500.png" style="width: 580px;height: 280px" alt="网站正在建设中...">
</body>
</html>
