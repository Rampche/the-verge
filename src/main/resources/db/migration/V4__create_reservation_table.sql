CREATE TABLE reservation(
reservation_id int auto_increment primary key,
reservation_date date,
reservation_time time(0),
party_size int,
customer_id int,
tables_id int,
FOREIGN KEY (tables_id) REFERENCES tables(tables_id),
FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);