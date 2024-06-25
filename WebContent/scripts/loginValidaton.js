const emailPattern = /^\S+@\S+\.\S+$/;
const safePattern = /^[a-zA-Z0-9]+$/; // Permette solo lettere e numeri

const emailErrorMessage = "Inserire un indirizzo email valido nella forma: name@domain.ext";
const passwordErrorMessage = "La password può contenere solo lettere e numeri";

function validate() {
    let valid = true;
    let form = document.getElementById("loginForm");

    let spanEmail = document.getElementById("errorEmail");
    if (!validateFormElem(form.email, emailPattern, spanEmail, emailErrorMessage)) {
        valid = false;
    }
    let spanPassword = document.getElementById("errorPassword");
    if (!validateFormElem(form.password, safePattern, spanPassword, passwordErrorMessage)) {
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
    document.getElementById("loginForm").onsubmit = function(event) {
        if (!validate()) {
            event.preventDefault();
        }
    };

    // Validazione in tempo reale
    let inputs = document.querySelectorAll("#loginForm input[type='text'], #loginForm input[type='password']");
    inputs.forEach(input => {
        input.addEventListener("input", function() {
            let spanId = "error" + input.name.charAt(0).toUpperCase() + input.name.slice(1);
            let span = document.getElementById(spanId);
            let pattern;
            let message;
            switch (input.name) {
                case "email":
                    pattern = emailPattern;
                    message = emailErrorMessage;
                    validateFormElem(input, pattern, span, message);
                    break;
                case "password":
                    pattern = safePattern;
                    message = passwordErrorMessage;
                    validateFormElem(input, pattern, span, message);
                    break;
                default:
                    pattern = /.*/;
                    message = "";
            }
        });
    });
});