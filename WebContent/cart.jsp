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
		<script src="<%= request.getContextPath() %>/scripts/ajax_scripts/UpdateQuantityCart.js" defer></script>
	</head>
	<body>
	    <jsp:include page="header.jsp" />
	    
	    <main>
	        <% 
	            UserBean user = (UserBean) request.getSession().getAttribute("utente");
	            if (user != null) {
	                float totalCost = 0;
	                Integer idCarrello = (Integer) request.getSession().getAttribute("idCarrello");
	                List<ContenenteCarrelloBean> cartItems = (List<ContenenteCarrelloBean>) request.getSession().getAttribute("ContenenteCarrelloBeanList"+idCarrello.toString());
	                if (cartItems != null && !cartItems.isEmpty()) {
	                    for (ContenenteCarrelloBean item : cartItems) {
	                        ProdottoBean product = (ProdottoBean) request.getSession().getAttribute("ProdottoCarrello"+item.getIdProdotto());
	                        int quantity = item.getQuantita();
	                        
	                        List<ImmagineProdottoBean> immagini = (List<ImmagineProdottoBean>) request.getSession().getAttribute("Prodottoimmagini"+Integer.toString(item.getIdProdotto()));
	                        // Display product information and quantity
	                        %>
	                        <div class="cart-item" id="cart-item-<%= item.getIdProdotto() %>">
	                            <div class="cart-item-image">
	                                <%
	                                for(ImmagineProdottoBean immagine : immagini) {
	                                %>
	                                    <img src="<%=request.getContextPath()%>/images/prodotti/<%=immagine.getImmagine()%>" alt="<%= product.getNome() %>">
	                                <%
	                                    break;
	                                }
	                                %>
	                            </div>
	                            <div class="cart-item-details">
	                                <p><%= product.getNome() %></p>
	                                <form class="update-quantity-form">
	                                    <input type="hidden" name="updateProductId" value="<%= item.getIdProdotto() %>">
	                                    <label for="quantity<%= item.getIdProdotto() %>">Quantity:</label>
	                                    <input type="number" id="quantity<%=item.getIdProdotto()%>" name="quantity" value="<%= quantity %>" min="0" max="100">
	                                    <button type="button" onclick="updateQuantity(<%= item.getIdProdotto() %>)">Aggiorna</button>
	                                </form>
	                                <button type="button" onclick="removeItem(<%= item.getIdProdotto() %>)">Rimuovi</button>
	                                <p>Price: <b><%= product.getPrezzo() %>€</b></p>
	                            </div>
	                        </div>
	                        <% 
	                        // Calculate total cost
	                        totalCost += quantity * product.getPrezzo();
	                    }
	                    %>
	                    <!-- Display total cost -->
	                    <div class="total-cost" id="total-cost">
	                        <p>Total Cost: <b><%= totalCost %></b>€</p>
	                    </div>
	                    <!-- Order button -->
	                    <div class="checkout-button">
	                        <button type="button" onclick="window.location.href='finalizza.jsp'">Proceed to checkout</button>
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
