<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" import="model.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GourmetSpices</title>
    <link rel="shortcut icon" href="<%= request.getContextPath() %>/images/logo.png">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/HomePage.css">
</head>
<body>
    <jsp:include page="header.jsp" />
    
    <section id="hero">
        <h4>Quality Guaranteed</h4>
        <h2>Super Value Deals</h2>
        <h1>On all products</h1>
        <button type="button" onclick="window.location.href='shop.jsp'">Shop Now</button>
    </section>
    
    <section id="product1" class="section-p1">
        <h2>Featured Products</h2>
        <div class="pro-container">
            <div class="pro">
                <img src="" alt="Product 1">
                <div class="des">
                    <span>Gourmet Spices</span>
                    <h5>Exotic Spice Blend</h5>
                </div>
            </div>
        </div>
    </section>
    
    <jsp:include page="footer.jsp" />
</body>
</html>