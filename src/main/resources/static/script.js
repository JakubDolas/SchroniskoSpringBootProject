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


//Skrypty dla strony Log in
const signUpButton=document.getElementById('signUpButton');
const signInButton=document.getElementById('signInButton');
const signInForm=document.getElementById('signIn');
const signUpForm=document.getElementById('signup');

signUpButton.addEventListener('click', function() {
    signInForm.style.display = "none";
    signUpForm.style.display = "block";
    console.log("SignUp form is now visible.");
});

signInButton.addEventListener('click', function() {
    signInForm.style.display = "block";
    signUpForm.style.display = "none";
    console.log("SignIn form is now visible.");
});
