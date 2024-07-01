<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About Us - GourmetSpices</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/about.css">
</head>
<body>
    <jsp:include page="header.jsp" />
    
    <section id="about-hero">
        <h2>Chi Siamo</h2>
        <h3>La nostra missione e i nostri valori</h3>
    </section>
    
    <section id="about-content">
        <div class="about-text">
            <h2>La Nostra Storia</h2>
            <p>
                Benvenuti su GourmetSpices, dove la qualità e il gusto si incontrano. Siamo un'azienda familiare
                con una passione per le spezie di alta qualità. Da anni selezioniamo solo i migliori prodotti
                per portare sulla vostra tavola sapori autentici e ricchi di tradizione.
            </p>
            <h2>I Nostri Valori</h2>
            <p>
                Crediamo nella sostenibilità, nella qualità e nella soddisfazione del cliente. Ogni spezia che
                trovate nel nostro negozio è stata attentamente selezionata per garantire il massimo della freschezza
                e del sapore. Lavoriamo direttamente con i produttori per assicurarci che ogni prodotto rispetti
                i nostri standard elevati.
            </p>
            <h2>Il Nostro Impegno</h2>
            <p>
                Il nostro impegno è verso di voi, i nostri clienti. Vogliamo offrirvi non solo prodotti eccezionali,
                ma anche un'esperienza di acquisto unica. La vostra soddisfazione è la nostra priorità.
            </p>
        </div>
        <div class="about-image">
            <img src="<%= request.getContextPath() %>/images/about.jpg" alt="Le nostre spezie">
        </div>
    </section>
    
    <jsp:include page="footer.jsp" />
</body>
</html>