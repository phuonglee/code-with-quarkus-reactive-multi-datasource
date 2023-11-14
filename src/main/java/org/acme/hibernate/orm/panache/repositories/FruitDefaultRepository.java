package org.acme.hibernate.orm.panache.repositories;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.hibernate.orm.panache.models.fruits.df.FruitDefault;

@ApplicationScoped
public class FruitDefaultRepository implements PanacheRepository<FruitDefault> {

}
