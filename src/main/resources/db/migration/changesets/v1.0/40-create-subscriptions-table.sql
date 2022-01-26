CREATE TABLE subscriptions
(
    id                   INT     NOT NULL AUTO_INCREMENT,
    user_id              INT     NOT NULL,
    subscription_type_id INT     NOT NULL,
    start_date           DATE    NOT NULL,
    end_date             DATE    NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_USER_ID
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT FK_SUBSCRIPTION_TYPE_ID
        FOREIGN KEY (subscription_type_id)
            REFERENCES subscription_types (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)