const navLinks = document.querySelectorAll('.nav-links a');

function setActiveLink() {
    navLinks.forEach(link => {
        link.classList.remove('active'); // Usuwamy klasę 'active' ze wszystkich linków
    });
    const currentHash = window.location.hash; // Pobieramy aktualny hash URL
    const activeLink = document.querySelector(`a[href="${currentHash}"]`);
    if (activeLink) {
        activeLink.classList.add('active'); // Dodajemy klasę 'active' do aktualnego linku
    }
}

window.addEventListener('hashchange', setActiveLink); // Ustawienie aktywnego linku przy zmianie hasha
window.addEventListener('DOMContentLoaded', setActiveLink); // Ustawienie aktywnego linku przy załadowaniu strony
