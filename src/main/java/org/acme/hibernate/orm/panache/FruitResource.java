package org.acme.hibernate.orm.panache;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Sort;
import io.quarkus.reactive.datasource.ReactiveDataSource;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.acme.hibernate.orm.panache.models.fruits.Fruit;
import org.acme.hibernate.orm.panache.models.fruits.df.FruitDefault;
import org.acme.hibernate.orm.panache.repositories.FruitDefaultRepository;
import org.acme.hibernate.orm.panache.repositories.FruitRepository;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

import static org.jboss.resteasy.reactive.RestResponse.Status.CREATED;

@Path("/fruits")
@ApplicationScoped
public class FruitResource {
    @Inject
    FruitDefaultRepository fruitDefaultRepository;

    @Inject
    FruitRepository fruitRepository;

    @Inject
    @ReactiveDataSource("fruits")
    PgPool client;

    @GET
    @Path("/default")
    public Uni<List<FruitDefault>> get() {
        return FruitDefault.listAll(Sort.by("name"));
    }

    @GET
    @Path("/pool")
    public Multi<org.acme.entity.Fruit > getPool() {
        return org.acme.entity.Fruit.findAll(client);
    }

    @GET
    public Uni<List<Fruit>> getFruit() {
        return Fruit.listAll(Sort.by("name"));
    }

    @GET
    @Path("/default/all")
    public Uni<List<FruitDefault>> getAll() {
        return fruitDefaultRepository.listAll();
    }

    @GET
    @Path("/all")
    public Uni<List<Fruit>> getFruitAll() {
        return fruitRepository.listAll();
    }

    @GET
    @Path("/default/{id}")
    public Uni<FruitDefault> getSingle(Long id) {
        return FruitDefault.findById(id);
    }

    @POST
    @Path("/default")
    public Uni<RestResponse<FruitDefault>> create(FruitDefault fruitDefault) {
        return Panache.withTransaction(fruitDefault::persist).replaceWith(RestResponse.status(CREATED, fruitDefault));
    }
}
