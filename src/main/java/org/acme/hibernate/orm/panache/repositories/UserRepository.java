package org.acme.hibernate.orm.panache.repositories;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.hibernate.orm.panache.models.users.User;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

}
