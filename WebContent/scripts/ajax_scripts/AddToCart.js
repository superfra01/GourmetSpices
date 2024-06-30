function AddToCart(productId) {
    let quantityElement = document.getElementById('quantity');
    let quantity;
    if (quantityElement) {
        quantity = quantityElement.value;
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
        Swal.fire({
            icon: 'success',
            title: 'Successo',
            text: 'Prodotto aggiunto al carrello!',
            confirmButtonColor: '#E09777' // Questo colore verrà applicato dal CSS globale
        });
    })
    .catch(error => {
        console.error('Errore:', error);
        Swal.fire({
            icon: 'error',
            title: 'Errore',
            text: 'Si è verificato un errore durante l\'aggiornamento della quantità.',
            confirmButtonColor: '#E09777' // Questo colore verrà applicato dal CSS globale
        });
    });
}
