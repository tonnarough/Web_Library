CREATE TABLE roles
(
    id          INT          NOT NULL AUTO_INCREMENT,
    title   VARCHAR(20)  NOT NULL DEFAULT 2,

    PRIMARY KEY (id),
    INDEX IX_NAME (title ASC) VISIBLE
)