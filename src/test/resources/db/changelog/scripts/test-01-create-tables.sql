DROP TABLE IF EXISTS prices CASCADE;

DROP SEQUENCE IF EXISTS hibernate_sequence;

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE prices (
    id BIGINT NOT NULL DEFAULT nextval('hibernate_sequence'),
    brand_id BIGINT,
    currency_iso_4217 VARCHAR(3),
    end_date TIMESTAMP,
    price NUMERIC(19,2),
    price_list INTEGER,
    priority SMALLINT,
    product_id BIGINT,
    start_date TIMESTAMP,
    PRIMARY KEY (id)
);
