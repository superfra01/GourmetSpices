<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" import="model.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <link rel="shortcut icon" href="<%= request.getContextPath() %>/images/logo.png">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/login_register.css">
    <script src="<%= request.getContextPath() %>/scripts/validation.js"></script> <!-- Link to external JS file -->
</head>
<body>
    <jsp:include page="header.jsp" />
    
    <main>
        <div class="login-container">
            <h3>Un mondo ricco di spezie e di sapori travolgenti ti aspetta</h3>
            <h4>Register</h4>
            <form id="regForm" action="<%= request.getContextPath() %>/register" method="post">
                <input type="text" name="email" placeholder="E-mail" required>
                <span id="errorEmail"></span>
                <input type="text" name="nome" placeholder="Nome" required>
                <span id="errorNome"></span>
                <input type="text" name="cognome" placeholder="Cognome" required>
                <span id="errorCognome"></span>
                <input type="text" name="username" placeholder="Username" required>
                <span id="errorUsername"></span>
                <input type="password" name="password" placeholder="Password" required>
                <span id="errorPassword"></span>
                <input type="password" name="confirm_password" placeholder="Confirm Password" required>
                <span id="errorConfirmPassword"></span>
                <button type="submit">Register</button>
            </form>
            <p>Already have an account? <a href="<%= request.getContextPath() %>/login.jsp">Login</a></p>
        </div>
    </main>

    <jsp:include page="footer.jsp" />
</body>
</html>