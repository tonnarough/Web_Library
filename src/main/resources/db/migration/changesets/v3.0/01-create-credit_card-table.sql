CREATE TABLE credit_cards
(
    id                   INT           NOT NULL AUTO_INCREMENT,
    credit_card_number   INT           NOT NULL UNIQUE,
    cardholder_name      VARCHAR(50)   NOT NULL,
    card_expiration_date DATE          NOT NULL,
    cvv                  INT           NOT NULL,
    balance              DECIMAL(6, 2) NOT NULL,

    PRIMARY KEY (id),
    INDEX IX_CARDHOLDER (cardholder_name ASC) VISIBLE,
    INDEX IX_CREDIT_CARD_NUMBER (credit_card_number ASC) VISIBLE
)