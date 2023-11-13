package org.acme.hibernate.orm.panache;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Sort;
import io.quarkus.reactive.datasource.ReactiveDataSource;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.acme.hibernate.orm.panache.models.users.User;
import org.acme.hibernate.orm.panache.repositories.UserRepository;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

import static org.jboss.resteasy.reactive.RestResponse.Status.CREATED;

@Path("/users")
@ApplicationScoped
public class UsersResource {
    @Inject
    UserRepository userRepository;

    @GET
    public Uni<List<User>> get() {
        return User.listAll(Sort.by("name"));
    }

    @GET
    @Path("/all")
//    @ReactiveDataSource("users")
    public Uni<List<User>> getAll() {
        return userRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Uni<User> getSingle(Long id) {
        return User.findById(id);
    }

    @POST
    public Uni<RestResponse<User>> create(User user) {
        return Panache.withTransaction(user::persist).replaceWith(RestResponse.status(CREATED, user));
    }
}
