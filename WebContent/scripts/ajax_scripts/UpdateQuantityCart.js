function updateQuantity(productId) {
    const quantity = document.getElementById('quantity' + productId).value;
    fetch('UpdateQuantity', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            updateProductId: productId,
            quantity: quantity
        })
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            document.getElementById('total-cost').innerHTML = `Total Cost: <b>${totalCost()}</b>€`;
            if (quantity == 0) {
                document.getElementById('cart-item-' + productId).remove();
            }
        } else {
            alert('Failed to update quantity.');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('An error occurred while updating the quantity.');
    });
}

function removeItem(productId) {
    fetch('UpdateQuantity', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            updateProductId: productId,
            quantity: 0
        })
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            document.getElementById('total-cost').innerHTML = `Total Cost: <b>${totalCost()}</b>€`;
            document.getElementById('cart-item-' + productId).remove();
        } else {
            alert('Failed to remove item.');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('An error occurred while removing the item.');
    });
}

function totalCost(){
	UserBean user = (UserBean) request.getSession().getAttribute("utente");
	if (user != null) {
		float totalCost = 0;
		Integer idCarrello = (Integer) request.getSession().getAttribute("idCarrello");
		List<ContenenteCarrelloBean> cartItems = (List<ContenenteCarrelloBean>) request.getSession().getAttribute("ContenenteCarrelloBeanList"+idCarrello.toString());
		if (cartItems != null && !cartItems.isEmpty()) {
		    for (ContenenteCarrelloBean item : cartItems) {
		        ProdottoBean product = (ProdottoBean) request.getSession().getAttribute("ProdottoCarrello"+item.getIdProdotto());
		        int quantity = item.getQuantita();
		        totalCost += quantity * product.getPrezzo();
		    }
		}
		return totalCost;
	}
}