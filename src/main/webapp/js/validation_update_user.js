const form = document.getElementById('form');
const last_name = document.getElementById('lastName_id');
const first_name = document.getElementById('firstName_id');
const email = document.getElementById('email_id');
const mobile = document.getElementById('mobile_id');
const newPassword = document.getElementById('new_password_id');
const confirmNewPassword = document.getElementById('confirm_new_password_id');


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
};

const setSuccess = (element) => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');

    errorDisplay.innerText = '';
    inputControl.classList.add('success');
    inputControl.classList.remove('error');
};

const isValidEmail = email => {
    const regEx = '^\S+@\S+\.\S+';
    return regEx.test(String(email).toLowerCase());
};

const isValidPassword = newPassword => {
    const regEx = '^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}';
    return regEx.test(String(newPassword).toLowerCase());
};

const isValidLastName = last_name => {
    const regEx = '^[a-zA-z]{3,}';
    return regEx.test(String(last_name).toLowerCase());
};

const isValidFirstName = first_name => {
    const regEx = '^[a-zA-z]{3,}';
    return regEx.test(String(first_name).toLowerCase());
};

const isValidMobile = mobile => {
    const regEx = '^\+[0-9]{12}';
    return regEx.test(String(mobile).toLowerCase());
};

const validateInputs = () => {
    const lastNameValue = last_name.value.trim();
    const firstNameValue = first_name.value.trim();
    const emailValue = email.value.trim();
    const mobileValue = mobile.value.trim();
    const newPasswordValue = newPassword.value.trim();
    const confirmNewPasswordValue = confirmNewPassword.value.trim();

    if (newPasswordValue === '') {

        setError(newPassword, 'Password is required');

    } else if (!(isValidPassword(newPasswordValue))) {

        setError(newPassword, 'Password must contain at least 1 uppercase, 1 lowercase letter and 1 digit');

    } else {

        setSuccess(newPassword);

    }

    if (newPasswordValue === '') {

        setError(confirmNewPassword, 'Please confirm your password')

    }else if(newPasswordValue !== confirmNewPasswordValue){

        setError(confirmNewPassword, "Passwords doesn't match");

    } else {

        setSuccess(confirmNewPassword);

    }

    if (lastNameValue === '') {

        setError(last_name, 'Last name is required');

    } else if (!(isValidLastName(lastNameValue))) {

        setError(last_name, 'Invalid value for last name field');

    } else {

        setSuccess(last_name);

    }

    if (firstNameValue === '') {

        setError(first_name, 'First name is required');

    } else if (!(isValidFirstName(firstNameValue))) {

        setError(first_name, 'Invalid value for first name field');

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

    } else if (!isValidMobile(mobileValue)) {

        setError(mobile, 'Invalid value for mobile field');

    } else {

        setSuccess(mobile);

    }

}