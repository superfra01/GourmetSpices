<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" import="model.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cart</title>
    <link rel="shortcut icon" href="<%= request.getContextPath() %>/images/logo.png">
    <link href="<%= request.getContextPath() %>/css/user.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
    <jsp:include page="header.jsp" />
    
    <main>
        <% 
            UserBean user = (UserBean) request.getSession().getAttribute("utente");
            if (user != null) {
                int totalCost = 0;
                Integer idCarrello = (Integer) request.getSession().getAttribute("idCarrello");
                List<ContenenteCarrelloBean> cartItems = (List<ContenenteCarrelloBean>) request.getSession().getAttribute("ContenenteCarrelloBeanList"+idCarrello.toString());
                if (cartItems != null && !cartItems.isEmpty()) {
                    for (ContenenteCarrelloBean item : cartItems) {
                        ProdottoBean product = (ProdottoBean) request.getSession().getAttribute("ProdottoCarrello"+item.getIdProdotto());
                        int quantity = item.getQuantita();
                        
                        List<ImmagineProdottoBean> immagini = (List<ImmagineProdottoBean>) request.getSession().getAttribute("Prodottoimmagini"+Integer.toString(item.getIdProdotto()));
                        // Display product information and quantity
                        %>
                        <div class="cart-item">
                            <p><%= product.getNome() %></p>
                            <form action="<%= request.getContextPath() %>/UpdateQuantity" method="post">
                                <input type="hidden" name="productId" value="<%= item.getIdProdotto() %>">
                                <label for="quantity<%= item.getIdProdotto() %>">Quantity:</label>
                                <input type="text" id="quantity<%=item.getIdProdotto()%>" name="quantity" value="<%= quantity %>">
                                <button type="submit">Update</button>
                            </form>
                            <p>Price: <b><%= product.getPrezzo() %>€</b></p>
                            <%
                            for(ImmagineProdottoBean immagine : immagini) {                           	
                            %>
                            	<img src="<%=request.getContextPath()%>/images/prodotti/<%=immagine.getImmagine()%>">
                            <%	
                            	break;
                            }
                            %>
                        </div>
                        <% 
                        // Calculate total cost
                        totalCost += quantity * product.getPrezzo();
                    }
                    %>
                    <!-- Display total cost -->
                    <div class="total-cost">
                        <p>Total Cost: <b><%= totalCost %></b>€</p>
                    </div>
                    <!-- Order button -->
                    <div class="checkout-button">
                		<form action="<%= request.getContextPath() %>/order" method="get">
                        	<button type="submit">Proceed to checkout</button>
                    	</form>
                	</div>
                	<div class="empty-button">
                		<form action="<%= request.getContextPath() %>/SvuotaCarrello" method="get">
                        	<button type="submit">Empty cart</button>
                    	</form>
                	</div>
                	<%
                } else {
                    // Handle empty cart scenario
                    %>
                    <div class="error-message">
                    	<%=idCarrello%>
                        <p>Your cart is empty.</p>
                    </div>
                    <% 
                }
            } else {
                // Handle not logged in scenario
                %>
                <div class="error-message">
                    <p>You must be logged in to view this page.</p>
                </div>
                <% 
            }
        %>
    </main>

    <jsp:include page="footer.jsp" />
</body>
</html>