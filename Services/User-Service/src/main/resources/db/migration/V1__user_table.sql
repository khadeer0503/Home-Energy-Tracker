CREATE TABLE users
(
    id                        BIGINT AUTO_INCREMENT NOT NULL,
    name                      VARCHAR(255)          NULL,
    surname                   VARCHAR(255)          NULL,
    email                     VARCHAR(255)          NULL,
    address                   VARCHAR(255)          NULL,
    alerting                  BIT(1)                NOT NULL,
    energy_alerting_threshold DOUBLE                NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);