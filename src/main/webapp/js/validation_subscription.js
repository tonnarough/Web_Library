const form = document.getElementById('loginform');
const creditCardNumber = document.getElementById('credit_card_number_id');
const cardholderName = document.getElementById('cardholder_name_id');
const cvv = document.getElementById('cvv_id');
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

const isValidCreditCardNumber = password => {
    const regEx = hello;//TODO credit_card_number regEx
    return regEx.test(String(password).toLowerCase());
}

const isValidCardholderName = password => {
    const regEx = hello;//TODO cardholder_name regEx
    return regEx.test(String(password).toLowerCase());
}

const isValidCvv = password => {
    const regEx = hello;//TODO cvv regEx
    return regEx.test(String(password).toLowerCase());
}

const validateInputs = () => {
    const creditCardNumberValue = creditCardNumber.value.trim();
    const cardholderNameValue = cardholderName.value.trim();
    const cvvValue = cvv.value.trim();
    const dateValue = date.value.trim();

    if (creditCardNumberValue === '') {
        setError(creditCardNumber, 'Credit card number is required')
    } else {
        setSuccess(creditCardNumber);
    }
    if (cardholderNameValue === '') {
        setError(cardholderName, 'Cardholder name is required');
    } else {
        setSuccess(cardholderName);
    }
    if (cvvValue === '') {
        setError(cvv, 'CVV is required');
    } else {
        setSuccess(cvv);
    }
    if (dateValue === '') {
        setError(date, 'Date is required');
    } else {
        setSuccess(date);
    }
}