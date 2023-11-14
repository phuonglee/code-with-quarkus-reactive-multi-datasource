package org.acme.hibernate.orm.panache.models.users;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
//@Cacheable
@Table(name = "user", schema = "public")
public class User extends PanacheEntity {

    @Column(name = "name", length = 40, unique = true)
    public String name;

    public User() {

    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
