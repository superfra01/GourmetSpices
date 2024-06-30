<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Page</title>
<link href="<%= request.getContextPath() %>/css/admin.css" rel="stylesheet">
<script src="<%= request.getContextPath() %>/scripts/adminValidation.js"></script>
</head>
<body>
    <jsp:include page="header.jsp" />
    
    <h1>Admin Page</h1>
    
    <% 
    UserBean user = (UserBean) request.getSession().getAttribute("utente");
    if(user != null && user.getTipoUtente().equals("ADMIN")) { %>
    
        <!-- Form per inserire un nuovo elemento nel catalogo -->
        <div class="form-container">
            <h2>Inserisci Nuovo Prodotto nel Negozio</h2>
            <form id="inserisciElemento" action="<%= request.getContextPath() %>/inserisciElemento" method="post" enctype="multipart/form-data" onsubmit="return validateInserisciElemento()">
                <label for="nome">Nome:</label>
                <input type="text" id="nomeprodotto" name="nomeprodotto" required>
                <span id="errorNomeProdotto" class="error-message"></span><br>
                <label for="descrizione">Descrizione:</label>
                <textarea id="descrizione" name="descrizione" rows="4" cols="50" required></textarea>
                <span id="errorDescrizione" class="error-message"></span><br>
                <label for="prezzo">Prezzo:</label>
                <input type="number" id="prezzo" name="prezzo" required>
                <span id="errorPrezzo" class="error-message"></span><br>
                <label for="immagine">Immagine:</label>
                <input type="file" id="immagine" name="immagine" accept="image/jpeg, image/png" required>
                <span id="errorImmagine" class="error-message"></span><br>
                <input type="submit" value="Inserisci">
            </form>
        </div>
        <!-- Form per modificare un elemento esistente nel catalogo -->
        <div class="form-container">
            <h2>Modifica Informazioni Prodotto</h2>
            <form id="modificaProdotto" action="<%= request.getContextPath() %>/ModificaProdotto" method="post" onsubmit="return validateModificaProdotto()">
                <label for="id_modifica">ID Elemento:</label>
                <input type="text" id="id_modifica" name="id" required>
                <span id="errorIdModifica" class="error-message"></span><br>
                <label for="nome_modifica">Nome:</label>
                <input type="text" id="nome_modifica" name="nome_modifica">
                <span id="errorNomeModifica" class="error-message"></span><br>
                <label for="descrizione_modifica">Descrizione:</label>
                <textarea id="descrizione" name="descrizione" rows="4" cols="50"></textarea>
                <span id="errorDescrizioneModifica" class="error-message"></span><br>
                <label for="prezzo_modifica">Prezzo:</label>
                <input type="number" id="prezzo_modifica" name="prezzo_modifica">
                <span id="errorPrezzoModifica" class="error-message"></span><br>
                <label for="valido">Valido:</label>
                <input type="text" name="valido" value="1" required>
                <span id="errorValidoModifica" class="error-message"></span><br>
                <input type="submit" value="Modifica">
            </form>
        </div>
    
        <!-- Form per visualizzare un elemento del catalogo -->
        <div class="form-container">
            <h2>Visualizza Prodotto</h2>
            <form id="visualizzaProdotto" action="<%= request.getContextPath() %>/ProdottoSpecificato" method="get" onsubmit="return validateVisualizzaProdotto()">
                <label for="id_visualizza">ID Elemento:</label>
                <input type="text" id="id_visualizza" name="id" required>
                <span id="errorIdVisualizza" class="error-message"></span><br>
                <input type="submit" value="Visualizza">
            </form>
        </div>
    
        <!-- Form per cancellare un elemento del catalogo -->
		<div class="form-container">
		    <h2>Cancella o Modifica visibilità Prodotto</h2>
		    <form id="invalidaProdotto" action="InvalidaProdotto" method="post" onsubmit="return validateInvalidaProdotto()">
		        <label for="id_cancella">ID Elemento:</label>
		        <input type="text" id="id_cancella" name="id" required>
		        <span id="errorIdCancella" class="error-message"></span><br>
		        <label for="valido_cancella">Valido:</label>
		        <input type="text" id="valido_cancella" name="valido" value="0" required>
		        <span id="errorValidoCancella" class="error-message"></span><br>
		        <input type="submit" value="Cancella">
		    </form>
		</div>
        
        <!-- Button to view orders -->
        <div class="form-container">
            <h2>Visualizza Ordini</h2>
            <form id="visualizzaOrdini" action="<%= request.getContextPath() %>/adminOrders" method="get">
                <input type="submit" value="Visualizza Ordini">
            </form>
        </div>
        
    <% } else { %>
        <div class="error-message">
            <p>You must be an admin in order to view this page.</p>
        </div>
    <% } %>
    
    <jsp:include page="footer.jsp" />
</body>
</html>