CREATE TABLE authors_books
(
    mtm_book_id   INT NOT NULL,
    mtm_author_id INT NOT NULL,

    CONSTRAINT FK_AUTHORS_ID
        FOREIGN KEY (mtm_author_id)
            REFERENCES authors (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT FK_AUTHORS_BOOKS_ID
        FOREIGN KEY (mtm_book_id)
            REFERENCES books (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)