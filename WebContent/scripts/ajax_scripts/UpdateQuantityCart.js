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
            document.getElementById('total-cost').innerHTML = `Total Cost: <b>${data.totalCost}</b>€`;
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
            document.getElementById('total-cost').innerHTML = `Total Cost: <b>${data.totalCost}</b>€`;
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
