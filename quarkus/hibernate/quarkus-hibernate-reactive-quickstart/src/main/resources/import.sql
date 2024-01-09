
DROP TABLE IF EXISTS product;

CREATE TABLE product (
    id bigserial NOT NULL,
    "name" varchar(255) NULL,
    CONSTRAINT product_pkey PRIMARY KEY (id)
);

INSERT INTO product(name) VALUES ('name#1');
INSERT INTO product(name) VALUES ('name#2');
INSERT INTO product(name) VALUES ('name#3');

