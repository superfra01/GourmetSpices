<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, model.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dettagli Prodotto</title>
    <link rel="stylesheet" href="./css/specificaProdotto.css">
    <script src="<%=request.getContextPath()%>/scripts/ajax_scripts/AddToCart.js" defer></script>
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="product-details-container">
        <%
            int productId = Integer.parseInt(request.getParameter("id"));
            ProdottoBean prodotto = (ProdottoBean) request.getSession().getAttribute("ProdottoRichiesto");
            List<ImmagineProdottoBean> immagini = (List<ImmagineProdottoBean>) request.getSession().getAttribute("ProdottoRichiestoImmagini");
            if(prodotto!=null){
        %>
        <div class="product-image">
            <% for (ImmagineProdottoBean immagine : immagini) { %>
                <img src="<%=request.getContextPath()%>/images/prodotti/<%=immagine.getImmagine()%>" alt="<%= prodotto.getNome() %>">
            <% break; } %>
        </div>
        <div class="product-info">
       <%
       UserBean user = (UserBean) request.getSession().getAttribute("utente");
       if(user!=null && user.getTipoUtente().equals("ADMIN")){
       %> 
            <h1><%= prodotto.getNome() %> ID: <%= prodotto.getIdProdotto() %> </h1>
        <%
        }
        else{%>
        <h1><%= prodotto.getNome() %></h1>
        <%}%>
            <p><%= prodotto.getDescrizione() %></p>
            <span class="price">€ <%= prodotto.getPrezzo() %></span>
            <%
            if(user != null && prodotto.getValidoProdotto()==1){ %>
                <form onsubmit=" event.preventDefault()">
                    <input type="hidden" name="AddProdottoId" value="<%= prodotto.getIdProdotto() %>">
                    <label for="quantity">Quantità:</label>
                    <input type="number" id="quantity" name="quantity" value="1" min="1" max="100">
                    <button type="submit" onclick="AddToCart(<%= prodotto.getIdProdotto() %>)">Aggiungi al carrello</button>
                </form>
            <% }
            else if(user != null && prodotto.getValidoProdotto()==0){%>

                    <h2><%= prodotto.getNome()%> Non disponibile</h2>
                
            <%
            }
            else if(user==null && prodotto.getValidoProdotto()==0){%>
            <h2><%= prodotto.getNome()%> Non disponibile</h2>
            <%}
            else if(user==null && prodotto.getValidoProdotto()==1){%>
            <h2>Devi effettuare il login per poter aggiungere prodotti al carrello</h2>
            <%}%>      
        </div>
      <%
            }
        %> 
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>