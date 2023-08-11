DROP TYPE IF EXISTS contact_type CASCADE;
CREATE TYPE contact_type AS ENUM ('EMAIL', 'RESIDENCE_ADDRESS', 'REGISTERED_ADDRESS', 'PRIVATE_PHONE_NUMBER', 'BUSINESS_PHONE_NUMBER');

DROP TABLE IF EXISTS "user" CASCADE;
CREATE TABLE IF NOT EXISTS "user"
(
    id        BIGSERIAL   NOT NULL,
    firstname VARCHAR(50) NOT NULL,
    lastname  VARCHAR(50) NOT NULL,
    pesel     VARCHAR(11) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (pesel)
);

DROP TABLE IF EXISTS contact;
CREATE TABLE IF NOT EXISTS contact
(
    id      BIGSERIAL    NOT NULL,
    user_id BIGINT       NOT NULL REFERENCES "user" (id),
    type    contact_type NOT NULL,
    value   VARCHAR(50)  NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (user_id, type)
);

DROP TABLE IF EXISTS user_dump;
CREATE TABLE IF NOT EXISTS user_dump
(
    uuid         uuid NOT NULL,
    created_date timestamp(6) with time zone,
    path         varchar(255),
    PRIMARY KEY (uuid)
);