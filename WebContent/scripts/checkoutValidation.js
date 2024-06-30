const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
const passwordPattern = /^[A-Za-z\d]*$/;

const emailErrorMessage = "Inserisci una email valida";
const passwordErrorMessage = "La password può contenere solo lettere e numeri";

function validate() {
    let valid = true;
    let form = document.getElementById("loginForm");
    
    let spanEmail = document.getElementById("errorEmail");
    if (!validateFormElem(form.email, emailPattern, spanEmail, emailErrorMessage)) {
        valid = false;
    }
    
    let spanPassword = document.getElementById("errorPassword");
    if (!validateFormElem(form.password, passwordPattern, spanPassword, passwordErrorMessage)) {
        valid = false;
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
    document.getElementById("loginForm").addEventListener("submit", function(event) {
        if (!validate()) {
            event.preventDefault();
        } else {
            // Reset server error messages if client validation passes
            const errorMessages = document.querySelector('.error-messages');
            if (errorMessages) {
                errorMessages.innerHTML = '';
            }
        }
    });
});