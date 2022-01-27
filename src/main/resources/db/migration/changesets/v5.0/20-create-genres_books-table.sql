CREATE TABLE genres_books
(
    genre_id INT NOT NULL,
    book_id  INT NOT NULL,

    CONSTRAINT FK_GENRES_ID
        FOREIGN KEY (genre_id)
            REFERENCES genres (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT FK_GENRES_BOOKS_ID
        FOREIGN KEY (book_id)
            REFERENCES books (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)