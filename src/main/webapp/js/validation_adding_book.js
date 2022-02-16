const form = document.getElementById('form');
const bookTitle = document.getElementById('book_title_id');
const ageLimit = document.getElementById('age_limit_id');
const numberOfPage = document.getElementById('number_of_page_id');
const genreTitle = document.getElementById('genre_title_id');
const authorLastName = document.getElementById('author_last_name_id');
const authorFirstName = document.getElementById('author_first_name_id');
const publishingTitle = document.getElementById('publishing_title_id');
const yearOfPublishing = document.getElementById('year_of_publishing_id');
const description = document.getElementById('description_id');

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

const isValidBookTitle = bookTitle => {
    const regEx = '^[a-zA-z]{4,}';
    return regEx.test(String(bookTitle).toLowerCase());
}

const isValidAgeLimit = ageLimit => {
    const regEx = '^\+[0-9]{1,2}';
    return regEx.test(String(ageLimit).toLowerCase());
}

const isValidNumberOfPage = numberOfPage => {
    const regEx = '^[0-9]{1,4}';
    return regEx.test(String(numberOfPage).toLowerCase());
}

const isValidGenreTitle = genreTitle => {
    const regEx = '^[a-zA-z]{5,}';
    return regEx.test(String(genreTitle).toLowerCase());
}

const isValidAuthorLastName = authorLastName => {
    const regEx = '^[a-zA-z]{3,}';
    return regEx.test(String(authorLastName).toLowerCase());
}

const isValidAuthorFirstName = authorFirstName => {
    const regEx = '^[a-zA-z]{3,}';
    return regEx.test(String(authorFirstName).toLowerCase());
}

const isValidPublishingTitle = publishingTitle => {
    const regEx = '^[a-zA-z]{2,}';
    return regEx.test(String(publishingTitle).toLowerCase());
}

const isValidYearOfPublishing = yearOfPublishing => {
    const regEx = '^[0-9]{3,4}';
    return regEx.test(String(yearOfPublishing).toLowerCase());
}

const validateInputs = () => {
    const bookTitleValue = bookTitle.value.trim();
    const ageLimitValue = ageLimit.value.trim();
    const numberOfPageValue = numberOfPage.value.trim();
    const genreTitleValue = genreTitle.value.trim();
    const authorLastNameValue = authorLastName.value.trim();
    const authorFirstNameValue = authorFirstName.value.trim();
    const publishingTitleValue = publishingTitle.value.trim();
    const yearOfPublishingValue = yearOfPublishing.value.trim();
    const descriptionValue = description.value.trim();

    if (bookTitleValue === '') {

        setError(bookTitle, 'Book title is required');

    } else if (!(isValidBookTitle(bookTitleValue))) {

        setError(bookTitle, 'Invalid value for book title field');

    } else {

        setSuccess(bookTitle);

    }

    if (ageLimitValue === '') {

        setError(ageLimit, 'Age limit is required');

    } else if (!(isValidAgeLimit(ageLimitValue))) {

        setError(ageLimit, 'Invalid value for age limit field');

    } else {

        setSuccess(ageLimit);

    }

    if (numberOfPageValue === '') {

        setError(numberOfPage, 'Number of page  is required');

    } else if (!(isValidNumberOfPage(numberOfPageValue))) {

            setError(numberOfPage, 'Invalid value for number of page field');

    } else {

        setSuccess(numberOfPage);

    }

    if (genreTitleValue === '') {

        setError(genreTitle, 'Genre title is required');

    } else if (!(isValidGenreTitle(genreTitleValue))) {

        setError(genreTitle, 'Invalid value for genre title field');

    } else {

        setSuccess(genreTitle);

    }

    if (authorLastNameValue === '') {

        setError(authorLastName, 'Author last name is required');

    } else if (!(isValidAuthorLastName(authorLastNameValue))) {

        setError(authorLastName, 'Invalid value for author last name field');

    } else {

        setSuccess(authorLastName);

    }

    if (authorFirstNameValue === '') {

        setError(authorFirstName, 'Author first name is required');

    } else if (!(isValidAuthorFirstName(authorFirstNameValue))) {

        setError(authorFirstName, 'Invalid value for author first name field');

    } else {

        setSuccess(authorFirstName);

    }

    if (publishingTitleValue === '') {

        setError(publishingTitle, 'Publishing title is required');

    } else if (!(isValidPublishingTitle(publishingTitleValue))) {

        setError(publishingTitle, 'Invalid value for publishing title field');

    } else {

        setSuccess(publishingTitle);

    }

    if (yearOfPublishingValue === '') {

        setError(yearOfPublishing, 'Year of publishing is required');

    } else if (!(isValidYearOfPublishing(yearOfPublishingValue))) {

        setError(yearOfPublishing, 'Invalid value for year of publishing field');

    } else {

        setSuccess(yearOfPublishing);

    }

    if (descriptionValue === '') {
        setError(description, 'Description is required');
    } else {
        setSuccess(description);
    }

}
