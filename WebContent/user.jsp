<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" import="model.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UserPage</title>
    <link rel="shortcut icon" href="<%= request.getContextPath() %>/images/logo.png">
    <link href="<%= request.getContextPath() %>/css/user.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="header.jsp" />

    <main>
        <%
            UserBean user = (UserBean) request.getSession().getAttribute("utente");
            if (user != null) {
                List<OrdineBean> ordini = (List<OrdineBean>) request.getSession().getAttribute("ordini");
        %>
        <div class="user-info">
            <h2>User Information</h2>
            <p>Username: <%= user.getUsername() %></p>
            <p>Email: <%= user.getEmail() %></p>
            <p>Nome: <%= user.getNome() %></p>
            <p>Cognome: <%= user.getCognome() %></p>
            <p>Password: ******</p>
        </div>

        <div class="purchase-history">
            <h2>Purchase History</h2>
            <table>
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>Products</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        if (ordini != null) {
                            for (OrdineBean ordine : ordini) {
                            	
                    %>
                    <tr>
                        <td><%= ordine.getData() %></td>
                        <td><%= ordine.getSpesa() %></td>
                        <td><%= ordine.getIndirizzo() %></td>  
                    </tr>
                    <% 
                    			List<ContenenteBean> contenteLista = (List<ContenenteBean>) request.getSession().getAttribute(Integer.toString(ordine.getIdOrdine()));
                    			for (ContenenteBean contente : contenteLista){
                    				int quantità = contente.getQuantita();
                    				ProdottoBean prodotto = (ProdottoBean) request.getSession().getAttribute(Integer.toString(contente.getIdProdotto()));
                    				%>
                                    <tr>
                                        <td><%= prodotto.getNome() %></td>
                                        <td><%= prodotto.getPrezzo() %></td>  
                                    </tr>
                                    <% 
                    			}
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="3">No purchase history available.</td>
                    </tr>
                    <% 
                        }
                    %>
                </tbody>
            </table>
        </div>
        <%
            } else {
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