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
			    <input type="text" id="email" name="email" placeholder="E-mail" required>
			    <span id="errorEmail" aria-live="polite"></span>
			    <input type="text" id="nome" name="nome" placeholder="Nome" required>
			    <span id="errorNome" aria-live="polite"></span>
			    <input type="text" id="cognome" name="cognome" placeholder="Cognome" required>
			    <span id="errorCognome" aria-live="polite"></span>
			    <input type="text" id="username" name="username" placeholder="Username" required>
			    <span id="errorUsername" aria-live="polite"></span>
			    <input type="password" id="password" name="password" placeholder="Password" required>
			    <span id="errorPassword" aria-live="polite"></span>
			    <input type="password" id="confirm_password" name="confirm_password" placeholder="Confirm Password" required>
			    <span id="errorConfirmPassword" aria-live="polite"></span>
			    <button type="submit">Register</button>
			</form>
            <p>Already have an account? <a href="<%= request.getContextPath() %>/login.jsp">Login</a></p>
        </div>
    </main>

    <jsp:include page="footer.jsp" />
</body>
</html>