CREATE TABLE IF NOT EXISTS public.user
(
    id        BIGINT NOT NULL ,
    firstname VARCHAR(50) NOT NULL,
    lastname  VARCHAR(50) NOT NULL,
    pesel     VARCHAR(11) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TYPE contact_type AS ENUM ('EMAIL', 'RESIDENCE_ADDRESS', 'REGISTERED_ADDRESS', 'PRIVATE_PHONE_NUMBER', 'BUSINESS_PHONE_NUMBER');

CREATE TABLE IF NOT EXISTS public.contact
(
    id    BIGINT       NOT NULL,
    type  contact_type NOT NULL,
    value VARCHAR(50)  NOT NULL,
    PRIMARY KEY (id, type)
);