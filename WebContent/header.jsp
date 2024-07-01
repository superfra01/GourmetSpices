<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" import="model.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" charset="text/html; ISO-8859-1">
    <link href="${pageContext.request.contextPath}/css/Header.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="<%= request.getContextPath() %>/scripts/contatti.js"></script>
</head>
<body>
<header>
    <a href="<%= request.getContextPath() %>/LoadProdotti?mode=home"><img src="<%= request.getContextPath() %>/images/logo.webp" alt="logo" class="logo"></a>
    <nav class="main-nav">
        <ul>
            <li><a class="selected" href="<%= request.getContextPath() %>/LoadProdotti?mode=home">Home</a></li>
            <li><a href="<%= request.getContextPath() %>/LoadProdotti?mode=shop">Negozio</a></li>
            <li><a href="<%= request.getContextPath() %>/about">About</a></li>
            <li><a id="contactLink" href="javascript:void(0);">Contatti</a></li>
            <% UserBean user = (UserBean) request.getSession().getAttribute("utente");
            if (user != null) { %>
                <li><a href="<%= request.getContextPath() %>/carrello"><i class="fa fa-shopping-cart fa-lg"></i></a></li>
                <li><a href="<%= request.getContextPath() %>/user.jsp"><i class="fa fa-user fa-lg"></i></a></li>  
            <% } else { %>
                <li><a href="<%= request.getContextPath() %>/login.jsp"><i class="fa fa-user fa-lg"></i></a></li>
            <% } %>
        </ul>
    </nav>
</header>
</body>
</html>