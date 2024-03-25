package edu.alatoo.sneakers.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "items")
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private Long price;

    private String label;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User user;


    public Item(String name, String label, Long price, User user) {
        this.name = name;
        this.price = price;
        this.label = label;
        this.user = user;
    }
}
