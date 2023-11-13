create table fruit
(
    id   bigint not null,
    name varchar(40) unique,
    primary key (id)
);

INSERT INTO fruit(id, name) VALUES (1, 'Cherry');
INSERT INTO fruit(id, name) VALUES (2, 'Apple');
INSERT INTO fruit(id, name) VALUES (3, 'Banana');
