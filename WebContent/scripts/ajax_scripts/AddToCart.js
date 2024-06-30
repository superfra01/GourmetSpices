function AddToCart(productId) {
    let quantityElement = document.getElementById('quantity');
    let quantity;
	if (quantityElement) {
	    quantity = quantityElement.value;
	    // Continua con il resto del tuo codice...
	} else {
	    console.error('L\'elemento con id "quantity" non è presente nel documento.');
	}

    if (quantity == null) {
        quantity = 1;
        
    }
    fetch('carrelloaggiungi', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: 'AddProdottoId=' + encodeURIComponent(productId) + '&quantity=' + encodeURIComponent(quantity)
    })
    .then(response => response.json())
    .then(data => {
        // Gestisci la risposta del server qui
        alert("aggiunto al carrello");
    })
    .catch(error => {
        console.error('Errore:', error);
        alert('Si è verificato un errore durante l\'aggiornamento della quantità.');
    });
}
