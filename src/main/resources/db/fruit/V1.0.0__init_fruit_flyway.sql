create table public.fruit
(
    id   bigint primary key not null,
    name character varying(40)
);
create unique index fruit_name_key on public.fruit using btree (name);

INSERT INTO public.fruit(id, name) VALUES (1, 'Cherry');
INSERT INTO public.fruit(id, name) VALUES (2, 'Apple');
INSERT INTO public.fruit(id, name) VALUES (3, 'Banana');
