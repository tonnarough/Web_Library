CREATE TABLE authors_books
(
    book_id   INT NOT NULL,
    author_id INT NOT NULL,

    CONSTRAINT FK_AUTHORS_ID
        FOREIGN KEY (author_id)
            REFERENCES authors (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT FK_AUTHORS_BOOKS_ID
        FOREIGN KEY (book_id)
            REFERENCES books (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)