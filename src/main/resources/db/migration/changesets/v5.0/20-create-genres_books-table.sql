CREATE TABLE genres_books
(
    book_id  INT NOT NULL,
    genre_id INT NOT NULL,

    CONSTRAINT FK_BOOK_GENRE_ID
        FOREIGN KEY (book_id)
            REFERENCES books (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT FK_GENRE_ID
        FOREIGN KEY (genre_id)
            REFERENCES genres (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)