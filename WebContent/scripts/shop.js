document.addEventListener("DOMContentLoaded", function() {
    const items = document.querySelectorAll('.product-item');
    items.forEach(item => {
        item.addEventListener('click', function() {
            const link = this.querySelector('a').getAttribute('href');
            window.location.href = link;
        });
    });
});