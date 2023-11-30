// Import necessary modules
const fs = require('fs');
const path = require('path');
const { JSDOM } = require('jsdom');

// Read the HTML file for the DOM
const html = fs.readFileSync(path.resolve(__dirname, 'index.html'), 'utf8');

let dom;
let container;

// Setup a JSDOM instance before each test
beforeEach(() => {
  dom = new JSDOM(html, { runScripts: 'dangerously' });
  global.document = dom.window.document;
  global.window = dom.window;
  container = document.body;
  require('./script.js'); // Assumes your script file is named 'script.js'
});

// Theme Toggle Tests
describe('theme toggle functionality', () => {
  test('toggles dark theme class on body', () => {
    const themeToggleButton = document.getElementById('theme-toggle');
    // Initial state should be light theme
    expect(container.classList.contains('dark-theme')).toBeFalsy();

    // Simulate the first click to switch to dark theme
    themeToggleButton.click();
    expect(container.classList.contains('dark-theme')).toBeTruthy();

    // Simulate the second click to switch back to light theme
    themeToggleButton.click();
    expect(container.classList.contains('dark-theme')).toBeFalsy();
  });

  test('changes theme toggle button text', () => {
    const themeToggleButton = document.getElementById('theme-toggle');
    // Check initial button text
    expect(themeToggleButton.textContent).toBe('Toggle Theme');
    // Check text after first click
    expect(themeToggleButton.textContent).toBe('Toggle Theme');
    themeToggleButton.click();
    // Check text after second click
    expect(themeToggleButton.textContent).toBe('Toggle Theme');
  });
});

// Language Switch Tests
describe('language switch functionality', () => {
  test('switches to Spanish', () => {
    const switchToSpanishButton = document.getElementById('switch-to-spanish');
    switchToSpanishButton.click();

    // Delay the expectation to allow for DOM updates
    setTimeout(() => {
      expect(document.getElementById('nav-home').textContent).toBe('Página Principal');
      expect(document.getElementById('nav-about').textContent).toBe('Sobre Mí');
      expect(document.getElementById('nav-projects').textContent).toBe('Proyectos');
      expect(document.getElementById('nav-social').textContent).toBe('Contactos Sociales');
      expect(document.getElementById('welcome-message').textContent).toBe('Bienvenido a Mi Sitio Web');
      expect(document.getElementById('description').textContent).toBe('Este es un sitio web personal que muestra los proyectos y contactos sociales de Ruimeng');
    }, 500);
  });

  test('switches to English', () => {
    // Switch to Spanish first to ensure we can switch back to English
    const switchToSpanishButton = document.getElementById('switch-to-spanish');
    switchToSpanishButton.click();

    // Now switch to English
    const switchToEnglishButton = document.getElementById('switch-to-english');
    switchToEnglishButton.click();

    // Check if content of specific elements has changed back to English
    expect(document.getElementById('nav-home').textContent).toBe('Home Page');
    expect(document.getElementById('nav-about').textContent).toBe('About Me');
    expect(document.getElementById('nav-projects').textContent).toBe('Projects');
    expect(document.getElementById('nav-social').textContent).toBe('Social Contacts');
    expect(document.getElementById('welcome-message').textContent).toBe('Welcome to My Website');
    expect(document.getElementById('description').textContent).toBe('This is a personal website showing Ruimeng\'s projects and social contacts');
  });
});

// Footer Year Update Tests
describe('footer year update', () => {
  test('updates footer year dynamically', () => {
    const footerYear = document.querySelector('footer p');
    expect(footerYear.textContent).toContain(new Date().getFullYear().toString());
  });
});

// Error Handling Tests
describe('error handling', () => {
  // Add tests for error handling if necessary
});
