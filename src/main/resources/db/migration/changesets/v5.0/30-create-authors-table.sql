CREATE TABLE authors
(
    id          INT         NOT NULL AUTO_INCREMENT,
    first_name  VARCHAR(20) NOT NULL,
    last_name   VARCHAR(20) NOT NULL,
    father_name VARCHAR(20) NULL,

    PRIMARY KEY (id),
    UNIQUE INDEX IX_NAME (first_name, last_name ASC) VISIBLE
)