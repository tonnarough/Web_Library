CREATE TABLE genres_books
(
    mtm_book_id  INT NOT NULL,
    mtm_genre_id INT NOT NULL,

    CONSTRAINT FK_BOOK_GENRE_ID
        FOREIGN KEY (mtm_book_id)
            REFERENCES books (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT FK_GENRE_ID
        FOREIGN KEY (mtm_genre_id)
            REFERENCES genres (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)