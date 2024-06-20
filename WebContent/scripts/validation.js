const nomeCognomePattern = /^[a-zA-Z]+(?: [a-zA-Z]+)*$/;
const emailPattern = /^\S+@\S+\.\S+$/;
const usernamePattern = /^[a-zA-Z0-9]+$/;
const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;

const nomeErrorMessage = "Il nome può contenere solo lettere<br/>";
const cognomeErrorMessage = "Il cognome può contenere solo lettere<br/>";
const emailErrorMessage = "Inserire un'indirizzo email nella forma: name@domain.ext<br/>";
const usernameErrorMessage = "Lo username può contenere solo lettere e numeri<br/>";
const passwordErrorMessage = "La password deve contenere: almeno 8 caratteri dei quali almeno una cifra<br/>";
const confirmPasswordErrorMessage = "Le password non corrispondono<br/>";

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
});