CREATE TABLE genres
(
    id              INT          NOT NULL AUTO_INCREMENT,
    title           VARCHAR(100) NOT NULL UNIQUE,

    PRIMARY KEY (id),
    INDEX IX_TITLE (title ASC) VISIBLE
)