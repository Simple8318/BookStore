<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <%--静态包含引用base标签、css样式、jQuery文件--%>
    <%@ include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            // 给删除按钮a标签绑定单击事件，用于提示用户是否确认删除图书信息
            $("a.deleteClass").click(function () {
                // 当confirm方法返回true时(点击确认)，执行删除操作；返回false时(点击取消)不执行任何操作
                return confirm("您确认要删除【" + $(this).parent().parent().find("td:first").text() + "】图书吗?")
            });

            // 为跳转至指定页码按钮绑定单击事件
            $("#searchPageBtn").click(function () {
                // 获取用户输入的页码数
                var pageNu = $("#pn_input").val();
                // 获取最大页码数
                var pageTotal =${requestScope.page.pageTotal};
                if (pageNu < 1) {		// 当用户输入的数值小于最小页码数(1)时，默认跳转至第一页
                    pageNu = 1;
                } else if (pageNu > pageTotal) {
                    pageNu = pageTotal;
                }
                location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNu;
            });
        });
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" src="static/img/logo.png" style="width: 200px;height: 80px">
    <span class="wel_word">图书管理系统</span>
    <%--静态包含后台管理模块的相同菜单--%>
    <%@ include file="/pages/common/manager_menu.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td>作者</td>
            <td>销量</td>
            <td>库存</td>
            <td colspan="2">操作</td>
        </tr>
        <%--通过JSTL表达式获取Request域中保存的当前页每个图书的信息--%>
        <c:forEach items="${requestScope.page.items}" var="book">
            <%--遍历输出全部信息并显示在页面上--%>
            <tr>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.stock}</td>
                    <%--调用manager/bookServlet程序，并启用getBook方法，同时传递book.id参数
                    	因为book_edit.jsp同时处理图书添加和修改的操作
                    	所以在发起请求的同时，附带上要操作的方法名参数，并在隐藏域中动态获取
                    	同时发送所删除图书的所在页码，以便检查图书是否已删除
                    --%>
                <td><a href="manager/bookServlet?action=getBook&id=${book.id}&method=update&pageNo=${requestScope.page.pageNo}">修改</a></td>
                    <%--调用manager/bookServlet程序，并启用delete方法，同时传递book.id参数
                        同时发送所修改图书的所在页码，以便检查图书是否已修改
                    --%>
                <td><a class="deleteClass" href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
            </tr>
        </c:forEach>

        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <%--因为book_edit.jsp同时处理图书添加和修改的操作
                所以在发起请求的同时，附带上要操作的方法名参数，并在隐藏域中动态获取
                同时将总页码数的最后一页页码数作为参数发送，以便能直接显示添加数据所在页码
            --%>
            <td><a href="pages/manager/book_edit.jsp?method=add&pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
        </tr>
    </table>

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
<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>