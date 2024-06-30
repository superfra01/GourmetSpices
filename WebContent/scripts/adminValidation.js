const nomePattern = /^[A-Za-z\s]{1,50}$/;
const descrizionePattern = /^[A-Za-z\s\d\.,!?]{1,500}$/;
const prezzoPattern = /^\d+(\.\d{1,2})?$/;
const idPattern = /^\d+$/;
const immaginePattern = /\.(jpe?g|png)$/i;
const validoPattern = /^[01]$/;
const inEvidenzaPattern = /^[01]$/;

const nomeErrorMessage = "Inserisci un nome valido (solo lettere e spazi, max 50 caratteri)";
const descrizioneErrorMessage = "Inserisci una descrizione valida (max 500 caratteri)";
const prezzoErrorMessage = "Inserisci un prezzo valido (es. 10.00)";
const idErrorMessage = "Inserisci un ID valido (solo numeri positivi)";
const immagineErrorMessage = "Inserisci un file immagine valido (JPEG o PNG)";
const validoErrorMessage = "Il campo 'Valido' deve essere 0 o 1";
const evidenzaErrorMessage = "Il campo 'In evidenza' deve essere 0 o 1";

function validateInserisciElemento() {
    let valid = true;
    let form = document.getElementById("inserisciElemento");

    valid = validateFormElem(form.nomeprodotto, nomePattern, document.getElementById("errorNomeProdotto"), nomeErrorMessage) && valid;
    valid = validateFormElem(form.descrizione, descrizionePattern, document.getElementById("errorDescrizione"), descrizioneErrorMessage) && valid;
    valid = validateFormElem(form.prezzo, prezzoPattern, document.getElementById("errorPrezzo"), prezzoErrorMessage) && valid;
    valid = validateFormElem(form.immagine, immaginePattern, document.getElementById("errorImmagine"), immagineErrorMessage) && valid;
    valid = validateFormElem(form.evidenza, inEvidenzaPattern, document.getElementById("errorEvidenza"), evidenzaErrorMessage) && valid;

    return valid;
}

function validateModificaProdotto() {
    let valid = true;
    let form = document.getElementById("modificaProdotto");

    valid = validateFormElem(form.id, idPattern, document.getElementById("errorIdModifica"), idErrorMessage) && valid;
    valid = validateFormElem(form.nome_modifica, nomePattern, document.getElementById("errorNomeModifica"), nomeErrorMessage) && valid;
    valid = validateFormElem(form.descrizione_modifica, descrizionePattern, document.getElementById("errorDescrizioneModifica"), descrizioneErrorMessage) && valid;
    valid = validateFormElem(form.prezzo_modifica, prezzoPattern, document.getElementById("errorPrezzoModifica"), prezzoErrorMessage) && valid;
    valid = validateFormElem(form.valido, validoPattern, document.getElementById("errorValidoModifica"), validoErrorMessage) && valid;
	valid = validateFormElem(form.evidenza_modifica, inEvidenzaPattern, document.getElementById("errorEvidenzaModifica"), evidenzaErrorMessage) && valid;
    return valid;
}

function validateVisualizzaProdotto() {
    let valid = true;
    let form = document.getElementById("visualizzaProdotto");

    valid = validateFormElem(form.id, idPattern, document.getElementById("errorIdVisualizza"), idErrorMessage) && valid;

    return valid;
}

function validateInvalidaProdotto() {
    let valid = true;
    let form = document.getElementById("invalidaProdotto");

    valid = validateFormElem(form.id, idPattern, document.getElementById("errorIdCancella"), idErrorMessage) && valid;
    valid = validateFormElem(form.valido, validoPattern, document.getElementById("errorValidoCancella"), validoErrorMessage) && valid;
	valid = validateFormElem(form.evidenza, inEvidenzaPattern, document.getElementById("errorEvidenzaVisibilita"), evidenzaErrorMessage) && valid;
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
    document.getElementById("inserisciElemento").addEventListener("submit", function(event) {
        if (!validateInserisciElemento()) {
            event.preventDefault();
        }
    });

    document.getElementById("modificaProdotto").addEventListener("submit", function(event) {
        if (!validateModificaProdotto()) {
            event.preventDefault();
        }
    });

    document.getElementById("visualizzaProdotto").addEventListener("submit", function(event) {
        if (!validateVisualizzaProdotto()) {
            event.preventDefault();
        }
    });

    document.getElementById("invalidaProdotto").addEventListener("submit", function(event) {
        if (!validateInvalidaProdotto()) {
            event.preventDefault();
        }
    });
});