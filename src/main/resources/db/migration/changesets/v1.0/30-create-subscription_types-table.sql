CREATE TABLE subscription_types
(
    id          INT           NOT NULL AUTO_INCREMENT,
    description VARCHAR(255)  NOT NULL,
    price       DECIMAL(4, 2) NOT NULL UNIQUE,

    PRIMARY KEY (id),
    INDEX IX_PRICE (price ASC) VISIBLE
)