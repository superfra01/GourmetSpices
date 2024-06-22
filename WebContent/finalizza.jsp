<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" import="model.*"%>
<!DOCTYPE html>
<html>
<head>
<title>Finalizza Pagamento</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<main>
		   <% 
	       UserBean user = (UserBean) request.getSession().getAttribute("utente");
	       if (user != null) {%>
		    	   <div class="payment-method">
		            <h2>Insert Payment Method</h2>
		            <form action="<%= request.getContextPath() %>/addPaymentMethod" method="post">
		                <label for="cardNumber">Card Number:</label>
		                <input type="text" id="cardNumber" name="cardNumber" required>
		                
		                <label for="expiryDate">Expiry Date:</label>
		                <input type="text" id="expiryDate" name="expiryDate" required>
		                
		                <label for="cvv">CVV:</label>
		                <input type="text" id="cvv" name="cvv" required>
		                
		                <label for="cardHolderName">Card Holder Name:</label>
		                <input type="text" id="cardHolderName" name="cardHolderName" required>
		                
		                <button type="submit">Add Payment Method</button>
		             </form>
		        	</div>
		       <%
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
	                           <p>Price: <b><%= product.getPrezzo() %>€</b></p>
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
	               <div class="order-button">
	                   <form action="<%= request.getContextPath() %>/order" method="get">
	                       <button type="submit">Order products</button>
	                   </form>
	               </div>
	               <%
	       } else {
	           // Handle not logged in scenario
	           %>
	           <div class="error-message">
	               <p>You must be logged in to view this page.</p>
	           </div>
	           <%
	       		}
	       }
	   %>
	</main>
	<jsp:include page="footer.jsp" />
</body>
</html>