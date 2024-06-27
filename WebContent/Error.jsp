<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" import="model.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error - GourmetSpices</title>
    <link rel="shortcut icon" href="logo.png">
    <link rel="stylesheet" href="./css/HomePage.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
    <jsp:include page="header.jsp" />
    <section id="error">
        <h1>Oops!</h1>
        <h2>Something Went Wrong</h2>
        <p>We're sorry, but an unexpected error has occurred. Please try again later or go back to the homepage.</p>
        <button onclick="window.location.href='index.html'">Go to Homepage</button>
    </section>
    <jsp:include page="footer.jsp" />
</body>
</html>
