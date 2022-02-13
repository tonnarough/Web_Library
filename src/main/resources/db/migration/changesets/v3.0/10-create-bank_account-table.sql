CREATE TABLE bank_account
(
    mtm_user_id        INT NOT NULL,
    mtm_credit_card_id INT NOT NULL,

    PRIMARY KEY (mtm_user_id, mtm_credit_card_id),
    CONSTRAINT FK_USERS_ID
        FOREIGN KEY (mtm_user_id)
            REFERENCES users (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT FK_CREDIT_CARD_ID
        FOREIGN KEY (mtm_credit_card_id)
            REFERENCES credit_cards (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)