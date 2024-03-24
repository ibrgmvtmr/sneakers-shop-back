package edu.alatoo.sneakers.repository;

import edu.alatoo.sneakers.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
