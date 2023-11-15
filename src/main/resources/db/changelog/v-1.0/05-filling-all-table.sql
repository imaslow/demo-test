-- changeset ilya_maslov :1.0.5
INSERT INTO department (department_name)
VALUES ('RISKI'),
       ('BIGDATA'),
       ('TECH');

INSERT INTO post (post_name)
VALUES ('JUNIOR'),
       ('MIDDLE'),
       ('SENIOR');

INSERT INTO employee (last_name, first_name, middle_name, birth_date, department_id, post_id)
VALUES ('IVANOV', 'IVAN', 'IVANOVICH', '1993-01-12', 1, 1),
       ('PETROV', 'PETR', 'PETROVICH', '1997-10-11', 2, 2),
       ('SIDOROV', 'OLEG', 'NIKOLAEVICH', '1990-11-02', 3, 3);