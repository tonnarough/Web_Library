CREATE TABLE publishing_houses_books
(
    publishing_house_id INT NOT NULL,
    book_id             INT NOT NULL,

    PRIMARY KEY (publishing_house_id, book_id),
    CONSTRAINT FK_PUBLISHING_HOUSES_ID
        FOREIGN KEY (publishing_house_id)
            REFERENCES publishing_houses (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT FK_PUBLISHING_HOUSES_BOOKS_ID
        FOREIGN KEY (book_id)
            REFERENCES books (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)