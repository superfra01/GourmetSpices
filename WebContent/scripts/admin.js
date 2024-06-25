document.addEventListener("DOMContentLoaded", function() {
    const forms = document.querySelectorAll("form");

    function validateForm(form) {
        let isValid = true;
        let errorMessage = "";

        // Regular expressions for validation
        const namePattern = /^[a-zA-Z0-9\s]+$/;
        const descriptionPattern = /^[a-zA-Z0-9\s,.\-:;!]+$/;
        const pricePattern = /^\d+(\.\d{1,2})?$/;
        const idPattern = /^[a-zA-Z0-9]+$/;
        const validPattern = /^[01]$/;
        const allowedImageTypes = ['image/jpeg', 'image/png'];

        for (let element of form.elements) {
            if (element.type !== 'submit' && element.type !== 'button') {
                if (element.name === "nomeprodotto" && !namePattern.test(element.value)) {
                    isValid = false;
                    errorMessage = "Il nome del prodotto è obbligatorio e deve contenere solo caratteri alfanumerici e spazi.";
                    element.focus();
                    break;
                }
                if (element.name === "descrizione" && !descriptionPattern.test(element.value)) {
                    isValid = false;
                    errorMessage = "La descrizione del prodotto deve essere valida e contenere solo caratteri specifici.";
                    element.focus();
                    break;
                }
                if (element.name === "prezzo" && !pricePattern.test(element.value)) {
                    isValid = false;
                    errorMessage = "Il prezzo deve essere un numero positivo con massimo due cifre decimali.";
                    element.focus();
                    break;
                }
                if ((element.name === "id" || element.name === "id_modifica" || element.name === "id_visualizza" || element.name === "id_cancella") && !idPattern.test(element.value)) {
                    isValid = false;
                    errorMessage = "L'ID è obbligatorio e deve contenere solo caratteri alfanumerici.";
                    element.focus();
                    break;
                }
                if (element.name === "valido" && !validPattern.test(element.value)) {
                    isValid = false;
                    errorMessage = "Il campo Valido deve essere 0 o 1.";
                    element.focus();
                    break;
                }
                if (element.name === "immagine") {
                    if (element.files.length === 0) {
                        isValid = false;
                        errorMessage = "Devi selezionare un'immagine per il prodotto.";
                        element.focus();
                        break;
                    }
                    const file = element.files[0];
                    if (!allowedImageTypes.includes(file.type)) {
                        isValid = false;
                        errorMessage = "L'immagine deve essere in formato JPG o PNG.";
                        element.focus();
                        break;
                    }
                }
            }
        }

        if (!isValid) {
            alert(errorMessage);
            return false;
        }

        return true;
    }

    forms.forEach(form => {
        form.addEventListener("submit", function(event) {
            if (!validateForm(form)) {
                event.preventDefault();
            }
        });
    });

    const inputs = document.querySelectorAll("input, textarea");
    inputs.forEach(input => {
        input.addEventListener("focus", function(event) {
            event.target.style.backgroundColor = "#e0f7fa";
        });

        input.addEventListener("blur", function(event) {
            event.target.style.backgroundColor = "";
        });
    });
});