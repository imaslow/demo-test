-- changeset ilya_maslov :1.0.4
ALTER TABLE employee
    ADD CONSTRAINT "fkbejtwvg9bxus2mffsm3swj3u9" FOREIGN KEY (department_id) REFERENCES department(id);

ALTER TABLE employee
    ADD CONSTRAINT "fkcm3b9d5fiw8s6co7lkw8c0lbs" FOREIGN KEY (post_id) REFERENCES post(id);

CREATE SEQUENCE seq_department START 10 INCREMENT 1;

CREATE SEQUENCE seq_post START 10 INCREMENT 1;

CREATE SEQUENCE seq_employee START 10 INCREMENT 1;