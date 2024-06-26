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
            // Calculate the new total cost
            let totalCost = 0;
            document.querySelectorAll('.cart-item').forEach(item => {
                const price = parseFloat(item.querySelector('.cart-item-details p b').innerText.replace('€', ''));
                const qty = item.querySelector('input[name="quantity"]').value;
                totalCost += price * qty;
            });

            // Update total cost display
            document.getElementById('total-cost').innerHTML = `Total Cost: <b>${totalCost.toFixed(2)}</b>€`;

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
            document.getElementById('cart-item-' + productId).remove();
            
            // Calculate the new total cost
            let totalCost = 0;
            document.querySelectorAll('.cart-item').forEach(item => {
                const price = parseFloat(item.querySelector('.cart-item-details p b').innerText.replace('€', ''));
                const qty = item.querySelector('input[name="quantity"]').value;
                totalCost += price * qty;
            });

            // Update total cost display
            document.getElementById('total-cost').innerHTML = `Total Cost: <b>${totalCost.toFixed(2)}</b>€`;
        } else {
            alert('Failed to remove item.');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('An error occurred while removing the item.');
    });
}





