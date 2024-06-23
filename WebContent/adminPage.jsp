<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Page</title>
<link href="<%= request.getContextPath() %>/css/admin.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp" />
	
    <h1>Admin Page</h1>
    
    <!-- Form per inserire un nuovo elemento nel catalogo -->
    <div class="form-container">
        <h2>Inserisci Nuovo Elemento</h2>
        <form action="inserisciElemento" method="post">
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" required><br>
            <label for="descrizione">Descrizione:</label>
            <input type="text" id="descrizione" name="descrizione" required><br>
            <label for="prezzo">Prezzo:</label>
            <input type="number" id="prezzo" name="prezzo" step="0.01" required><br>
            <input type="submit" value="Inserisci">
        </form>
    </div>

    <!-- Form per modificare un elemento esistente nel catalogo -->
    <div class="form-container">
        <h2>Modifica Elemento</h2>
        <form action="modificaElemento" method="post">
            <label for="id_modifica">ID Elemento:</label>
            <input type="text" id="id_modifica" name="id_modifica" required><br>
            <label for="nome_modifica">Nome:</label>
            <input type="text" id="nome_modifica" name="nome_modifica"><br>
            <label for="descrizione_modifica">Descrizione:</label>
            <input type="text" id="descrizione_modifica" name="descrizione_modifica"><br>
            <label for="prezzo_modifica">Prezzo:</label>
            <input type="number" id="prezzo_modifica" name="prezzo_modifica" step="0.01"><br>
            <input type="submit" value="Modifica">
        </form>
    </div>

    <!-- Form per visualizzare un elemento del catalogo -->
    <div class="form-container">
        <h2>Visualizza Elemento</h2>
        <form action="/ProdottoSpecificato" method="get">
            <label for="id_visualizza">ID Elemento:</label>
            <input type="text" id="id_visualizza" name="id" required><br>
            <input type="submit" value="Visualizza">
        </form>
    </div>

    <!-- Form per cancellare un elemento del catalogo -->
    <div class="form-container">
        <h2>Cancella Elemento</h2>
        <form action="cancellaElemento" method="post">
            <label for="id_cancella">ID Elemento:</label>
            <input type="text" id="id_cancella" name="id_cancella" required><br>
            <input type="submit" value="Cancella">
        </form>
    </div>
    <jsp:include page="footer.jsp" />
</body>
</html>