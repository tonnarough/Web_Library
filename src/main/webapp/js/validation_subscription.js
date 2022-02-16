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

const isValidCreditCardNumber = creditCardNumber => {
    const regEx = '^[0-9]{16}';
    return regEx.test(String(creditCardNumber).toLowerCase());
}

const isValidCardholderName = cardholderName => {
    const regEx = '^\S+\s\S+'
    return regEx.test(String(cardholderName).toLowerCase());
}

const isValidCvv = cvv => {
    const regEx = '^[0-9]{3}';
    return regEx.test(String(cvv).toLowerCase());
}

const validateInputs = () => {
    const creditCardNumberValue = creditCardNumber.value.trim();
    const cardholderNameValue = cardholderName.value.trim();
    const cvvValue = cvv.value.trim();
    const dateValue = date.value.trim();

    if (creditCardNumberValue === '') {

        setError(creditCardNumber, 'Credit card number is required')

    } else if (!(isValidCreditCardNumber(creditCardNumberValue))) {

        setError(bookTitle, 'Invalid value for credit card field');

    } else {

        setSuccess(creditCardNumber);

    }

    if (cardholderNameValue === '') {

        setError(cardholderName, 'Cardholder name is required');

    } else if (!(isValidCardholderName(cardholderNameValue))) {

        setError(cardholderName, 'Invalid value for cardholder name field');

    } else {

        setSuccess(cardholderName);

    }

    if (cvvValue === '') {

        setError(cvv, 'CVV is required');

    } else if (!(isValidCvv(cvvValue))) {

        setError(cvv, 'Invalid value for cardholder name field');

    } else {

        setSuccess(cvv);

    }

    if (dateValue === '') {
        setError(date, 'Date is required');
    } else {
        setSuccess(date);
    }
}