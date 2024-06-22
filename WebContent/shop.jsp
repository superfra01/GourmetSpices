<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, model.*" %>
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
            <%
                List<ProdottoBean> prodottiList = (List<ProdottoBean>) request.getSession().getAttribute("ProdottiList");
                if (prodottiList != null) {
                    for (ProdottoBean prodotto : prodottiList) {
                    	List<ImmagineProdottoBean> immagini = (List<ImmagineProdottoBean>) request.getSession().getAttribute("ImmagineProdotto"+Integer.toString(prodotto.getIdProdotto()));
            %>
                        <div class="product-item">
                            <% for (ImmagineProdottoBean immagine : immagini) { %>
                                <img src="<%=request.getContextPath()%>/images/prodotti/<%=immagine.getImmagine()%>" alt="<%= prodotto.getNome() %>">
                            <% 
                            	break;
                            	} %>
                            <h2><%= prodotto.getNome() %></h2>
                            <p><%= prodotto.getDescrizione() %></p>
                            <span>€ <%= prodotto.getPrezzo() %></span>
                            <% UserBean user = (UserBean) request.getSession().getAttribute("utente");
            				if(user!=null){%>
	                            <form action="<%=request.getContextPath()%>/carrelloaggiungi" method="post">
	                                <input type="hidden" name="AddProdottoId" value="<%= prodotto.getIdProdotto() %>">
	                                <button type="submit">Aggiungi al carrello</button>
	                            </form>
                            <%}%>
                        </div>
            <%
                    }
                }
            %>
        </div>
    </div>
    
    <jsp:include page="footer.jsp" />
</body>
</html>