CREATE TABLE customer(
customer_id int auto_increment primary key,
name varchar(255),
email varchar(255) unique,
password varchar(255),
status varchar(100) default 'ACTIVE'
)