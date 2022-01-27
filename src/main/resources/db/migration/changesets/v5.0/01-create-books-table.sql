CREATE TABLE books
(
    id      INT          NOT NULL AUTO_INCREMENT,
    title   VARCHAR(100)   NOT NULL,
    file    VARCHAR(100) NOT NULL,
    picture VARCHAR(100) NOT NULL,

    PRIMARY KEY (id),
    INDEX IX_TITLE (title ASC) VISIBLE
)