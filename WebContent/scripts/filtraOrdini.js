function filterOrders() {
    let filterDate = document.getElementById("filterDate").value;
    let filterEmail = document.getElementById("filterEmail").value.toLowerCase();
    
    let orders = document.getElementsByClassName("orderRow");
    for (let i = 0; i < orders.length; i++) {
        let orderDate = orders[i].getAttribute("data-order-date");
        let orderEmail = orders[i].getAttribute("data-order-email").toLowerCase();
        
        let dateMatch = !filterDate || orderDate === filterDate;
        let emailMatch = !filterEmail || orderEmail.includes(filterEmail);
        
        if (dateMatch && emailMatch) {
            orders[i].style.display = "";
        } else {
            orders[i].style.display = "none";
        }
    }
}