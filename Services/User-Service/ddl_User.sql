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

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);
CREATE TABLE users
(
    object_id                 BIGINT                NOT NULL,
    created_date              datetime              NULL,
    updated_date              datetime              NULL,
    active                    BIT(1) DEFAULT 0      NULL,
    id                        BIGINT AUTO_INCREMENT NOT NULL,
    name                      VARCHAR(255)          NULL,
    surname                   VARCHAR(255)          NULL,
    email                     VARCHAR(255)          NULL,
    address                   VARCHAR(255)          NULL,
    alerting                  BIT(1)                NOT NULL,
    energy_alerting_threshold DOUBLE                NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id, object_id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);