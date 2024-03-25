package edu.alatoo.sneakers.model;

import edu.alatoo.sneakers.model.enums.Roles;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Roles name;

    public Role() {

    }
}