
CREATE TABLE product (
  id int8 NOT NULL,
  name varchar(255) NULL,
  "price" numeric(19, 2)  NULL,
  CONSTRAINT product_pkey PRIMARY KEY (id)
);

CREATE TABLE comment (
  id int8 NOT NULL,
  content varchar(255) NULL,
  CONSTRAINT content_pkey PRIMARY KEY (id)
);