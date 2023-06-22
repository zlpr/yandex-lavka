--liquibase formatted sql

--changeset zlpr:1
INSERT INTO courier_type(type, rating, earning, weight, orders, regions, time_first, time_others)
VALUES ('FOOT', 1, 4, 10, 2, 1, 25, 10),
       ('BIKE', 2, 3, 20, 4, 2, 12, 80),
       ('AUTO', 3, 2, 40, 7, 3, 8, 4);