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

const setSuccess = (element) => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');

    errorDisplay.innerText = '';
    inputControl.classList.add('success');
    inputControl.classList.remove('error');
}

const isValidPassword = password => {
    const regEx = '^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}';
    return regEx.test(String(password).toLowerCase());
}

const validateInputs = () => {
    const loginValue = login.value.trim();
    const passwordValue = password.value.trim();

    if (loginValue === '') {

        setError(login, 'Login is required')

    } else {

        setSuccess(login);

    }

    if (passwordValue === '') {

        setError(password, 'Password is required');

    } else if (!(isValidPassword(passwordValue))){

        setError(password, 'Password must contain at least 1 uppercase, 1 lowercase letter and 1 digit');

    }else {

        setSuccess(password);
    }

};