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
            <%if (user.getTipoUtente().equals("ADMIN")){%>
            	<button type="button" onclick="window.location.href='admin.jsp'">Admin Page</button>
            <%}%>
        </div>
        
        <div class="logout-button">
            <form action="<%= request.getContextPath() %>/logout" method="post">
                <button type="submit">Logout</button>
            </form>
        </div>

        <div class="purchase-history">
            <h2>Purchase History</h2>
            <div class="orders">
                <% 
                    if (ordini != null) {
                        for (OrdineBean ordine : ordini) {
                %>
                <div class="order">
                    <div class="order-info">
                        <p><strong>Date:</strong> <%= ordine.getData() %></p>
                        <p><strong>Spesa:</strong> <%= ordine.getSpesa() %></p>
                        <p><strong>Indirizzo:</strong> <%= ordine.getIndirizzo() %></p>
                    </div>
                    <div class="order-products">
                        <h3>Products:</h3>
                        <ul>
                            <% 
                                List<ContenenteBean> contenteLista = (List<ContenenteBean>) request.getSession().getAttribute("contenente" + ordine.getIdOrdine());
                                for (ContenenteBean contente : contenteLista) {
                                    int quantità = contente.getQuantita();
                                    ProdottoBean prodotto = (ProdottoBean) request.getSession().getAttribute("prodotto" + contente.getIdProdotto());
                            %>
                            <li>
                                <p><strong>Nome:</strong> <%= prodotto.getNome() %></p>
                                <p><strong>Prezzo:</strong> <%= prodotto.getPrezzo() %></p>
                                <p><strong>Quantità:</strong> <%= quantità %></p>
                            </li>
                            <% 
                                }
                            %>
                        </ul>
                    </div>
                </div>
                <% 
                        }
                    } else {
                %>
                <div class="no-orders">
                    <p>No purchase history available.</p>
                </div>
                <% 
                    }
                %>
            </div>
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