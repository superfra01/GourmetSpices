<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" import="model.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GourmetSpices</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/HomePage.css">
</head>
<body>
    <jsp:include page="header.jsp" />
    
    <section id="hero">
        <h2>Qualità Garantita</h2>
        <h3>Prezzi convenienti su ogni prodotto</h3>
        <button type="button" onclick="window.location.href='<%= request.getContextPath() %>/shop'">Acquista Ora</button>
    </section>
    
    <section id="prodotti">
        <h2>Prodotti in evidenza</h2>
    </section>
    
    <jsp:include page="footer.jsp" />
</body>
</html>