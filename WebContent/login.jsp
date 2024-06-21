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

    <style>
        .error-messages {
            color: red;
            font-weight: bold;
        }
        .error-messages ul {
            list-style-type: none;
            padding: 0;
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp" />
    
    <main>
        <div class="login-container">
            <h3>Un mondo ricco di spezie e di sapori travolgenti ti aspetta</h3>
            <h4>Login</h4>

            <form id="loginForm" action="<%= request.getContextPath() %>/login" method="post">
                <input type="text" name="email" placeholder="Email" required>
                <span id="errorEmail"></span>
                <input type="password" name="password" placeholder="Password" required>
                <span id="errorPassword"></span>
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
            
            <p>Don't have an account? <a href="<%= request.getContextPath() %>/register.jsp">Register</a></p>
        </div>
    </main>

    <jsp:include page="footer.jsp" />
</body>
</html>