// Theme Toggle Functionality
const themeToggleButton = document.getElementById('theme-toggle');

function switchToDark() {
    document.body.classList.add('dark-theme');
    themeToggleButton.textContent = 'Switch to Light Theme';
}

function switchToLight() {
    document.body.classList.remove('dark-theme');
    themeToggleButton.textContent = 'Switch to Dark Theme';
}

if (themeToggleButton) {
    themeToggleButton.addEventListener('click', function() {
        if (document.body.classList.contains('dark-theme')) {
            switchToLight();
        } else {
            switchToDark();
        }
    });
} else {
    console.error('Theme toggle button not found');
}

// Language Switch Functionality
const switchToEnglishButton = document.getElementById('switch-to-english');
const switchToSpanishButton = document.getElementById('switch-to-spanish');

function switchToSpanish() {
    console.log('Switching to Spanish');
    document.getElementById('nav-home').textContent = 'Página Principal';
    document.getElementById('nav-about').textContent = 'Sobre Mí';
    document.getElementById('nav-projects').textContent = 'Proyectos';
    document.getElementById('nav-social').textContent = 'Contactos Sociales';
    document.getElementById('welcome-message').textContent = 'Bienvenido a Mi Sitio Web';
    document.getElementById('description').textContent = 'Este es un sitio web personal que muestra los proyectos y contactos sociales de Ruimeng';
}

function switchToEnglish() {
    console.log('Switching to English');
    document.getElementById('nav-home').textContent = 'Home Page';
    document.getElementById('nav-about').textContent = 'About Me';
    document.getElementById('nav-projects').textContent = 'Projects';
    document.getElementById('nav-social').textContent = 'Social Contacts';
    document.getElementById('welcome-message').textContent = 'Welcome to My Website';
    document.getElementById('description').textContent = 'This is a personal website showing Ruimeng\'s projects and social contacts';
}

if (switchToEnglishButton && switchToSpanishButton) {
    switchToEnglishButton.addEventListener('click', switchToEnglish);
    switchToSpanishButton.addEventListener('click', switchToSpanish);
} else {
    console.error('Language switch buttons not found');
}

// Update the year dynamically in the footer
const currentYear = new Date().getFullYear();
const footerYear = document.querySelector('footer p');
if (footerYear) {
    footerYear.textContent = `© ${currentYear} My Portfolio`;
} else {
    console.error('Footer paragraph not found');
}

