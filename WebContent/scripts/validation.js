const nomeCognomePattern = /^[a-zA-Z]+(?: [a-zA-Z]+)*$/;
const emailPattern = /^\S+@\S+\.\S+$/;
const usernamePattern = /^[a-zA-Z0-9]+$/;
const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;

const nomeErrorMessage = "Il nome può contenere solo lettere";
const cognomeErrorMessage = "Il cognome può contenere solo lettere";
const emailErrorMessage = "Inserire un indirizzo email valido nella forma: name@domain.ext";
const usernameErrorMessage = "Lo username può contenere solo lettere e numeri";
const passwordErrorMessage = "La password deve contenere almeno 8 caratteri, inclusa una cifra";
const confirmPasswordErrorMessage = "Le password non corrispondono";

function validate() {
    let valid = true;
    let form = document.getElementById("regForm");

    let spanNome = document.getElementById("errorNome");
    if (!validateFormElem(form.nome, nomeCognomePattern, spanNome, nomeErrorMessage)) {
        valid = false;
    }
    let spanCognome = document.getElementById("errorCognome");
    if (!validateFormElem(form.cognome, nomeCognomePattern, spanCognome, cognomeErrorMessage)) {
        valid = false;
    }
    let spanEmail = document.getElementById("errorEmail");
    if (!validateFormElem(form.email, emailPattern, spanEmail, emailErrorMessage)) {
        valid = false;
    }
    let spanUsername = document.getElementById("errorUsername");
    if (!validateFormElem(form.username, usernamePattern, spanUsername, usernameErrorMessage)) {
        valid = false;
    }
    let spanPassword = document.getElementById("errorPassword");
    if (!validateFormElem(form.password, passwordPattern, spanPassword, passwordErrorMessage)) {
        valid = false;
    }
    let spanConfirmPassword = document.getElementById("errorConfirmPassword");
    if (form.password.value !== form.confirm_password.value) {
        spanConfirmPassword.innerHTML = confirmPasswordErrorMessage;
        spanConfirmPassword.style.color = "red";
        form.confirm_password.classList.add("error");
        valid = false;
    } else {
        spanConfirmPassword.innerHTML = "";
        form.confirm_password.classList.remove("error");
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
    document.getElementById("regForm").onsubmit = function(event) {
        if (!validate()) {
            event.preventDefault();
        }
    };

    // Validazione in tempo reale
    let inputs = document.querySelectorAll("#regForm input[type='text'], #regForm input[type='password']");
    inputs.forEach(input => {
        input.addEventListener("input", function() {
            let spanId = "error" + input.name.charAt(0).toUpperCase() + input.name.slice(1);
            let span = document.getElementById(spanId);
            let pattern;
            let message;
            switch (input.name) {
                case "nome":
                case "cognome":
                    pattern = nomeCognomePattern;
                    message = input.name === "nome" ? nomeErrorMessage : cognomeErrorMessage;
                    break;
                case "email":
                    pattern = emailPattern;
                    message = emailErrorMessage;
                    break;
                case "username":
                    pattern = usernamePattern;
                    message = usernameErrorMessage;
                    break;
                case "password":
                    pattern = passwordPattern;
                    message = passwordErrorMessage;
                    break;
                case "confirm_password":
                    if (input.value !== document.getElementById("regForm").password.value) {
                        span.innerHTML = confirmPasswordErrorMessage+"</br>";
                        span.style.color = "red";
                        input.classList.add("error");
                        return;
                    } else {
                        span.innerHTML = "";
                        input.classList.remove("error");
                        return;
                    }
                default:
                    pattern = /.*/;
                    message = "";
            }
            validateFormElem(input, pattern, span, message);
        });
    });
});