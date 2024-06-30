const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
const passwordPattern = /^[A-Za-z\d]*$/;
const cardNumberPattern = /^\d{16}$/;
const cvvPattern = /^\d{3,4}$/; //American express usa cvv a 4 cifre
const namePattern = /^[A-Za-z\s]+$/;

const emailErrorMessage = "Inserisci una email valida";
const passwordErrorMessage = "La password può contenere solo lettere e numeri";
const cardNumberErrorMessage = "Inserisci un numero di carta valido (16 cifre)";
const cvvErrorMessage = "Inserisci un CVV valido (3 o 4 cifre)";
const nameErrorMessage = "Il nome del titolare della carta può contenere solo lettere e spazi";
const addressErrorMessage = "L'indirizzo di spedizione è obbligatorio";

function validate() {
    let valid = true;
    let form = document.getElementById("checkoutForm");

    let spanCardNumber = document.getElementById("errorCardNumber");
    if (!validateFormElem(form.cardNumber, cardNumberPattern, spanCardNumber, cardNumberErrorMessage)) {
        valid = false;
    }

    let spanDate = document.getElementById("errorDate");
    if (form.expiryDate.value === "") {
        form.expiryDate.classList.add("error");
        spanDate.innerHTML = "La data di scadenza è obbligatoria";
        spanDate.style.color = "red";
        valid = false;
    } else {
        form.expiryDate.classList.remove("error");
        spanDate.style.color = "black";
        spanDate.innerHTML = "";
    }

    let spanCVV = document.getElementById("errorCVV");
    if (!validateFormElem(form.cvv, cvvPattern, spanCVV, cvvErrorMessage)) {
        valid = false;
    }

    let spanName = document.getElementById("errorName");
    if (!validateFormElem(form.cardHolderName, namePattern, spanName, nameErrorMessage)) {
        valid = false;
    }

    let spanAddress = document.getElementById("errorIndirizzo");
    if (form.indirizzoSpedizione.value === "") {
        form.indirizzoSpedizione.classList.add("error");
        spanAddress.innerHTML = addressErrorMessage;
        spanAddress.style.color = "red";
        valid = false;
    } else {
        form.indirizzoSpedizione.classList.remove("error");
        spanAddress.style.color = "black";
        spanAddress.innerHTML = "";
    }

    return valid;
}

function validateFormElem(formElem, pattern, span, message) {
    if (!formElem.value.match(pattern)) {
        formElem.classList.add("error");
        span.innerHTML = message;
        span.style.color = "red";
        return false;
    }
    formElem.classList.remove("error");
    span.style.color = "black";
    span.innerHTML = "";
    return true;
}

document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("checkoutForm").addEventListener("submit", function(event) {
        if (!validate()) {
            event.preventDefault();
        } else {
            const errorMessages = document.querySelector('.error-messages');
            if (errorMessages) {
                errorMessages.innerHTML = '';
            }
        }
    });
});