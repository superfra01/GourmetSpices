<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" import="model.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error - GourmetSpices</title>
    <link rel="shortcut icon" href="logo.png">
    <link rel="stylesheet" href="./css/error.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
    <jsp:include page="header.jsp" />
    <section id="error">
        <h1>Oops!</h1>
        <h2>Qualcosa è andato storto</h2>
        <p>Ci scusiamo per il disagio, un errore inaspettato ha impedito il corretto funzionamento del sito. Perfavore riprova più tardi, nel frattempo prova a tornare alla Homepage.</p>
        <button onclick="window.location.href='HomePage.jsp'">Ritorna alla Homepage</button>
    </section>
    <jsp:include page="footer.jsp" />
</body>
</html>
