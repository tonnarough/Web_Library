CREATE TABLE books
(
    id              INT          NOT NULL AUTO_INCREMENT,
    title           VARCHAR(100) NOT NULL,
    description     TEXT(2000)   NOT NULL,
    age_limit       VARCHAR(3)   NOT NULL,
    number_of_pages INT          NOT NULL,
    file            VARCHAR(100) NOT NULL UNIQUE,
    picture         VARCHAR(100) NOT NULL,

    PRIMARY KEY (id),
    INDEX IX_TITLE (title ASC) VISIBLE
)