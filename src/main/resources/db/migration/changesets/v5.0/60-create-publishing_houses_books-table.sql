CREATE TABLE publishing_houses_books
(
    mtm_book_id             INT NOT NULL,
    mtm_publishing_house_id INT NOT NULL,

    CONSTRAINT FK_PUBLISHING_HOUSES_ID
        FOREIGN KEY (mtm_publishing_house_id)
            REFERENCES publishing_houses (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT FK_PUBLISHING_HOUSES_BOOKS_ID
        FOREIGN KEY (mtm_book_id)
            REFERENCES books (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)