const form = document.getElementById('loginform');
const login = document.getElementById('login_id');
const password = document.getElementById('password_id');


form.addEventListener("submit", e => {
    e.preventDefault();

    validateInputs();
});

const setError = (element, message) => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');

    errorDisplay.innerText = message;
    inputControl.classList.add('error');
    inputControl.classList.remove('success');
}

const validateInputs = () => {
    const loginValue = login.value.trim();
    const passwordValue = password.value.trim();

    if (loginValue === '') {
        setError(login, 'Login is required')
    } else {

    }
}