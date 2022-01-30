CREATE TABLE users
(
    id              INT          NOT NULL AUTO_INCREMENT,
    role_id         INT          NOT NULL Default 2,
    user_details_id INT          NOT NULL UNIQUE,
    login           VARCHAR(255) NULL UNIQUE,
    password        VARCHAR(255) NOT NULL,

    PRIMARY KEY (id),
    INDEX IX_LOGIN (login ASC) VISIBLE,
    CONSTRAINT FK_ROLE_ID
        FOREIGN KEY (role_id)
            REFERENCES roles (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT FK_USER_DETAIL_ID
        FOREIGN KEY (user_details_id)
            REFERENCES user_details (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)

