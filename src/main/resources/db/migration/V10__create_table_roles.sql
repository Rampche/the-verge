CREATE TABLE roles(
employee_id int not null,
role varchar(50),
FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
)