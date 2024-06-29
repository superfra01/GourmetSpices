function AddToCart(productId, quantity = 1) {
	
	
	
    const quantity = document.getElementById('quantity' + productId).value;
    fetch('carrelloaggiungi', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: 'AddProdottoId=' + encodeURIComponent(productId) + '&quantity=' + encodeURIComponent(quantity)
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            // Calculate the new total cost
            let totalCost = 0;
            document.querySelectorAll('.cart-item').forEach(item => {
                const price = parseFloat(item.querySelector('.cart-item-details p b').textContent.replace('€', ''));
                const qty = parseInt(item.querySelector('input[name="quantity"]').value);
                totalCost += price * qty;
            });

            // Update total cost display
            const totalCostElement = document.getElementById('total-cost');
            if (totalCostElement) {
                totalCostElement.innerHTML = '<p>Total Cost: <b>' + totalCost.toFixed(2) + '</b>€</p>';
            }

            if (parseInt(quantity) === 0) {
                const cartItem = document.getElementById('cart-item-' + productId);
                if (cartItem) {
                    cartItem.remove();
                }
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
