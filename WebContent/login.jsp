<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" import="model.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="shortcut icon" href="<%= request.getContextPath() %>/images/logo.png">
    <link href="<%= request.getContextPath() %>/css/login_register.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="header.jsp" />
    
    <main>
        <div class="login-container">
            <h3>Un mondo ricco di spezie e di sapori travolgenti ti aspetta</h3>
            <h4>Login</h4>
            <form action="<%= request.getContextPath() %>/login" method="post">
                <input type="text" name="email" placeholder="email" required>
                <input type="password" name="password" placeholder="Password" required>
                <button type="submit">Login</button>
            </form>
            <p>Don't have an account? <a href="<%= request.getContextPath() %>/register.jsp">Register</a></p>
        </div>
    </main>

    <jsp:include page="footer.jsp" />
</body>
</html>