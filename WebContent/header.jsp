<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" charset="text/html; ISO-8859-1">
	<link href="${pageContext.request.contextPath}/css/Header.css" rel="stylesheet" type="text/css">
</head>
<header>
    <a href="<%= request.getContextPath() %>/HomePage.jsp"><img src="<%= request.getContextPath() %>/images/logo.webp" alt="logo" class="logo"></a>
    <nav>
        <ul>
            <li><a class="selected" href="<%= request.getContextPath() %>/pages/HomePage.jsp">Home</a></li>
            <li><a href="<%= request.getContextPath() %>/pages/shop.jsp">Shop</a></li>
            <li><a href="<%= request.getContextPath() %>/pages/about.jsp">About</a></li>
            <li><a href="<%= request.getContextPath() %>/pages/contact.jsp">Contact</a></li>
            <li><a href="<%= request.getContextPath() %>/pages/cart.jsp"><i class="fa fa-shopping-cart fa-lg"></i></a></li>
            <li><a href="<%= request.getContextPath() %>/pages/login.jsp"><i class="fa fa-user fa-lg"></i></a></li>
        </ul>
    </nav>
</header>