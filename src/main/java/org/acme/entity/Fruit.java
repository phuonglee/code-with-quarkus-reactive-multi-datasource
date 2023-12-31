package org.acme.entity;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;

public class Fruit {
    public Long id;

    public String name;

    public Fruit() {
    }

    public Fruit(String name) {
        this.name = name;
    }

    public Fruit(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private static Fruit from(Row row) {
        return new Fruit(row.getLong("id"), row.getString("name"));
    }

    public static Multi<Fruit> findAll(PgPool client) {
//        Uni<RowSet<Row>> rowSet = client.query("SELECT id, name FROM fruits ORDER BY name ASC").execute();
//        Multi<Fruit> fruits = rowSet
//                .onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
//                .onItem().transform(Fruit::from);

        return client.query("SELECT * FROM public.fruit ORDER BY name ASC").execute()
                .onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
                .onItem().transform(Fruit::from);
    }
}
