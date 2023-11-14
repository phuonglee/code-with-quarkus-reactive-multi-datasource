package org.acme.hibernate.orm.panache.models.fruits.df;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;

@Entity
@Cacheable
public class FruitDefault extends PanacheEntity {
    @Column(length = 40, unique = true)
    public String name;
}
