DROP TABLE employee;

CREATE TABLE employee (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    first_name varchar(30) NOT NULL,
    type varchar(20) NOT NULL,
    available varchar(2) NOT NULL
);
