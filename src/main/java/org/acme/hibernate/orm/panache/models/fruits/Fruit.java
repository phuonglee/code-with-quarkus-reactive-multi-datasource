package org.acme.hibernate.orm.panache.models.fruits;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fruit", schema = "public")
public class Fruit extends PanacheEntityBase {
    @Id
    @Column(name = "id", nullable = false)
    public Long id;

    @Column(name = "name", length = 40, unique = true)
    public String name;
}
