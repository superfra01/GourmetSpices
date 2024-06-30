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
        <div class="product-list">
            <%
                List<ProdottoBean> prodottiList = (List<ProdottoBean>) request.getSession().getAttribute("ProdottiList");
                if (prodottiList != null) {
                    for (ProdottoBean prodotto : prodottiList) {
                        List<ImmagineProdottoBean> immagini = (List<ImmagineProdottoBean>) request.getSession().getAttribute("ImmagineProdotto" + Integer.toString(prodotto.getIdProdotto()));
                        if(prodotto.getValidoProdotto()==1 && prodotto.getInEvidenza()==1){
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
    </section>
    
    <jsp:include page="footer.jsp" />
</body>
</html>