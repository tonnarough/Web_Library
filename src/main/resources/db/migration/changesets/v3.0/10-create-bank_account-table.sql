CREATE TABLE bank_account
(
    user_id        INT NOT NULL,
    credit_card_id INT NOT NULL,

    CONSTRAINT FK_USERS_ID
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT FK_CREDIT_CARD_ID
        FOREIGN KEY (credit_card_id)
            REFERENCES credit_cards (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)