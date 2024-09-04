CREATE TABLE category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);


CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    available_quantity DOUBLE PRECISION,
    price NUMERIC(19, 2),
    category_id INTEGER REFERENCES category(id)
);
create sequence if not exists category_seq increment by 50;
create sequence if not exists product_seq increment by 50;