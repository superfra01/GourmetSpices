function filterOrders() {
    let startDate = document.getElementById("startDate").value;
    let endDate = document.getElementById("endDate").value;
    let filterEmail = document.getElementById("filterEmail").value.toLowerCase();
    
    let orders = document.getElementsByClassName("orderRow");
    for (let i = 0; i < orders.length; i++) {
        let orderDate = orders[i].getAttribute("data-order-date");
        let orderEmail = orders[i].getAttribute("data-order-email").toLowerCase();
        
        let dateMatch = true;
        
        if (startDate) {
            dateMatch = dateMatch && (orderDate >= startDate);
        }
        
        if (endDate) {
            dateMatch = dateMatch && (orderDate <= endDate);
        }

        let emailMatch = !filterEmail || orderEmail.includes(filterEmail);
        
        if (dateMatch && emailMatch) {
            orders[i].style.display = "";
        } else {
            orders[i].style.display = "none";
        }
    }
}