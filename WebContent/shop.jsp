<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, model.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shop</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/shop.css">
    <script src="<%=request.getContextPath()%>/scripts/shop.js" defer></script>
    <script src="<%=request.getContextPath()%>/scripts/ajax_scripts/AddToCart.js" defer></script>
</head>
<body>
    <jsp:include page="header.jsp" />
    
    <div class="shop-container">
        <h1>Benvenuto nel nostro Negozio</h1>

        <div class="product-list">
            <%
                List<ProdottoBean> prodottiList = (List<ProdottoBean>) request.getSession().getAttribute("ProdottiList");
                if (prodottiList != null) {
                    for (ProdottoBean prodotto : prodottiList) {
                        List<ImmagineProdottoBean> immagini = (List<ImmagineProdottoBean>) request.getSession().getAttribute("ImmagineProdotto" + Integer.toString(prodotto.getIdProdotto()));
                        if(prodotto.getValidoProdotto()==1){
            %>
                        <div class="product-item" onclick="window.location.href='<%=request.getContextPath()%>/ProdottoSpecificato?id=<%= prodotto.getIdProdotto() %>'">
                            <% for (ImmagineProdottoBean immagine : immagini) { %>
                                <img src="<%=request.getContextPath()%>/images/prodotti/<%=immagine.getImmagine()%>" alt="<%= prodotto.getNome() %>">
                            <% 
                                break;
                                } 
                            %>
                            <div class="product-item-name">
                                <h2>
                                    <a href="<%=request.getContextPath()%>/ProdottoSpecificato?id=<%= prodotto.getIdProdotto() %>">
                                        <%= prodotto.getNome() %>
                                    </a>
                                </h2>
                            </div>
                            <p><%= prodotto.getDescrizione() %></p>
                            <span>€ <%= prodotto.getPrezzo() %></span>
                            <% UserBean user = (UserBean) request.getSession().getAttribute("utente");
                            if(user!=null){%>
                                <form onsubmit=" event.preventDefault()">
                                    <input type="hidden" name="AddProdottoId" value="<%= prodotto.getIdProdotto() %>">
                                    <button type="submit" onclick="AddToCart(<%= prodotto.getIdProdotto() %>)">Aggiungi al carrello</button>
                                </form>
                            <%}%>
                        </div>
            <%}
                    }
                }
            %>
        </div>
    </div>
    
    <jsp:include page="footer.jsp" />
</body>
</html>