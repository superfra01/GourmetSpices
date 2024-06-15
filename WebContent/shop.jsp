<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" import="model.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shop</title>
    <link rel="stylesheet" href="./css/Shop.css">
</head>
<body>
    <jsp:include page="header.jsp" />
    
    <div class="shop-container">
        <h1>Benvenuto nel nostro Shop</h1>
        <div class="product-list">
            <div class="product-item">
                <img src="path/to/product1.jpg" alt="Product 1">
                <h2>Prodotto 1</h2>
                <p>Descrizione del prodotto 1.</p>
                <span>€19.99</span>
                <button>Aggiungi al carrello</button>
            </div>
            <div class="product-item">
                <img src="path/to/product2.jpg" alt="Product 2">
                <h2>Prodotto 2</h2>
                <p>Descrizione del prodotto 2.</p>
                <span>€29.99</span>
                <button>Aggiungi al carrello</button>
            </div>
            <!-- Aggiungere altri prodotti secondo necessità -->
        </div>
    </div>
    
    <jsp:include page="footer.jsp" />
</body>
</html>