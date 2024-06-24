<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Page</title>
<link href="<%= request.getContextPath() %>/css/admin.css" rel="stylesheet">
<script>
function filterOrders() {
    let filterDate = document.getElementById("filterDate").value;
    let filterEmail = document.getElementById("filterEmail").value.toLowerCase();
    
    let orders = document.getElementsByClassName("orderRow");
    for (let i = 0; i < orders.length; i++) {
        let orderDate = orders[i].getAttribute("data-order-date");
        let orderEmail = orders[i].getAttribute("data-order-email").toLowerCase();
        
        let dateMatch = !filterDate || orderDate === filterDate;
        let emailMatch = !filterEmail || orderEmail.includes(filterEmail);
        
        if (dateMatch && emailMatch) {
            orders[i].style.display = "";
        } else {
            orders[i].style.display = "none";
        }
    }
}
</script>
</head>
<body>
    <jsp:include page="header.jsp" />
    
    <h1>Admin Page</h1>
    
    <% 
    UserBean user = (UserBean) request.getSession().getAttribute("utente");
    if(user != null && user.getTipoUtente().equals("ADMIN")) {
        List<OrdineBean> adminOrdini = (List<OrdineBean>) request.getSession().getAttribute("adminOrdini");
    %>
    
    <div class="filter-section">
        <h2>Filter Orders</h2>
        <form onsubmit="event.preventDefault(); filterOrders();">
            <label for="filterDate">Order Date:</label>
            <input type="date" id="filterDate" name="filterDate">
            <label for="filterEmail">User Email:</label>
            <input type="text" id="filterEmail" name="filterEmail" placeholder="Enter user email">
            <button type="submit">Filter</button>
        </form>
    </div>
    
    <div class="orders-section">
        <h2>All Orders</h2>
        <table>
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Order Date</th>
                    <th>User Email</th>
                    <th>Product</th>
                    <th>Payment Method</th>
                </tr>
            </thead>
            <tbody>
                <% 
                for (OrdineBean ordine : adminOrdini) {
                    int idOrdine = ordine.getIdOrdine();
                    String orderDate = ordine.getData().toString();
                    String userEmail = ordine.getEmail(); // Assuming there's a method to get user's email
                    List<ContenenteBean> contenenteList = (List<ContenenteBean>) request.getSession().getAttribute("adminContenenteList"+idOrdine);
                    if(contenenteList==null)
                    System.out.println("dio stegosauro");
                %>
                <tr class="orderRow" data-order-date="<%= orderDate %>" data-order-email="<%= userEmail %>">
                    <td><%= idOrdine %></td>
                    <td><%= orderDate %></td>
                    <td><%= userEmail %></td>
                    <td>
                        <ul>
                            <% 
                            for (ContenenteBean contenente : contenenteList) {
                                if (contenente.getIdOrdine() == idOrdine) {
                                    ProdottoBean prodotto = (ProdottoBean) request.getSession().getAttribute("adminProdotto" + contenente.getIdProdotto());
                                    if (prodotto != null) {
                            %>
                            <li><%= prodotto.getNome() %></li>
                            <% 
                                    }
                                }
                            }
                            %>
                        </ul>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
    
    <% } else { %>
        <div class="error-message">
            <p>You must be an admin in order to view this page.</p>
        </div>
    <% } %>
    
    <jsp:include page="footer.jsp" />
</body>
</html>