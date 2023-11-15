-- changeset ilya_maslov :1.0.4
ALTER TABLE employee
    ADD CONSTRAINT "employeeFK" FOREIGN KEY (department_id) REFERENCES department(id);

ALTER TABLE employee
    ADD CONSTRAINT "employeeFK2" FOREIGN KEY (post_id) REFERENCES post(id);

CREATE SEQUENCE seq_department START 10 INCREMENT 1;

CREATE SEQUENCE seq_post START 10 INCREMENT 1;

CREATE SEQUENCE seq_employee START 10 INCREMENT 1;