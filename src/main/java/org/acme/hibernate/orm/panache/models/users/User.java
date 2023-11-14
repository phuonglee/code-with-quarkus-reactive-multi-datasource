package org.acme.hibernate.orm.panache.models.users;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user", schema = "public")
public class User extends PanacheEntityBase {

    @Id
    @Column(name = "id", nullable = false)
    public Long id;

    @Column(name = "name", length = 40, unique = true)
    public String name;

    public User() {}

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
