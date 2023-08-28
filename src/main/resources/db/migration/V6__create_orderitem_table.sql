CREATE TABLE orderItem(
orderitem_id int auto_increment primary key,
item_id int,
FOREIGN KEY (item_id) REFERENCES items(item_id),
quantity int
);