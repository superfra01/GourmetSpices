document.addEventListener('DOMContentLoaded', (event) => {
    document.querySelectorAll('.product-item button').forEach(button => {
        button.addEventListener('click', (e) => {
            e.stopPropagation();
        });
    });
});