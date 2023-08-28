CREATE TABLE orders(
orders_id int auto_increment primary key,
schedule time(0),
total decimal(10,2),
employee_id int,
FOREIGN KEY (employee_id) REFERENCES employee(employee_id),
reservation_id int,
FOREIGN KEY (reservation_id) REFERENCES reservation(reservation_id)
);