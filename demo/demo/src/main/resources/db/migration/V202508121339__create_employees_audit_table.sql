CREATE TABLE employees_audit (
    id BIGINT not null AUTO_INCREMENT,
    name VARCHAR(50) ,
    role VARCHAR(50) ,
    salary DECIMAL(10,2),
    old_name VARCHAR(50) ,
    old_role VARCHAR(50) ,
    old_salary DECIMAL(10,2),
    operation CHAR(1),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;