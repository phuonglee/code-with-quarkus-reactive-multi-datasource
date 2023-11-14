package org.acme.hibernate.orm.panache.repositories;

import io.quarkus.reactive.datasource.ReactiveDataSource;
import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.hibernate.orm.panache.models.users.User;

import java.util.function.Function;
import java.util.stream.StreamSupport;

@ApplicationScoped
public class UserRepository {
    @Inject
    @ReactiveDataSource("users")
    PgPool client;

    private final Function<Row, User> ROW_MAPPER = (Row row) ->
            new User(
                    row.getLong("id"),
                    row.getString("name")
            );

    public Multi<User> findAll() {
        return this.client
                .query("SELECT * FROM public.user")
                .execute()
                .onItem().transformToMulti(
                        rs -> Multi.createFrom().items(() -> StreamSupport.stream(rs.spliterator(), false))
                )
                .map(ROW_MAPPER);
    }
}
