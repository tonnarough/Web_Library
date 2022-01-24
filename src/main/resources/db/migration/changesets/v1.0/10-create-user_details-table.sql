CREATE TABLE user_details
(
    id          INT          NOT NULL AUTO_INCREMENT,
    last_name   VARCHAR(20)  NOT NULL,
    first_name  VARCHAR(20)  NOT NULL,
    father_name VARCHAR(20)  NULL,
    email       VARCHAR(255) NOT NULL UNIQUE,
    mobile      VARCHAR(13)  NOT NULL UNIQUE,
    birthday    DATE         NOT NULL,

    PRIMARY KEY (id),
    INDEX IX_NAME (last_name, first_name, father_name ASC) VISIBLE,
    INDEX IX_EMAIL (email ASC) VISIBLE
)

