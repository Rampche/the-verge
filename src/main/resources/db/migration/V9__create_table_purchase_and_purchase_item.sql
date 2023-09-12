CREATE TABLE purchase(
purchase_id int auto_increment primary key,
tables_id int not null,
price DECIMAL(15,2) not null,
created_at datetime,
FOREIGN KEY (tables_id) REFERENCES tables(tables_id)
);

CREATE TABLE purchase_item(
purchase_id int not null,
item_id int not null,
FOREIGN KEY (purchase_id) REFERENCES purchase(purchase_id),
FOREIGN KEY (item_id) REFERENCES items(item_id),
PRIMARY KEY (purchase_id, item_id)
)