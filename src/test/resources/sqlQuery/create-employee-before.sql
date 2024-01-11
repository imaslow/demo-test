INSERT INTO post (id, post_name)
VALUES (1, 'JUNIOR'),
       (2, 'MIDDLE'),
       (3, 'SENIOR');

INSERT INTO department (id, department_name)
VALUES (1, 'RISKI'),
       (2, 'BIGDATA'),
       (3, 'TECH');

INSERT INTO employee (id, last_name, first_name, middle_name, birth_date, department_id, post_id)
VALUES (1, 'IVANOV', 'IVAN', 'IVANOVICH', '1993-01-12', 1, 1),
       (2, 'PETROV', 'PETR', 'PETROVICH', '1997-10-11', 2, 2),
       (3, 'SIDOROV', 'OLEG', 'NIKOLAEVICH', '1990-11-02', 3, 3);