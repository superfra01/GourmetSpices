<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, model.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dettagli Prodotto</title>
    <link rel="stylesheet" href="./css/specificaProdotto.css">
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="product-details-container">
        <%
            int productId = Integer.parseInt(request.getParameter("id"));
            List<ProdottoBean> prodottiList = (List<ProdottoBean>) request.getSession().getAttribute("ProdottiList");
            ProdottoBean prodotto = null;
            if (prodottiList != null) {
                for (ProdottoBean p : prodottiList) {
                    if (p.getIdProdotto() == productId) {
                        prodotto = p;
                        break;
                    }
                }
            }
            List<ImmagineProdottoBean> immagini = (List<ImmagineProdottoBean>) request.getSession().getAttribute("ImmagineProdotto" + Integer.toString(productId));
        %>
        <div class="product-image">
            <% for (ImmagineProdottoBean immagine : immagini) { %>
                <img src="<%=request.getContextPath()%>/images/prodotti/<%=immagine.getImmagine()%>" alt="<%= prodotto.getNome() %>">
            <% break; } %>
        </div>
        <div class="product-info">
            <h1><%= prodotto.getNome() %></h1>
            <p><%= prodotto.getDescrizione() %></p>
            <span class="price">€ <%= prodotto.getPrezzo() %></span>
            <% UserBean user = (UserBean) request.getSession().getAttribute("utente");
            if(user != null){ %>
                <form action="<%=request.getContextPath()%>/carrello" method="post">
                    <input type="hidden" name="AddProdottoId" value="<%= prodotto.getIdProdotto() %>">
                    <label for="quantity">Quantità:</label>
                    <input type="number" id="quantity" name="quantity" value="1" min="1" max="100">
                    <button type="submit">Aggiungi al carrello</button>
                </form>
            <% } %>
        </div>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>