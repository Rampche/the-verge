CREATE TABLE employee(
employee_id int auto_increment primary key,
name varchar(255),
email varchar(255) unique,
role varchar(100) not null
);
