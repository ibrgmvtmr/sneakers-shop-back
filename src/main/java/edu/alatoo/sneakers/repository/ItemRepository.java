package edu.alatoo.sneakers.repository;

import edu.alatoo.sneakers.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
