CREATE TABLE credit_cards
(
    id                   INT           NOT NULL AUTO_INCREMENT,
    credit_card_number   VARCHAR(20)   NOT NULL UNIQUE,
    cardholder_name      VARCHAR(50)   NOT NULL,
    card_expiration_date DATE          NOT NULL,
    cvv                  INT           NOT NULL,
    balance              DECIMAL(6, 2) NOT NULL DEFAULT 100,

    PRIMARY KEY (id),
    INDEX IX_CARDHOLDER (cardholder_name ASC) VISIBLE,
    INDEX IX_CREDIT_CARD_NUMBER (credit_card_number ASC) VISIBLE
)