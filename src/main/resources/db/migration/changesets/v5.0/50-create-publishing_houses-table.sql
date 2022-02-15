CREATE TABLE publishing_houses
(
    id                 INT          NOT NULL AUTO_INCREMENT,
    title              VARCHAR(100) NOT NULL,
    year_of_publishing INT          NOT NULL,

    PRIMARY KEY (id),
    UNIQUE INDEX IX_TITLE (title, year_of_publishing ASC) VISIBLE
)