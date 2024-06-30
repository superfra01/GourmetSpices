
const dateErrorMessage = "La data di fine non può essere precedente alla data di inizio.";

function validateFilterOrders() {
    let valid = true;

    const startDate = document.getElementById("startDate");
    const endDate = document.getElementById("endDate");
    const filterEmail = document.getElementById("filterEmail");

    const errorStartDate = document.getElementById("errorStartDate");
    const errorEndDate = document.getElementById("errorEndDate");
    const errorFilterEmail = document.getElementById("errorFilterEmail");

    // Reset error messages
    errorStartDate.innerHTML = "";
    errorEndDate.innerHTML = "";
    errorFilterEmail.innerHTML = "";

    if (startDate.value && endDate.value) {
        if (new Date(startDate.value) > new Date(endDate.value)) {
            endDate.classList.add("error");
            errorEndDate.innerHTML = dateErrorMessage;
            errorEndDate.style.color = "red";
            valid = false;
        } else {
            endDate.classList.remove("error");
            errorEndDate.style.color = "black";
            errorEndDate.innerHTML = "";
        }
    }


    return valid;
}

document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("filterOrdersForm").addEventListener("submit", function(event) {
        if (!validateFilterOrders()) {
            event.preventDefault();
        }
    });
});