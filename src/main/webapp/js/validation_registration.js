const form = document.getElementById('form');
const login = document.getElementById('login_id');
const password = document.getElementById('password_id');
const last_name = document.getElementById('last_name_id');
const first_name = document.getElementById('first_name_id');
const email = document.getElementById('email_id');
const mobile = document.getElementById('mobile_id');
const date = document.getElementById('date_id');


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

const isValidEmail = email => {
    const regEx = hello;//TODO email regEx
    return regEx.test(String(email).toLowerCase());
}

const isValidPassword = password => {
    const regEx = hello;//TODO password regEx
    return regEx.test(String(password).toLowerCase());
}

const validateInputs = () => {
    const loginValue = login.value.trim();
    const passwordValue = password.value.trim();
    const last_nameValue = last_name.value.trim();
    const first_nameValue = first_name.value.trim();
    const emailValue = email.value.trim();
    const mobileValue = mobile.value.trim();
    const dateValue = date.value.trim();

    if (loginValue === '') {
        setError(login, 'Login is required');
    } else {
        setSuccess(login);
    }
    if (passwordValue === '') {
        setError(password, 'Password is required');
    } else if (!(isValidPassword(passwordValue) && passwordValue.length > 8)){
        setError(password, 'TODO');
    }else {
        setSuccess(password);
    }
    if (last_nameValue === '') {
        setError(last_name, 'Last name is required');
    } else {
        setSuccess(last_name);
    }
    if (first_nameValue === '') {
        setError(first_name, 'First name is required');
    } else {
        setSuccess(first_name);
    }
    if (emailValue === '') {
        setError(email, 'Email is required');
    } else if (!isValidEmail(emailValue)) {
        setError(email, 'Provide a valid email address');
    } else {
        setSuccess(email);
    }
    if (mobileValue === '') {
        setError(mobile, 'Mobile is required');
    } else {
        setSuccess(mobile);
    }
    if (dateValue === '') {
        setError(date, 'Date is required');
    } else {
        setSuccess(date);
    }

}