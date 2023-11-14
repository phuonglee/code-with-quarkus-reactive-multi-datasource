create table public.user
(
    id   bigint primary key not null,
    name character varying(40)
);
create unique index user_name_key on public.user using btree (name);

INSERT INTO public.user(id, name)
VALUES (1, 'John');
INSERT INTO public.user(id, name)
VALUES (2, 'Felipe');
INSERT INTO public.user(id, name)
VALUES (3, 'Robert');
INSERT INTO public.user(id, name)
VALUES (4, 'Phuong');

