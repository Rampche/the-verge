CREATE TABLE orders(
orders_id int auto_increment primary key,
payment_status boolean not null,
nfe varchar(255),
tables_id int,
FOREIGN KEY (tables_id) REFERENCES tables(tables_id)
);