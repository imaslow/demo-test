-- changeset ilya_maslov :1.0.1
CREATE TABLE employee
(
    id     BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    last_name                VARCHAR(128)    NOT NULL,
    first_name               VARCHAR(128)    NOT NULL,
    middle_name              VARCHAR(128)    NOT NULL,
    birth_date               date            NOT NULL,
    department_id            BIGINT          NOT NULL,
    post_id                  BIGINT          NOT NULL,
    CONSTRAINT "employeePK" PRIMARY KEY (id)
);

ALTER TABLE employee
  ADD CONSTRAINT "employeeFK" FOREIGN KEY (department_id) REFERENCES department(id);

ALTER TABLE employee
  ADD CONSTRAINT "employeeFK2" FOREIGN KEY (post_id) REFERENCES post(id);

INSERT INTO employee (last_name, first_name, middle_name, birth_date, department_id, post_id)
VALUES ('IVANOV', 'IVAN', 'IVANOVICH', '1993-01-12', 1, 1),
       ('PETROV', 'PETR', 'PETROVICH', '1997-10-11', 2, 2),
       ('SIDOROV', 'OLEG', 'NIKOLAEVICH', '1990-11-02', 3, 3);

CREATE SEQUENCE seq_employee START 10 INCREMENT 1;