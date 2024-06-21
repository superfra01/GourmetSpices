const emailPattern = /^\S+@\S+\.\S+$/;
const passwordPattern = /^[A-Za-z\d]+$/;

const emailErrorMessage = "Inserire un indirizzo email valido<br/>";
const passwordErrorMessage = "Inserire una password valida<br/>";


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
    document.getElementById("loginForm").onsubmit = function(event) {
        if (!validate()) {
            event.preventDefault();
        }
    };
});