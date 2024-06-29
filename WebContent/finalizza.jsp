<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" import="model.*"%>
<!DOCTYPE html>
<html>
<head>
<title>Finalizza Pagamento</title>
<link href="<%= request.getContextPath() %>/css/finalizza.css" rel="stylesheet">
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
                               <p>Quantità: <%= quantity %></p>
                               <p>Prezzo: <b><%= product.getPrezzo() %>€</b></p>
                           </div>
                       </div>
	                   <% 
	                   // Calculate total cost
	                   totalCost += quantity * product.getPrezzo();
	               }
	               %>
	               <!-- Display total cost -->
	               <div class="total-cost">
	                   <p>Costo totale: <b><%= totalCost %></b>€</p>
	               </div>
	               <!-- Order button -->
		    	   <div class="payment-method">
		            <h2>Inserisci informazioni di pagamento</h2>
		            <form action="<%= request.getContextPath() %>/order" method="post">
		                <label for="cardNumber">Card Number:</label>
		                <input type="text" id="cardNumber" name="cardNumber" required>
		                
		                <label for="expiryDate">Expiry Date:</label>
		                <input type="date" id="expiryDate" name="expiryDate" required>
		                
		                <label for="cvv">CVV:</label>
		                <input type="text" id="cvv" name="cvv" required>
		                
		                <label for="cardHolderName">Card Holder Name:</label>
		                <input type="text" id="cardHolderName" name="cardHolderName" required>
		                
		                <label for="indirizzoSpedizione">Indirizzo di spedizione:</label>
		                <input type="text" id="indirizzoSpedizione" name="indirizzoSpedizione" required>
		                
		                <button type="submit">Effettua pagamento e concludi ordine</button>
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