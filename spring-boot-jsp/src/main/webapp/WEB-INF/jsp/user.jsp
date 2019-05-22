<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><html lang="en">
<body>
<h3>一行 Java 代码</h3>
<p>
	今天的日期是: <%=(new java.util.Date())%>
</p>

<h3>多行 Java 代码</h3>
<p>
	你的 IP 地址是：
<%
	out.println("Your IP address is " + request.getRemoteAddr()+"</br>");
	out.println("一段代码 ");
%>
</p>

<h3>标签 c:if</h3>
<c:if test="${username !=null}">
<p>用户名为：${username}<p>
</c:if>

<h3>标签 c:choose</h3>
<c:choose>
	<c:when test="${ salary <= 1000}">
	太惨了。
	</c:when>
	<c:when test="${salary > 10000}">
	大神呐，带我吧。
	</c:when>
	<c:when test="${10000 > salary && salary > 1000}">
	不错的薪水，还能生活。
	</c:when>
	<c:otherwise>
	什么都没有。
	</c:otherwise>
</c:choose>

<h3>For 循环实例</h3>
<%
	int count = (int)session.getAttribute("count");
	for ( int fontSize = 1; fontSize <=count; fontSize++){
 %>
          纯洁的微笑
<br />
<%}%>


<h3>布局</h3>

<%--动态包含 是在请求处理阶段执行，会在执行时检查包含内容变化--%>
<jsp:include page="footer.jsp" flush="true"/>

<%--静态包含 是在翻译阶段执行--%>
<%@include file="footer.jsp"%>

</body>
</html>
