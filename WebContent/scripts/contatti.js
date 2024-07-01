document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('contactLink').addEventListener('click', function(event) {
        event.preventDefault();
        document.getElementById('footer').scrollIntoView({ behavior: 'smooth' });
    });
});