CREATE TABLE invoice(
invoice_id int auto_increment primary key,
order_id int,
FOREIGN KEY (order_id) REFERENCES orders(orders_id),
orderitem_id int,
FOREIGN KEY (orderitem_id) REFERENCES order_item(orderitem_id),
total decimal(10,2)
);