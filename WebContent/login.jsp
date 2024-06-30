<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" import="model.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="shortcut icon" href="<%= request.getContextPath() %>/images/logo.png">
    <link href="<%= request.getContextPath() %>/css/login_register.css" rel="stylesheet">
    <script src="<%= request.getContextPath() %>/scripts/loginValidation.js"></script>
</head>
<body>
    <jsp:include page="header.jsp" />
    
    <main>
        <div class="login-container">
            <h3>Un mondo ricco di spezie e di sapori travolgenti ti aspetta</h3>
            <h4>Login</h4>

            <form id="loginForm" action="<%= request.getContextPath() %>/login" method="post" novalidate>
                <input type="text" id="email" name="email" placeholder="Email" required>
                <span id="errorEmail" aria-live="polite"></span>
                <input type="password" id="password" name="password" placeholder="Password" required>
                <span id="errorPassword" aria-live="polite"></span>
                <button type="submit">Login</button>
            </form>
            
            <% 
                List<String> errors = (List<String>) request.getAttribute("errors");
                if (errors != null && !errors.isEmpty()) {
            %>
                <div class="error-messages">
                    <ul>
                        <% for (String error : errors) { %>
                            <li><%= error %></li>
                        <% } %>
                    </ul>
                </div>
            <% } %>
            
            <p>Non ti sei ancora registrato? <a href="<%= request.getContextPath() %>/register.jsp" class="register-now">Registrati ora!</a></p>
        </div>
    </main>

    <jsp:include page="footer.jsp" />
</body>
</html>